package factory_dao;

import model.game.GameDAO;
import model.game.GameMemoryDAO;
import model.library.LibraryDAO;
import model.library.LibraryMemoryDAO;
import model.notify.NotifyDAO;
import model.notify.NotifyMemoryDAO;
import model.user.UserDAO;
import model.user.UserMemoryDAO;

public class MemoryDAO extends FactoryDAO {

    public MemoryDAO() {
        super();
    }

    @Override
    public UserDAO createUserDAO() {
        return UserMemoryDAO.getInstance();
    }

    @Override
    public LibraryDAO createLibraryDAO() {
        return LibraryMemoryDAO.getInstance();
    }

    @Override
    public GameDAO createGameDAO() {
        return GameMemoryDAO.getInstance();
    }

    @Override
    public NotifyDAO createNotifyDAO() {
        return NotifyMemoryDAO.getInstance();
    }
}
