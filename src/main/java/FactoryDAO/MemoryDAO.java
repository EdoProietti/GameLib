package FactoryDAO;

import Model.Game.GameDAO;
import Model.Game.GameMemoryDAO;
import Model.Library.LibraryDAO;
import Model.Library.LibraryMemoryDAO;
import Model.Notify.NotifyDAO;
import Model.Notify.NotifyMemoryDAO;
import Model.User.UserDAO;
import Model.User.UserMemoryDAO;

public class MemoryDAO extends FactoryDAO {

    protected MemoryDAO() {
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
