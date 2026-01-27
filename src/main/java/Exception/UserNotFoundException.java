package Exception;

public class UserNotFoundException extends Exception {

    private static final String MESSAGE = "Utente non trovato";

    public UserNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
