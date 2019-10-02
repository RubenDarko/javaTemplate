package com.etaluma.scopesmart.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.etaluma.scopesmart.util.DatabaseQueries.*;

public class Database {

    //private final static String url = "jdbc:postgresql://localhost/IVDGatewayService";
    private final static String url = "jdbc:postgresql://ec2-35-160-94-191.us-west-2.compute.amazonaws.com/IVDGatewayService";
    private final static String user = "IVDGatewayService";
    private final static String password = "";

    static {
        String SQL = ADD_USER;
        int affectedrows = 0;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            affectedrows = pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public int deleteRows() throws SQLException {
        String SQL = CLEAN_DATABASE;
        int affectedrows = 0;
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            affectedrows = pstmt.executeUpdate();
        }
        return affectedrows;
    }

    public List<Map> getPatientSamples() throws SQLException {
        List<Map> results;
        try (Connection conn = connect(); Statement pstmt = conn.createStatement()) {
            results = resultSetToArrayList(pstmt.executeQuery(SELECT_FROM_PATIENT_TEST_SAMPLE));
        }
        return results;
    }

    public List<Map> getTestBatches() throws SQLException {
        List<Map> results;
        try (Connection conn = connect(); Statement pstmt = conn.createStatement()) {
            results = resultSetToArrayList(pstmt.executeQuery(SELECT_FROM_TEST_BATCH));
        }
        return results;
    }

    public List<Map> getTestBatchAud() throws SQLException {
        List<Map> results;
        try (Connection conn = connect(); Statement pstmt = conn.createStatement()) {
            results = resultSetToArrayList(pstmt.executeQuery(SELECT_FROM_TEST_BATCH_AUD));
        }
        return results;
    }

    public List<Map> getSlideAud() throws SQLException {
        List<Map> results;
        try (Connection conn = connect(); Statement pstmt = conn.createStatement()) {
            results = resultSetToArrayList(pstmt.executeQuery(SELECT_FROM_SLIDE_AUD));
        }
        return results;
    }

    public List<Map> getWellSampleAud() throws SQLException {
        List<Map> results;
        try (Connection conn = connect(); Statement pstmt = conn.createStatement()) {
            results = resultSetToArrayList(pstmt.executeQuery(SELECT_FROM_WELL_SAMPLE_AUD));

        }
        return results;
    }

    public List<Map> getControlSample() throws SQLException {
        List<Map> results;
        try (Connection conn = connect(); Statement pstmt = conn.createStatement()) {
            results = resultSetToArrayList(pstmt.executeQuery(SELECT_FROM_CONTROL_SAMPLE));
        }
        return results;
    }

    public List<Map> getSlide() throws SQLException {
        List<Map> results;
        try (Connection conn = connect(); Statement pstmt = conn.createStatement()) {
            results = resultSetToArrayList(pstmt.executeQuery(SELECT_FROM_SLIDE));
        }
        return results;
    }

    public List<Map> getWellSample() throws SQLException {
        List<Map> results;
        try (Connection conn = connect(); Statement pstmt = conn.createStatement()) {
            results = resultSetToArrayList(pstmt.executeQuery(SELECT_FROM_WELL_SAMPLE));
        }
        return results;
    }

    public List<Map> resultSetToArrayList(ResultSet resultSet) throws SQLException{
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columns = resultSetMetaData.getColumnCount();
        List<Map> list = new ArrayList(50);
        while (resultSet.next()){
            Map<String,Object> row = new HashMap(columns);
            for(int i=1; i<=columns; ++i){
                row.put(resultSetMetaData.getColumnName(i),resultSet.getObject(i));
            }
            list.add(row);
        }
        return list;
    }
}