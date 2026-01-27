package Exception;

public class GameExist extends Exception {
    public GameExist() {}
    public GameExist(String message) {}

    @Override
    public String getMessage(){
        return "Il gioco inserito è già presente.";
    }
}
