package cui.toughtstorm.core.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ToughtStormConnection {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/toughtstormdb";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    public ToughtStormConnection() {

    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        System.out.println("Connection created successfully!");
        return conn;
    }

}
