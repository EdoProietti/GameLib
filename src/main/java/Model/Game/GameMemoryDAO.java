package Model.Game;

import Model.User.Publisher;

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

    @Override
    public void addGame(Game game){
        this.cache.add(game);
    }

    public void removeGame(Game game){
        this.cache.remove(game);
    }

    @Override
    public Game getGame(String title, String publisher){
        for(Game game: this.cache){
            if(game.getTitle().equals(title) && game.getPublisher().getUsername().equals(publisher)){
                return game;
            }
        }
        return null;
    }

    @Override
    public List<Game> getGames(String title){
        List<Game> games = new ArrayList<>();
        for(Game game: this.cache){
            if(game.getTitle().equalsIgnoreCase(title)){
                games.add(game);
            }
        }
        return games;
    }

    @Override
    public List<Game> getPublisherGames(Publisher publisher){
        List<Game> games = new ArrayList<>();
        for(Game g:this.cache){
            if(g.getPublisher().getUsername().equals(publisher.getUsername())){
                games.add(g);
            }
        }
        return games;
    }
}