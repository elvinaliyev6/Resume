package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Elvin
 */
public abstract class AbstractDAO {

    public Connection connect() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String userName = "root";
        String password = "12345";

        String url = "jdbc:mysql://localhost:3306/resume";
        return DriverManager.getConnection(url, userName, password);
    }
}
