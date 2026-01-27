package model.search;

import model.game.Game;

public interface GameFilter {
    boolean check(Game game);
}
