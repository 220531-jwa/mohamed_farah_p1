package dev.farah.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil 
									{
private static ConnectionUtil cu;

    private static Properties dbProps;

    private ConnectionUtil() 
    {

        // Initialize the properties object to hold credentials
        dbProps = new Properties();

        // Stream the credentials from connection.properties file to this object

        // We need to break down it into a stream of information
        InputStream props = ConnectionUtil.class.getClassLoader().getResourceAsStream("connection.properties");

        try {
            dbProps.load(props);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
public static synchronized ConnectionUtil getConnectionUtil()
    {
        // first check if an instance does not already exists

        if(cu == null)
        {
            cu = new ConnectionUtil();
        }

        return cu;
    }

    // Method to actually establish a connection to the database


    public Connection getConnection()
    {
        Connection conn = null;

        // get our credentials from the connectionUtils properties object that we created in the constructor

        String url = dbProps.getProperty("url");

        String username = dbProps.getProperty("username");

        String password = dbProps.getProperty("password");

        // USE THOSE credentials and driver manager to connect to database

//        try {
//            Class.forName(dbProps.getProperty("driver"));
//        } catch (ClassNotFoundException e1) {
//            e1.printStackTrace();
//        }

        try 
        {
            DriverManager.setLoginTimeout(10);
            conn = DriverManager.getConnection(url,username,password);
            conn.setNetworkTimeout(null, 1000);
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }

        return conn;
    }
public static void main(String[] args)
    {
        Connection connection = getConnectionUtil().getConnection();

        if(connection != null)
        {
            System.out.println("Connection successful");
        } else
        {
            System.out.println("Connection unsuccessful");
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
