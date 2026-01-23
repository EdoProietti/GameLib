package Bean;

import Model.User.UserType;

public class UserBean {

    private String username;
    private String password;
    private String confirmPassword;
    private UserType type;

    public UserBean(String username, String password, UserType type){
        this.username = username;
        this.password = password;
        this.type = type;
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
}
