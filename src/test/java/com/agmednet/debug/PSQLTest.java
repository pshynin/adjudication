package com.agmednet.debug;


import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by pshynin on 1/17/17.
 */
public class PSQLTest {

    @Test
    public void testConnection() {
        String dbURL = "jdbc:postgresql://db.test05.agmednet.net:5432/portal_db";
        Properties parameters = new Properties();
        parameters.put("user", "master");
        parameters.put("password", "Agmednet1!");
        try {
            Connection conn = DriverManager.getConnection(dbURL, parameters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
