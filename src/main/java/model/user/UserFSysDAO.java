package model.user;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import file_path_classes.FileStorageConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserFSysDAO extends UserDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserFSysDAO.class);
    private final File fileBuyer = FileStorageConfig.getBuyerFilePath().toFile();
    private final File filePublisher = FileStorageConfig.getPublisherFilePath().toFile();

    @Override
    public User getUserByUsername(String username) {
        User u = checkBuyerFile(username);
        if(u == null){
            u = checkPublisherFile(username);
        }
        return u;
    }

    @Override
    public void addUser(User user) {
        Gson gson = new Gson();
        if(user.getUserType() == UserType.BUYER){
            List<Buyer> buyers = getAllBuyers();
            try(FileWriter writer = new FileWriter(fileBuyer)){
                buyers.add((Buyer) user);
                gson.toJson(buyers, writer);
            }catch(IOException e){
                LOGGER.error(e.getMessage());
            }
        }else{
            List<Publisher> publishers = getAllPublishers();
            try(FileWriter writer = new FileWriter(filePublisher)){
                publishers.add((Publisher) user);
                gson.toJson(publishers, writer);
            }catch(IOException e){
                LOGGER.error(e.getMessage());
            }
        }
    }

    private User checkPublisherFile(String username){
        try(FileReader reader = new FileReader(filePublisher)){
            // typeToken serve a specificare alla libreria gson che deve prendere una lista di publisher
            Type type = new TypeToken<ArrayList<Publisher>>(){}.getType();
            Gson gson = new Gson();
            List<Publisher> publishers = gson.fromJson(reader, type);
            for(Publisher p: publishers){
                if(p.getUsername().equals(username)){
                    return p;
                }
            }
        }catch(IOException e){
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    private User checkBuyerFile(String username){
        try(FileReader reader = new FileReader(fileBuyer)){
            Type type = new TypeToken<ArrayList<Buyer>>(){}.getType();
            Gson gson = new Gson();
            List<Buyer> buyers = gson.fromJson(reader, type);
            for(Buyer b: buyers){
                if(b.getUsername().equals(username)){
                    return b;
                }
            }
        }catch(IOException e){
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    private List<Buyer> getAllBuyers(){
        try(FileReader reader = new FileReader(fileBuyer)){
            Type type = new TypeToken<ArrayList<Buyer>>(){}.getType();
            Gson gson = new Gson();
            return gson.fromJson(reader, type);
        }catch(IOException e){
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    private List<Publisher> getAllPublishers(){
        try(FileReader reader = new FileReader(filePublisher)){
            Type type = new TypeToken<ArrayList<Publisher>>(){}.getType();
            Gson gson = new Gson();
            return gson.fromJson(reader, type);
        }catch(IOException e){
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }
}
