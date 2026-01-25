package Bean;

import Model.User.UserType;
import Exception.PasswordMissmatch;

public class UserBean {

    private String username;
    private String password;
    private String confirmPassword;
    private UserType type;

    public UserBean(String username, String password,   String confirmPassword, UserType type) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.type = type;
    }

    public UserBean(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public UserType getType(){
        return this.type;
    }

    public String getConfirmPassword(){
        return this.confirmPassword;
    }

    public void passwordAreEqual() throws PasswordMissmatch {
        if(!this.password.equals(this.confirmPassword)){
            throw new PasswordMissmatch();
        }
    }
}
