package Exception;

public class PasswordNotEquals extends Exception {
    private static final String message = "Password errata.";
    public PasswordNotEquals() {
        super(message);
    }

    public String getMessage() {
        return message;
    }
}