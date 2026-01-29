package factoryDAO;

import model.game.GameDAO;
import model.game.GameDBDAO;
import model.library.LibraryDAO;
import model.library.LibraryDBDAO;
import model.notify.NotifyDAO;
import model.notify.NotifyDBDAO;
import model.user.UserDAO;
import model.user.UserDBDAO;

public class DatabaseDAO extends FactoryDAO {

    @Override
    public UserDAO createUserDAO() {
        return new UserDBDAO();
    }

    @Override
    public NotifyDAO createNotifyDAO() {
        return new NotifyDBDAO();
    }

    @Override
    public LibraryDAO createLibraryDAO() {
        return new LibraryDBDAO();
    }

    @Override
    public GameDAO createGameDAO() {
        return new GameDBDAO();
    }
}
