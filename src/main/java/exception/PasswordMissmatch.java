package exception;

public class PasswordMissmatch extends Exception {
    private static final String MESSAGE = "Le due password inserite non sono uguali";
    public PasswordMissmatch() {
        super(MESSAGE);
    }
    public PasswordMissmatch(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
