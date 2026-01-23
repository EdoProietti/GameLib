package Session;

import Model.User.User;

public class SessionManager {
    private static SessionManager instance;
    private User loggedUser;

    private SessionManager(){}

    public static SessionManager getInstance(){
        if(instance == null){
            instance = new SessionManager();
        }
        return instance;
    }

    public User getLoggedUser(){
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public void freeLoggedUser(){
        this.loggedUser = null;
    }
}
