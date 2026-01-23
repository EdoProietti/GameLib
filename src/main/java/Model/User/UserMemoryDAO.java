package Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserMemoryDAO extends UserDAO{

    private static UserMemoryDAO instance;
    private final List<User> cache;

    private UserMemoryDAO(){
        this.cache = new ArrayList<>();
    }

    public static UserMemoryDAO getInstance(){
        if(instance == null){
            instance = new UserMemoryDAO();
        }
        return instance;
    }

    @Override
    public User getUserByUsername(String username) {
        for(User u : cache){
            if(username.equals(u.getUsename())){
                return u;
            }
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        cache.add(user);
    }
}
