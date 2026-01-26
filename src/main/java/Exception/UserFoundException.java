package Exception;

public class UserFoundException extends Exception {
    private static final String message = "Username inserito già esistente.";

    public UserFoundException() {
        super(message);
    }

    public String  getMessage() {
        return message;
    }
}
