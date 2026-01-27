package model.user;

public abstract class UserDAO {

    public abstract User getUserByUsername(String username);
    public abstract void addUser(User user);

}
