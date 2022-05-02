package com.lab9C;


import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * A class that implements the singleton pattern responsible with making sure that there exists only one connection to the database for the run time of the program
 * It saves as static final fields the settings required to create a data source that are saved in a file called database.properties and when the needed it extracts the value from the file and creates the data source
 */
public class SingletonDataSource {

    private static final String databaseUsername="db.username";
    private static final String databasePassword="db.password";
    private static final String databaseUrl="db.url";
    private static final String databaseDriverClass="driver.class.name";

    private static Connection connection;

    static {
        try {
            //load data source options
            Properties properties = new Properties();
            properties.load( new FileInputStream( "src/database.properties" ) );

            //create and set the data source parameters
            BasicDataSource dataSource = new BasicDataSource();

            dataSource.setUsername( properties.getProperty( databaseUsername ) );
            dataSource.setPassword( properties.getProperty( databasePassword ) );
            dataSource.setUrl( properties.getProperty( databaseUrl ) );
            dataSource.setDriverClassName( properties.getProperty( databaseDriverClass ) );

            connection= dataSource.getConnection();

            connection.setAutoCommit( false );

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the current connection to the data source
     * @return the connection to the data source
     * @throws SQLException thrown if any sql exceptions occur
     */
    public static Connection getSingleConnection() throws SQLException {
        return connection;
    }
}
