package FactoryDAO;

import Model.Game.GameDAO;
import Model.Library.LibraryDAO;
import Model.Notify.NotifyDAO;
import Model.User.UserDAO;

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
