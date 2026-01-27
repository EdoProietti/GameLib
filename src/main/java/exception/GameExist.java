package exception;

public class GameExist extends Exception {
    public GameExist() {}
    public GameExist(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return "Il gioco inserito è già presente.";
    }
}
