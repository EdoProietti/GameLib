package Exception;

public class PasswordNotEquals extends Exception {
    private static final String MESSAGE = "Password errata.";
    public PasswordNotEquals() {
        super(MESSAGE);
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}