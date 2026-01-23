package Model.User;

public abstract class User {
    private String username;
    private String password;
    private UserType type;

    protected User(String username, UserType type){
        this.username = username;
        this.type = type;
    }

    public String getUsename(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public UserType getUserType(){
        return type;
    }

    public void setUserType(UserType type){
        this.type = type;
    }
}