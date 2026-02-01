package factory_dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import file_path_classes.FileStorageConfig;
import model.game.GameDAO;
import model.game.GameFSysDAO;
import model.library.Library;
import model.library.LibraryDAO;
import model.library.LibraryFSysDAO;
import model.notify.NotifyDAO;
import model.notify.NotifyFSysDAO;
import model.user.Buyer;
import model.user.Publisher;
import model.user.UserDAO;
import model.user.UserFSysDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileSystemDAO extends FactoryDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemDAO.class);

    public FileSystemDAO(){
        super();
        initializeValues();
    }

    @Override
    public UserDAO createUserDAO() {
        return new UserFSysDAO();
    }

    @Override
    public GameDAO createGameDAO() {
        return new GameFSysDAO();
    }

    @Override
    public LibraryDAO createLibraryDAO() {
        return new LibraryFSysDAO();
    }

    @Override
    public NotifyDAO createNotifyDAO() {
        return new NotifyFSysDAO();
    }

    private void initializeValues(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path buyerFilePath = FileStorageConfig.getBuyerFilePath();
        Path publisherFilePath = FileStorageConfig.getPublisherFilePath();
        try{
            if(Files.notExists(buyerFilePath) || Files.size(buyerFilePath) == 0){
                List<Buyer> startBuyerList = new ArrayList<>();
                startBuyerList.add(new Buyer("Prova", "Prova", new Library()));
                try(FileWriter fw = new FileWriter(buyerFilePath.toFile())){
                    gson.toJson(startBuyerList, fw);
                }
            }
            if(Files.notExists(publisherFilePath) || Files.size(publisherFilePath) == 0){
                List<Publisher> startList = new ArrayList<>();
                startList.add(new Publisher("Ciao", "ciao1"));
                try(FileWriter fw = new FileWriter(publisherFilePath.toFile())){
                    gson.toJson(startList, fw);
                }
            }
        }catch(IOException e){
            LOGGER.error(e.getMessage());
        }
    }
}
