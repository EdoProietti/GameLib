package exception;

public class UserTypeMissmatch extends Exception {
    private static final String MESSAGE = "Utente non autorizzato per questa operazione.";
    public UserTypeMissmatch(){}
    public UserTypeMissmatch(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return MESSAGE;
    }
}
