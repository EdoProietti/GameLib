package model.library;

import model.game.Game;
import model.user.Buyer;

import java.util.HashMap;

public class LibraryMemoryDAO extends LibraryDAO{
    private static LibraryMemoryDAO instance;
    private final HashMap<String, Library> cache;

    private LibraryMemoryDAO(){
        cache = new HashMap<>();
    }

    public static LibraryMemoryDAO getInstance(){
        if(instance == null){
            instance = new LibraryMemoryDAO();
        }
        return instance;
    }

    @Override
    public Library getLibrary(String username){
        if(cache.get(username) == null){
            Library library = new Library();
            cache.put(username, library);
        }
        return cache.get(username);
    }

    @Override
    public void addBuyerGame(Buyer buyer, Game game) {
        Library library = getLibrary(buyer.getUsername());
        for(Game g: library.getGames()){
            if(g.equals(game)){
                return;
            }
        }
        library.addGame(game);
    }
}
