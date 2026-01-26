package Exception;

public class UserNotLogged extends Exception {
    private static final String message = "Questa funzione ha bisogno del login.";

    public UserNotLogged(String message) {
        super(message);
    }

    public UserNotLogged(){}

    public String getMessage(){
        return message;
    }
}
