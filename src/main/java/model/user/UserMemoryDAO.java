package model.user;

import factory_dao.FactoryDAO;
import model.game.Game;
import model.game.Genre;
import model.game.Platform;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserMemoryDAO extends UserDAO{

    private static UserMemoryDAO instance;
    private final List<User> cache;

    private UserMemoryDAO(){
        this.cache = new ArrayList<>();
        Publisher p = new Publisher("Embark Studio", "12345");
        Game g = new Game("ARC Raiders", BigDecimal.valueOf(29.99), p, Genre.ACTION, Platform.PC);
        p.getCatalog().add(g);
        FactoryDAO.getInstance().createGameDAO().addGame(g);
        cache.add(new Buyer("Wyirdu", "12345"));
        cache.add(p);
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
            if(username.equals(u.getUsername())){
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
