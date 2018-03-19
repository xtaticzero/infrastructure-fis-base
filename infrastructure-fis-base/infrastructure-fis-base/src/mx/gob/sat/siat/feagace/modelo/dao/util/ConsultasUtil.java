package mx.gob.sat.siat.feagace.modelo.dao.util;

/**
 * Clase utileria para las consultas SQL
 * @author eolf
 */
public final class ConsultasUtil {

    private static final String SQL = "SQL: ";
    private static final String PARAMS = "PARAMS: ";
    private static final String ESPACIO = "\n";

    private ConsultasUtil() {
        super();
    }
    
    /**
     * Metodo para armar la consulta SQL
     * author eolf
     * @param consulta
     * @param sql
     */
    private static void armarConsulta(StringBuilder consulta, final String sql) {
        if (sql != null) {
            consulta.append(ESPACIO);
            consulta.append(SQL);
            consulta.append("[");
            consulta.append(sql);
            consulta.append("]");
        } // if
    }

    /**
     * Armado del mensaje de la consulta sql
     * @author eolf
     * @param sql
     * @return
     */
    public static String armarValoresConsulta(String sql) {
        StringBuilder consulta = new StringBuilder();
        armarConsulta(consulta, sql);
        return consulta.toString();
    }

    /**
     * Armado del mensaje de la consulta sql y sus parametros
     * @author eolf
     * @param sql
     * @param parameters
     * @return
     */
    public static String armarValoresConsulta(String sql, Object[] parameters) {
        StringBuilder consulta = new StringBuilder();
        armarConsulta(consulta, sql);
        if (parameters != null && parameters.length > 0) {
            consulta.append(ESPACIO);
            consulta.append(PARAMS);
            consulta.append("[");
            if(parameters.length > 0) {                
                StringBuilder parametros = new StringBuilder();
                for (Object paramActual : parameters) {
                    if (paramActual != null) {
                        parametros.append(paramActual).append(",");
                    } // if
                } // for
                String tmp = parametros.substring(0, parametros.length() - 1);
                consulta.append(tmp);
            }
            consulta.append("]");
        } // if
        return consulta.toString();
    }
}
