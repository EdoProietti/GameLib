package model.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import filePathClasses.FileStorageConfig;

public class UserFSysDAO extends UserDAO{

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
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
        if(user.getUserType() == UserType.BUYER){
            try(FileWriter writer = new FileWriter(fileBuyer)){
                Buyer buyer = (Buyer) user;
                gson.toJson(buyer, writer);
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }else{
            try(FileWriter writer = new FileWriter(filePublisher)){
                Publisher publisher = (Publisher) user;
                gson.toJson(publisher, writer);
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private User checkPublisherFile(String username){
        try(FileReader reader = new FileReader(filePublisher)){
            // typeToken serve a specificare alla libreria gson che deve prendere una lista di publisher
            Type type = new TypeToken<Publisher>(){}.getType();
            List<Publisher> publishers = gson.fromJson(reader, type);
            for(Publisher p: publishers){
                if(p.getUsername().equals(username)){
                    return p;
                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private User checkBuyerFile(String username){
        try(FileReader reader = new FileReader(fileBuyer)){
            Type type = new TypeToken<Buyer>(){}.getType();
            List<Buyer> buyers = gson.fromJson(reader, type);
            for(Buyer b: buyers){
                if(b.getUsername().equals(username)){
                    return b;
                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
