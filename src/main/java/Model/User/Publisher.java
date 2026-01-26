package Model.User;

import Model.Game.Game;
import Model.Notify.Notify;

import java.util.ArrayList;
import java.util.List;

public class Publisher extends User {
    private final List<Game> catalog;
    private final List<Notify> notification;

    public Publisher(String username, String password) {
        super(username, password, UserType.PUBLISHER);
        this.catalog = new  ArrayList<>();
        this.notification = new ArrayList<>();
    }

    public List<Game> getCatalog() {
        return catalog;
    }

    public List<Notify> getNotification() {
        return notification;
    }

    public boolean notificationCreated(Game game){
        for(Notify notify : this.notification){
            if(notify.getGame().equals(game)){
                return true;
            }
        }
        return false;
    }
}
