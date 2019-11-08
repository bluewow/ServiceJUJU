package com.stockmarket.www.service.jdbc;

import java.sql.*;

public class JdbcContext {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection(url, "ACORNGROUP1", "month100man");

        return connection;
    }

    public static Statement getStatement() throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        return statement;
    }

    public static PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        return statement;
    }
}
