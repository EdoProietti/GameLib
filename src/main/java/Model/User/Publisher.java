package Model.User;

import Model.Game.Game;
import Model.Notify.Notify;

import java.util.ArrayList;
import java.util.List;

public class Publisher extends User {
    private List<Game> catalog;
    private List<Notify> notification;

    public Publisher(String username, String password) {
        super(username, password, UserType.PUBLISHER);
    }

}
