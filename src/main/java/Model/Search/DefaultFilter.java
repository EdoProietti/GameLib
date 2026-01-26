package Model.Search;

import Model.Game.Game;

public class DefaultFilter implements GameFilter {
    @Override
    public boolean check(Game game) {
        return true;
    }
}
