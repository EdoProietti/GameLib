package Model.Notify;

import Model.User.Publisher;

import java.util.List;

public abstract class NotifyDAO {
    public abstract List<Notify> getPublisherNotification(Publisher publisher);
    public abstract void addNotify(Notify notify);
}
