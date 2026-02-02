package connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionFactory instance;
    private Connection connection;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionFactory.class);

    private ConnectionFactory() {
        this.connection = null;
    }

    public static ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            try (InputStream input = new FileInputStream("src/main/resources/utils/connection.properties")) {
                Properties properties = new Properties();
                properties.load(input);
                String connectionUrl = properties.getProperty("CONNECTION_URL");
                String user = properties.getProperty("USER");
                String pass = properties.getProperty("PASS");
                connection = DriverManager.getConnection(connectionUrl, user, pass);
            } catch (IOException | SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return connection;
    }

}
