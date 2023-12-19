package com.greensupermarket.util;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;

public class ConnectionManager {

    public Connection getConnection() {
        Properties properties = new Properties();
        InputStream InputStream = null;
        
        try {
            // Provide the path to your database.properties file
            InputStream = getClass().getClassLoader().getResourceAsStream("DB/database.properties");
            properties.load(InputStream);
            
            String dbUrl = properties.getProperty("database.url");
            String dbUsername = properties.getProperty("database.username");
            String dbPassword = properties.getProperty("database.password");
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            return con;  
        }
        
        catch (Exception e) {
            System.out.println(e);
            return null;
        } 
        
        finally {
            if (InputStream != null) {
                try {
                    InputStream.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
    
    public void closeConnection(Connection con){
        if(con != null){
            try{
                con.close();
            }
            catch(Exception e){
                System.out.println(e);
            
            }
        }
    }
}
