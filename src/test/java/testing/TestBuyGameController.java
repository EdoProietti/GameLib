package testing;

import bean.GameBean;
import controller.logic.BuyGameController;
import exception.UserNotLogged;
import exception.UserTypeMissmatch;
import factory_dao.FactoryDAO;
import factory_dao.MemoryDAO;
import model.game.Game;
import model.game.Genre;
import model.game.Platform;
import model.user.Buyer;
import model.user.Publisher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import session.SessionManager;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TestBuyGameController {

    private static Game g;
    private static Buyer b;

    @BeforeAll
    static void beforeAll() {
        FactoryDAO factoryDAO = new MemoryDAO();
        Publisher p = new Publisher("username", "password");
        Game game = new Game("title", BigDecimal.valueOf(20.0), p, Genre.FPS, Platform.PC);
        p.addGame(game);
        FactoryDAO.setInstance(factoryDAO);
        factoryDAO.createUserDAO().addUser(p);
        factoryDAO.createGameDAO().addGame(game);
        g = game;
        b = new Buyer("username1", "password1");
        SessionManager.getInstance().setLoggedUser(b);
        factoryDAO.createUserDAO().addUser(b);
    }

    @Test
     void testAddGameToCartGameFound(){
        int test = 0;
        GameBean gameBean = new GameBean();
        gameBean.setTitle("title");
        gameBean.setPublisher("username");
        BuyGameController bc = new BuyGameController();
        try {
            bc.addGameToCart(gameBean);
            if(!b.getCart().isInCart(g)){
                test = 1;
            }
            assertEquals(0, test);
        } catch (UserNotLogged e) {
            fail("User should be logged.");
        } catch (UserTypeMissmatch e) {
            fail("User type should be buyer");
        }
    }

    @Test
     void testAddGameToCartGameNotFound(){
        int test = 0;
        GameBean gameBean = new GameBean();
        gameBean.setTitle("title1");
        gameBean.setPublisher("username1");
        BuyGameController bc = new BuyGameController();
        try {
            bc.addGameToCart(gameBean);
            List<Game> items = b.getCart().getItems();
            if(!items.isEmpty()){
                for(Game game : items){
                    if(game.getTitle().equals(gameBean.getTitle())){
                        test = 1;
                        break;
                    }
                }
            }
            assertEquals(0, test);
        } catch (UserNotLogged e) {
            fail("User should be logged.");
        } catch (UserTypeMissmatch e) {
            fail("User type should be buyer");
        }
    }
}