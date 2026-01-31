package controller.logic;

import bean.UserBean;
import exception.UserNotFoundException;
import exception.UserFoundException;
import exception.PasswordNotEquals;
import factoryDAO.FactoryDAO;
import model.user.*;
import session.SessionManager;

public class AuthController {

    public void registerNewUser(UserBean userBean) throws UserFoundException{
        if(userExists(userBean)){
            throw new UserFoundException();
        }
        User user;
        if(userBean.getType().equals(UserType.PUBLISHER)){
            user = new Publisher(userBean.getUsername(), userBean.getPassword());
        } else {
            user = new Buyer(userBean.getUsername(), userBean.getPassword());
        }
        FactoryDAO.getInstance().createUserDAO().addUser(user);
    }

    public UserType loginUser(UserBean userBean) throws UserNotFoundException,  PasswordNotEquals {
        if(!userExists(userBean)){
            throw new UserNotFoundException();
        }
        User user = FactoryDAO.getInstance().createUserDAO().getUserByUsername(userBean.getUsername());
        if(user.getPassword().equals(userBean.getPassword())){
            SessionManager.getInstance().setLoggedUser(user);
        } else {
            throw new PasswordNotEquals();
        }
        if(user.getUserType().equals(UserType.PUBLISHER)){
            return  UserType.PUBLISHER;
        }
        return  UserType.BUYER;
    }

    public void logoutUser(){
        SessionManager.getInstance().freeLoggedUser();
    }

    private boolean userExists(UserBean userBean){
        UserDAO userDAO = FactoryDAO.getInstance().createUserDAO();
        return userDAO.getUserByUsername(userBean.getUsername()) != null;
    }

    public static User getLoggedUser(){
        return SessionManager.getInstance().getLoggedUser();
    }

    public static UserBean getLoggedUserBean(){
        User user = getLoggedUser();
        return new UserBean(user.getUsername(),  user.getPassword());
    }
}
