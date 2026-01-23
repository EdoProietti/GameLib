package Model.Notify;

import java.util.ArrayList;
import java.util.List;

public class NotifyMemoryDAO extends NotifyDAO{
    private static NotifyMemoryDAO instance;
    private List<Notify> cache;

    private NotifyMemoryDAO(){
        cache = new ArrayList<>();
    }

    public static NotifyMemoryDAO getInstance(){
        if(instance == null){
            instance = new NotifyMemoryDAO();
        }
        return instance;
    }
}