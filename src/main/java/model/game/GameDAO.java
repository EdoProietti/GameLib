package model.game;

import model.user.Publisher;

import java.util.List;

public abstract class GameDAO {
    public abstract Game getGame(String title, String publisher);
    public abstract List<Game> getGames(String title);
    public abstract void addGame(Game game);
    public abstract List<Game> getPublisherGames(Publisher publisher);
}
