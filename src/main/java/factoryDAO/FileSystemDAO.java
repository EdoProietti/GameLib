package factoryDAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import filePathClasses.FileStorageConfig;
import model.game.GameDAO;
import model.library.Library;
import model.library.LibraryDAO;
import model.library.LibraryFSysDAO;
import model.notify.NotifyDAO;
import model.user.Buyer;
import model.user.Publisher;
import model.user.UserDAO;
import model.user.UserFSysDAO;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileSystemDAO extends FactoryDAO {

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
        return null;
    }

    @Override
    public LibraryDAO createLibraryDAO() {
        return new LibraryFSysDAO();
    }

    @Override
    public NotifyDAO createNotifyDAO() {
        return null;
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
            System.out.println(e.getMessage());
        }
    }
}
