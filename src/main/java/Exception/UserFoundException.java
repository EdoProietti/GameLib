package Exception;

public class UserFoundException extends RuntimeException {
    private static final String message = "Username inserito già esistente.";

    public UserFoundException() {
        super(message);
    }

    public String  getMessage() {
        return message;
    }
}
