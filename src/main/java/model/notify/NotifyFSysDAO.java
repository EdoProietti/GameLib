package model.notify;

import model.user.Publisher;

import java.util.ArrayList;
import java.util.List;

public class NotifyFSysDAO extends NotifyDAO{

    @Override
    public List<Notify> getPublisherNotification(Publisher publisher) {
        // not implemented
        return new ArrayList<>();
    }

    @Override
    public void addNotify(Notify notify) {
        // not implemented
    }
}
