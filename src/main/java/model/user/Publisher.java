package model.user;

import factory_dao.FactoryDAO;
import model.game.Game;
import model.notify.Notify;

import java.util.ArrayList;
import java.util.List;

public class Publisher extends User {
    private List<Game> catalog;
    private List<Notify> notification;

    public Publisher(String username, String password) {
        super(username, password, UserType.PUBLISHER);
        this.catalog = new  ArrayList<>();
        this.notification = new ArrayList<>();
    }

    public List<Game> getCatalog() {
        this.catalog = FactoryDAO.getInstance().createGameDAO().getPublisherGames(this);
        return catalog;
    }

    public List<Notify> getNotification() {
        this.notification = FactoryDAO.getInstance().createNotifyDAO().getPublisherNotification(this);
        return notification;
    }

    public void addNotification(Notify notify){
        this.notification.add(notify);
    }

    public void addGame(Game game){
        this.catalog.add(game);
    }
}