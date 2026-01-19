package Model;

import java.util.ArrayList;

public class Publisher extends User{
    private ArrayList<Game> catalog;
    private ArrayList<Notify> notification;

    protected Publisher(String username, UserType type) {
        super(username, type);
    }


}
