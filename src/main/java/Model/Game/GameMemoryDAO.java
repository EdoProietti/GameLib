package Model.Game;

import java.util.ArrayList;
import java.util.List;

public class GameMemoryDAO extends  GameDAO{
    private static GameMemoryDAO instance;
    private List<Game> cache;

    private GameMemoryDAO(){
        this.cache = new ArrayList<>();
    }

    public GameMemoryDAO getInstance(){
        if(instance == null){
            instance = new GameMemoryDAO();
        }
        return instance;
    }
}
