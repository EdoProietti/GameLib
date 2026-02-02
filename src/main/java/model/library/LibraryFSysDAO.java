package model.library;

import com.google.gson.Gson;
import file_path_classes.FileStorageConfig;
import model.game.Game;
import model.user.Buyer;

import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LibraryFSysDAO extends LibraryDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryFSysDAO.class);

    public LibraryFSysDAO() {
        super();
    }

    @Override
    public Library getLibrary(String username) {
        try(FileReader reader = new FileReader(FileStorageConfig.getBuyerFilePath().toFile())){
            Type type = new TypeToken<ArrayList<Buyer>>(){}.getType();
            List<Buyer> buyers = new Gson().fromJson(reader, type);
            if(buyers == null){
                return new Library();
            }
            for(Buyer buyer : buyers){
                if(buyer.getUsername().equals(username)){
                    return buyer.getLibrary();
                }
            }
            return new Library();
        }catch(IOException e){
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void addBuyerGame(Buyer buyer, Game game) {
        List<Buyer> buyers = getBuyers();
        List<Buyer> newBuyers = new ArrayList<>();
        if(buyers != null){
            for(Buyer buyer1 : buyers){
                if(!buyer1.getUsername().equals(buyer.getUsername())){
                    newBuyers.add(buyer1);
                }else{
                    newBuyers.add(buyer);
                }
            }
            try(FileWriter writer = new FileWriter(FileStorageConfig.getBuyerFilePath().toFile())){
                Gson gson = new Gson();
                gson.toJson(newBuyers, writer);
            } catch(IOException e){
                LOGGER.error(e.getMessage());
            }
        }
    }

    private List<Buyer> getBuyers(){
        try(FileReader reader = new FileReader(FileStorageConfig.getBuyerFilePath().toFile())){
            Type type = new TypeToken<ArrayList<Buyer>>(){}.getType();
            return new Gson().fromJson(reader, type);
        } catch(IOException e){
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }
}