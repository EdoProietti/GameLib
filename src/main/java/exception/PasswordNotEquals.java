package exception;

public class PasswordNotEquals extends Exception {
    private static final String MESSAGE = "Credenziali errate";
    public PasswordNotEquals() {
        super(MESSAGE);
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}