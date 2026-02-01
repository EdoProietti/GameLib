package factory_dao;

import model.game.GameDAO;
import model.library.LibraryDAO;
import model.notify.NotifyDAO;
import model.user.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public abstract class FactoryDAO {

    private static FactoryDAO instance = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(FactoryDAO.class.getName());

    protected FactoryDAO(){}

    public static FactoryDAO getInstance(){

        if(instance == null){
            try(FileInputStream file = new FileInputStream("src/main/resources/utils/config.properties")){
                Properties prop = new Properties();
                prop.load(file);
                String conf = prop.getProperty("PERSISTENCY_TYPE");
                switch(conf) {
                    case "db":
                        instance = new DatabaseDAO();
                        break;
                    case "fileSystem":
                        instance = new FileSystemDAO();
                        break;
                    default:
                        instance = new MemoryDAO();
                        break;
                }
            }catch(IOException e){
                LOGGER.error(e.getMessage());
            }
        }
        return instance;
    }

    public abstract UserDAO createUserDAO();
    public abstract LibraryDAO createLibraryDAO();
    public abstract NotifyDAO createNotifyDAO();
    public abstract GameDAO createGameDAO();
}