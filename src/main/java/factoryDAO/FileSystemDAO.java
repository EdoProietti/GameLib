package factoryDAO;

import model.game.GameDAO;
import model.library.LibraryDAO;
import model.notify.NotifyDAO;
import model.user.UserDAO;
import model.user.UserFSysDAO;

public class FileSystemDAO extends FactoryDAO {

    @Override
    public UserDAO createUserDAO() {
        return new UserFSysDAO();
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
