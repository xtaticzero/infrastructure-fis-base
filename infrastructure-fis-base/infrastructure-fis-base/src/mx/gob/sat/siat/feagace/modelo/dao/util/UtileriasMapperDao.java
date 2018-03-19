/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

/**
 * 
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class UtileriasMapperDao {

    public static boolean existeColumna(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        for (int columna = 1; columna <= metaData.getColumnCount(); columna++) {
            if (columnName.equalsIgnoreCase(metaData.getColumnName(columna))) {
                return true;
            }
        }
        return false;
    }

    public static String getNameFileFromPath(String pathFile) {
        String name = "";
        if (pathFile != null && !pathFile.trim().isEmpty()) {
            if (pathFile.indexOf('/') >= 0) {
                name = pathFile.substring(pathFile.lastIndexOf('/') + 1);
            } else if (pathFile.indexOf('\\') >= 0) {
                name = pathFile.substring(pathFile.lastIndexOf('\\') + 1);
            } else {
                name = pathFile;
            }

        }
        return name;
    }

    public static String getPathFromAbsolutePath(String pathFile) {
        String path = "";
        if (pathFile != null && !pathFile.trim().isEmpty()) {
            if (pathFile.indexOf('/') >= 0) {
                path = pathFile.substring(0, pathFile.lastIndexOf('/') + 1);
            } else if (pathFile.indexOf('\\') >= 0) {
                path = pathFile.substring(0, pathFile.lastIndexOf('\\') + 1);
            } else {
                path = pathFile;
            }

        }
        return path;
    }

    public static java.sql.Date getDateLikeSQLFormat(Date fecha) {
        if (fecha != null) {
            return new java.sql.Date(fecha.getTime());
        } else {
            return null;
        }
    }
}
