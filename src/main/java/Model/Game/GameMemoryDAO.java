package Model.Game;

import java.util.ArrayList;
import java.util.List;

public class GameMemoryDAO extends  GameDAO{
    private static GameMemoryDAO instance;
    private final List<Game> cache;

    private GameMemoryDAO(){
        this.cache = new ArrayList<>();
    }

    public static GameMemoryDAO getInstance(){
        if(instance == null){
            instance = new GameMemoryDAO();
        }
        return instance;
    }

    public void addGame(Game game){
        this.cache.add(game);
    }

    public void removeGame(Game game){
        this.cache.remove(game);
    }

    public List<Game> getGameByTitle(String title){
        ArrayList<Game> games = new ArrayList<>();
        for(Game game: this.cache){
            if(game.getTitle().equals(title)){
                games.add(game);
            }
        }
        return games;
    }
}
