package com.example.muhammad.chambers.c195.pa.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class SQLHelper {

    public static int updateForStrColumn(String tableName, String primaryKeyIdColumnName, int primaryKeyId, String columnToUpdateName, String valueToUpdate) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = ? WHERE %s = ?", tableName, columnToUpdateName, primaryKeyIdColumnName);
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        ps.setString(1, valueToUpdate);
        ps.setInt(2, primaryKeyId);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static int updateForIntColumn(String tableName,  String primaryKeyIdColumnName, int primaryKeyId, String columnToUpdateName, int valueToUpdate) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = ? WHERE %s = ?", tableName, columnToUpdateName, primaryKeyIdColumnName);
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        ps.setInt(1, valueToUpdate);
        ps.setInt(2, primaryKeyId);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static int updateForTimestampColumn(String tableName, String primaryKeyIdColumnName, int primaryKeyId, String columnToUpdateName, Timestamp valueToUpdate) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = ? WHERE %s = ?", tableName, columnToUpdateName, primaryKeyIdColumnName);
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        ps.setTimestamp(1, valueToUpdate);
        ps.setInt(2, primaryKeyId);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static int delete(String tableName, String primaryKeyIdColumnName, int primaryKeyId) throws SQLException {
        String sql = String.format("DELETE FROM %s WHERE %s = ?", tableName, primaryKeyIdColumnName);
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        ps.setInt(1, primaryKeyId);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }
}
