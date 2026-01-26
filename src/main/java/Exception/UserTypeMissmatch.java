package Exception;

public class UserTypeMissmatch extends Exception {
    private static final String message = "Utente non autorizzato per questa operazione.";
    public UserTypeMissmatch(){}
    public UserTypeMissmatch(String message) {
        super(message);
    }

    public String getMessage(){
        return message;
    }
}
