package model.game;

import model.user.Publisher;

import java.util.ArrayList;
import java.util.List;

public class GameFSysDAO extends GameDAO{

    @Override
    public Game getGame(String title, String publisher) {
        // not implemented
        return null;
    }

    @Override
    public List<Game> getGames(String title) {
        // not implemented
        return new ArrayList<>();
    }

    @Override
    public void addGame(Game game) {
        // not implemented
    }

    @Override
    public List<Game> getPublisherGames(Publisher publisher) {
        // not implemented
        return new ArrayList<>();
    }
}
