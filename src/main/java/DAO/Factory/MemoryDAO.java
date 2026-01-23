package DAO.Factory;

import Model.Game.GameDAO;
import Model.Library.LibraryDAO;
import Model.Notify.NotifyDAO;
import Model.User.UserDAO;
import Model.User.UserMemoryDAO;

public class MemoryDAO extends FactoryDAO {

    @Override
    public UserDAO createUserDAO() {
        return UserMemoryDAO.getInstance();
    }

    @Override
    public LibraryDAO createLibraryDAO() {
        return null;
    }

    @Override
    public GameDAO createGameDAO() {
        return null;
    }

    @Override
    public NotifyDAO createNotifyDAO() {
        return null;
    }
}
