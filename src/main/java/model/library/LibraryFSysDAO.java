package model.library;

import com.google.gson.Gson;
import filePathClasses.FileStorageConfig;
import model.game.Game;
import model.user.Buyer;

import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LibraryFSysDAO extends LibraryDAO {

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
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void addBuyerGame(Buyer buyer, Game game) {

    }
}
