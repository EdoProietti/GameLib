package model.notify;

import model.user.Publisher;

import java.util.List;

public abstract class NotifyDAO {
    public abstract List<Notify> getPublisherNotification(Publisher publisher);
    public abstract void addNotify(Notify notify);
}
