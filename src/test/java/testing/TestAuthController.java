package testing;

import bean.UserBean;
import controller.logic.AuthController;
import exception.PasswordNotEquals;
import exception.UserNotFoundException;
import factory_dao.FactoryDAO;
import factory_dao.MemoryDAO;
import model.user.Buyer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import session.SessionManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAuthController {

    private static Buyer b;
    private static MemoryDAO m;


    @BeforeEach
    void setUp(){
        m = new MemoryDAO();
        b = new Buyer("username", "password");
        FactoryDAO.setInstance(m);
        m.createUserDAO().addUser(b);
    }

    @Test
    void testLoginUserAuthSuccess() {
        int test = 0;
        AuthController authController = new AuthController();
        try {
            authController.loginUser(new UserBean("username", "password"));
            if(SessionManager.getInstance().getLoggedUser() ==  null){
                test = 1;
            }
        } catch (UserNotFoundException | PasswordNotEquals e) {
            test = 1;
        }
        assertEquals(0, test);
    }

    @Test
    void testLoginUserAuthFail() {
        int test = 0;
        AuthController authController = new AuthController();
        try{
            authController.loginUser(new UserBean("username", "p"));
            if(SessionManager.getInstance().getLoggedUser() != null){
                test = 1;
            }
            assertEquals(0, test);
        }catch(UserNotFoundException | PasswordNotEquals e){
            assertEquals(0, test);
        }
    }
}
