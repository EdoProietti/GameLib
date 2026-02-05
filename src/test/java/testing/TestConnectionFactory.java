package testing;

import org.junit.jupiter.api.Test;
import connection.ConnectionFactory;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class TestConnectionFactory {

    @Test
    void testGetInstanceNotNull(){
        int tester = 0;
        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        if(connectionFactory == null){
            tester = 1;
        }
        assertEquals(0, tester);
    }

    @Test
    void testGetConnectionNotNull(){
        int tester = 0;
        ConnectionFactory instance = ConnectionFactory.getInstance();
        Connection connection = instance.getConnection();
        if(connection == null){
            tester = 1;
        }
        assertEquals(0, tester);
    }
}
