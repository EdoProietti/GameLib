package Controller.Logic;

import Bean.UserBean;
import Exception.UserNotFoundException;

public class AuthController {

    public boolean passwordEquals(UserBean userBean){
        if(userBean.getPassword().equals(userBean.getConfirmPassword())){
            return true;
        }
        return false;
    }

    public void addNewUser(UserBean userBean){
        if(userExists(userBean)){
            // crea l'utente e registra nel dao, aggiunge l'utente nel session manager.
        }

    }

    public void loginUser(UserBean userBean) throws UserNotFoundException {
        if(!userExists(userBean)){
            throw new UserNotFoundException();
        }
    }

    private boolean userExists(UserBean userBean){

        // cerchiamo utenti con lo stesso nome

        return false;
    }



}
