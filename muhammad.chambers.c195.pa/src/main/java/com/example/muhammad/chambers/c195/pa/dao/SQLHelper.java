package com.example.muhammad.chambers.c195.pa.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/** This class contains the common SQL code to pull data from the database.*/
public class SQLHelper {
    /** This is the updateForStrColumn method.
     This method updates a string column in the database.
     @param tableName the name of the table to update the row for
     @param primaryKeyIdColumnName the tables primary key column name
     @param primaryKeyId the tables primary key column id value
     @param columnToUpdateName the name of the column to update
     @param valueToUpdate the value to replace in the column
     @return Returns an integer as to how many rows were affected*/
    public static int updateForStrColumn(String tableName, String primaryKeyIdColumnName, int primaryKeyId, String columnToUpdateName, String valueToUpdate) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = ? WHERE %s = ?", tableName, columnToUpdateName, primaryKeyIdColumnName);
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        ps.setString(1, valueToUpdate);
        ps.setInt(2, primaryKeyId);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    /** This is the updateForIntColumn method.
     This method updates an integer column in the database.
     @param tableName the name of the table to update the row for
     @param primaryKeyIdColumnName the tables primary key column name
     @param primaryKeyId the tables primary key column id value
     @param columnToUpdateName the name of the column to update
     @param valueToUpdate the value to replace in the column
     @return Returns an integer as to how many rows were affected*/
    public static int updateForIntColumn(String tableName,  String primaryKeyIdColumnName, int primaryKeyId, String columnToUpdateName, int valueToUpdate) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = ? WHERE %s = ?", tableName, columnToUpdateName, primaryKeyIdColumnName);
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        ps.setInt(1, valueToUpdate);
        ps.setInt(2, primaryKeyId);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    /** This is the updateForTimestampColumn method.
     This method updates a timestamp column in the database.
     @param tableName the name of the table to update the row for
     @param primaryKeyIdColumnName the tables primary key column name
     @param primaryKeyId the tables primary key column id value
     @param columnToUpdateName the name of the column to update
     @param valueToUpdate the value to replace in the column
     @return Returns an integer as to how many rows were affected*/
    public static int updateForTimestampColumn(String tableName, String primaryKeyIdColumnName, int primaryKeyId, String columnToUpdateName, Timestamp valueToUpdate) throws SQLException {
        String sql = String.format("UPDATE %s SET %s = ? WHERE %s = ?", tableName, columnToUpdateName, primaryKeyIdColumnName);
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        ps.setTimestamp(1, valueToUpdate);
        ps.setInt(2, primaryKeyId);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    /** This is the delete method.
     This method is used to remove a row from the database.
     @param tableName the name of the table to update the row for
     @param primaryKeyIdColumnName the tables primary key column name
     @param primaryKeyId the tables primary key column id value
     @return Returns an integer as to how many rows were affected*/
    public static int delete(String tableName, String primaryKeyIdColumnName, int primaryKeyId) throws SQLException {
        String sql = String.format("DELETE FROM %s WHERE %s = ?", tableName, primaryKeyIdColumnName);
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        ps.setInt(1, primaryKeyId);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }
}
