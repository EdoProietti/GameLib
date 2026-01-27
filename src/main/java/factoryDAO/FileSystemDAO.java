package factoryDAO;

import model.game.GameDAO;
import model.library.LibraryDAO;
import model.notify.NotifyDAO;
import model.user.UserDAO;

public class FileSystemDAO extends FactoryDAO {

    @Override
    public UserDAO createUserDAO() {
        return null;
    }

    @Override
    public GameDAO createGameDAO() {
        return null;
    }

    @Override
    public LibraryDAO createLibraryDAO() {
        return null;
    }

    @Override
    public NotifyDAO createNotifyDAO() {
        return null;
    }
}
