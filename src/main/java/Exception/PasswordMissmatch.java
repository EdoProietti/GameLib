package Exception;

public class PasswordMissmatch extends RuntimeException {
    private static final String message = "Le due password inserite non sono uguali";
    public PasswordMissmatch() {
        super(message);
    }
    public PasswordMissmatch(String message) {
        super(message);
    }

    public String getMessage() {
        return message;
    }
}
