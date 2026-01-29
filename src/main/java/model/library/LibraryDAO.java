package model.library;

import model.game.Game;
import model.user.Buyer;

public abstract class LibraryDAO {
    public abstract Library getLibrary(String username);
    public abstract void addBuyerGame(Buyer buyer, Game game);
}
