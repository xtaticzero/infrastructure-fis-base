/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 *
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.base.dao;

import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;

/**
 * Clase DAO principal que sirve de base para los DAO JDBC TEMPLATE
 *
 */
public abstract class BaseJDBCDao<T extends BaseModel> extends BaseRepository<T> {

    /**
     * Numero de version
     */
    private static final long serialVersionUID = -2424923901624965933L;

    /**
     * Log de la clase.
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Jdbc template generico.
     */
    @Autowired
    @Qualifier("jdbcTemplate")
    private transient JdbcTemplate jdbcTemplateBase;

    /**
     * Constructor por defecto.
     */
    public BaseJDBCDao() {
        super();
    }

    /**
     * Obtiene el jdbcTemplateBase.
     * 
     * @return regresa el jdbcTemplateBase.
     */
    public final JdbcTemplate getJdbcTemplateBase() {
        return jdbcTemplateBase;
    }

    /**
     * Metodo encargado de realizar consultas.
     *
     * @param sql
     *            SQL de la consulta a realizar.
     * @param args
     *            Argumentos para la consulta.
     * @return Listado de objetos de la entidad.
     */
    protected final List<T> findByQuery(final String sql, final Object... args) {
        return this.jdbcTemplateBase.query(sql, super.getRowMapper(), args);
    }

    /**
     * Metodo encargado de relizar la consulta y obtener un solo registro.
     *
     * @param sql
     *            SQL de la consulta a realizar.
     * @param args
     *            Argumentos para la consulta.
     * @return Objeto de la entidad.
     */
    protected final T findOne(final String sql, final Object... args) {
        return this.jdbcTemplateBase.queryForObject(sql, super.getRowMapper(),
                args);
    }

    /**
     * Metodo encargado de guardar o actualizar en la base de datos.
     *
     * @param sql
     *            SQL de la consulta a realizar.
     * @param params
     *            Argumentos para el save or update.
     * @return 1 si se guardo o actualizo correctamente.
     */
    protected final int saveOrUpdate(final String sql, final Object[] params) {
        return this.jdbcTemplateBase.update(sql, params);
    }

    /**
     * Metodo encargado de guardar un registro en la base de datos y regresar el
     * Id del registro insertado.
     *
     * @param tableName
     *            Nombre de la tabla donde se insertara el registro
     * @param idColumn
     *            Nombre de la columna de la llave primaria, debe ser numerica
     *            autoincrementable
     * @param params
     *            Mapa <nombreColumna, objValor> valores a guardar
     * @return El id generado del registro
     */
    protected final long save(final String tableName, final String idColumn,
            final Map<String, Object> params) {
        SimpleJdbcInsert simpleJdbcTemplateBase = new SimpleJdbcInsert(
                this.jdbcTemplateBase.getDataSource());

        return simpleJdbcTemplateBase.withTableName(tableName)
                .usingGeneratedKeyColumns(idColumn).executeAndReturnKey(params)
                .longValue();
    }

    /**
     * Metodo encargado de guardar un registro en la base de datos. Guarda el
     * registro sin generar el identificador, este debe ser mandado en el mapa
     * de parametros.
     *
     * @param tableName
     *            Nombre de la tabla donde se guardara el registro.
     * @param params
     *            Mapa con las relaciones columna - valor.
     * @return Regresa el numero de registros insertados.
     */
    protected final int save(final String tableName,
            final Map<String, Object> params) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                this.jdbcTemplateBase.getDataSource());

        return jdbcInsert.withTableName(tableName).execute(params);
    }

    /**
     * Metodo encargado de guardar un registro en la base de datos y regresar el
     * Id del registro insertado especificando la secuencia a utilizar.
     *
     * @param tableName
     *            Nombre de la tabla donde se guardara el registro.
     * @param idColumn
     *            Columna identificada como PK.
     * @param params
     *            Mapa con las relaciones columna - valor.
     * @param secuenceName
     *            Nombre de la secuencia a utilizar para obtener el ID.
     * @return ID generado por la secuencia.
     */
    protected final long save(final String tableName, final String idColumn,
            final Map<String, Object> params, final String secuenceName) {
        SimpleJdbcInsert simpleJdbcTemplateBase = new SimpleJdbcInsert(
                this.jdbcTemplateBase.getDataSource());

        long idGen = this.getSequence(secuenceName);
        params.put(idColumn, idGen);
        simpleJdbcTemplateBase.withTableName(tableName).execute(params);

        return idGen;
    }

    /**
     * Metodo encargado de obtener la siguiente secuencia, dado el nombre de la
     * secuencia especificada.
     *
     * @param nombreSecuencia
     *            Nombre de la secuencia.
     * @return Numero de secuencia correspondiente.
     */
    protected final long getSequence(final String nombreSecuencia) {
        OracleSequenceMaxValueIncrementer sequence = new OracleSequenceMaxValueIncrementer(
                this.jdbcTemplateBase.getDataSource(), nombreSecuencia);

        return sequence.nextLongValue();
    }

}
