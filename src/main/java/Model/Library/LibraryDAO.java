package Model.Library;

import Model.Game.Game;
import Model.User.Buyer;

public abstract class LibraryDAO {

    public abstract Library getLibrary(String username);
    public abstract void addBuyerGame(Buyer buyer, Game game);
}
