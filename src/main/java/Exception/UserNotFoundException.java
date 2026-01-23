package Exception;

public class UserNotFoundException extends RuntimeException {

    private static String message = "Utente non trovato";

    public UserNotFoundException() {
        super(message);
    }

}
