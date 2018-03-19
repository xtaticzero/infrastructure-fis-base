package mx.gob.sat.siat.base.dao;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.base.excepcion.RowMapperException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Clase padre para los repository.
 */
public abstract class BaseRepository<T extends BaseModel> implements
        Serializable {

    /**
     * Numero de version
     */
    private static final long serialVersionUID = 2957590278387291297L;

    /**
     * Clase de la entidad.
     */
    private Class<T> clase;

    /**
     * Constructor por defecto.
     */
    @SuppressWarnings("unchecked")
    public BaseRepository() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.clase = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    /**
     * Obtiene la clase.
     *
     * @return Clase
     */
    protected final Class<T> getClase() {
        return this.clase;
    }

    /**
     * Obtiene el nombre completo de la clase entidad. Incluye el nombre del
     * paquete, por ejemplo:
     * mx.gob.sat.siat.buzon.framework.domain.entity.Supuesto
     *
     * @return Nombre de la clase entidad
     */
    protected final String getEntityName() {
        return this.clase.getName();
    }

    /**
     * Metodo encargado de obtener el RowMapper dada la entidad.
     *
     * @return RowMapper de la entidad.
     */
    @SuppressWarnings("unchecked")
    protected final RowMapper<T> getRowMapper() {

        RowMapper<T> rm = null;

        try {

            StringBuffer sb = new StringBuffer(this.clase.getPackage()
                    .getName());
            sb.append(".rowmapper.");
            sb.append(this.clase.getSimpleName());
            sb.append("RowMapper");

            String className = sb.toString();

            Class<?> claseRM = Class.forName(className);
            Constructor<?> constructor = claseRM.getConstructor();

            rm = (RowMapper<T>) constructor.newInstance();
        } catch (Exception ex) {
            throw new RowMapperException(ex);
        }

        return rm;
    }

}
