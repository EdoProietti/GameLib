package factoryDAO;

import model.game.GameDAO;
import model.library.LibraryDAO;
import model.notify.NotifyDAO;
import model.user.UserDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class FactoryDAO {

    private static FactoryDAO instance = null;

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
                System.out.println("File non trovato: " + e.getMessage());
            }
        }
        return instance;
    }

    public abstract UserDAO createUserDAO();
    public abstract LibraryDAO createLibraryDAO();
    public abstract NotifyDAO createNotifyDAO();
    public abstract GameDAO createGameDAO();
}