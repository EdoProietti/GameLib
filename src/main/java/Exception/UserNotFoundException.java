package Exception;

public class UserNotFoundException extends Exception {

    private static final String message = "Utente non trovato";

    public UserNotFoundException() {
        super(message);
    }

    public String getMessage() {
        return message;
    }

}
