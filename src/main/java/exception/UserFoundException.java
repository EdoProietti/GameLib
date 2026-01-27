package exception;

public class UserFoundException extends Exception {
    private static final String MESSAGE = "Username inserito già esistente.";

    public UserFoundException() {
        super(MESSAGE);
    }

    @Override
    public String  getMessage() {
        return MESSAGE;
    }
}
