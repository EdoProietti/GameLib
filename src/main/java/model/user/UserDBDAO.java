package model.user;

import connection.ConnectionFactory;
import filePathClasses.PropertyPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

public class UserDBDAO extends UserDAO{

    private static final HashMap<String, User> list = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDBDAO.class);

    public UserDBDAO(){
        super();
    }

    @Override
    public User getUserByUsername(String username) {
        try(FileInputStream input = new FileInputStream(PropertyPath.getQueryPath())){
            User user = list.get(username);
            if(user != null){
                return user;
            }
            Properties prop = new Properties();
            prop.load(input);
            Connection conn = ConnectionFactory.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(prop.getProperty("GET_USER_BY_USERNAME"));
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if(UserType.toUserType(resultSet.getString(3)) == UserType.PUBLISHER){
                Publisher p = new Publisher(resultSet.getString(1), resultSet.getString(2));
                list.put(p.getUsername(), p);
                return p;
            } else{
                Buyer b = new Buyer(resultSet.getString(1), resultSet.getString(2));
                list.put(b.getUsername(), b);
                return b;
            }
        } catch(IOException | SQLException   e){
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void addUser(User user){
        try(FileInputStream input = new FileInputStream(PropertyPath.getQueryPath())){
            Properties prop = new Properties();
            prop.load(input);
            Connection conn = ConnectionFactory.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(prop.getProperty("ADD_NEW_USER"));
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getUserType().toString());
            preparedStatement.execute();
        }catch(IOException | SQLException e){
            LOGGER.error(e.getMessage());
        }
    }

}
