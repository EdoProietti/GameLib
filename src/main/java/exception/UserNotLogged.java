package exception;

public class UserNotLogged extends Exception {
    private static final String MESSAGE = "Questa funzione ha bisogno del login.";

    public UserNotLogged(String message) {
        super(message);
    }

    public UserNotLogged(){}

    @Override
    public String getMessage(){
        return MESSAGE;
    }
}
