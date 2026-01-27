package model.search;

import model.game.Game;

public class DefaultFilter implements GameFilter {
    @Override
    public boolean check(Game game) {
        return true;
    }
}
