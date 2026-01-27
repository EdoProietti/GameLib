package Model.Notify;

import Model.Game.Game;
import Model.User.Publisher;

import java.util.ArrayList;
import java.util.List;

public class NotifyMemoryDAO extends NotifyDAO{
    private static NotifyMemoryDAO instance;
    private final List<Notify> cache;

    private NotifyMemoryDAO(){
        cache = new ArrayList<>();
    }

    public static NotifyMemoryDAO getInstance(){
        if(instance == null){
            instance = new NotifyMemoryDAO();
        }
        return instance;
    }

    public List<Notify> getPublisherNotification(Publisher publisher){
        List<Notify> result = new ArrayList<>();
        for(Notify notify : cache){
            if(notify.getGame().getPublisher().equals(publisher)){
                result.add(notify);
            }
        }
        return result;
    }

    public void addNotify(Notify notify){
        for(Notify n: cache){
            if(notify.getGame().equals(n.getGame())){
                n.addCopySold();
                return;
            }
        }
        cache.add(notify);
    }

    // questo metodo viene utilizzato per aggiungere copie vendute.
    public Notify getNotify(Game game){
        for(Notify notify : cache){
            if(notify.getGame().equals(game)){
                return notify;
            }
        }
        return null;
    }
}