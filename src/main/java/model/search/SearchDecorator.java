package model.search;

import model.game.Game;

public abstract class SearchDecorator implements GameFilter{
    protected GameFilter decorator;

    protected SearchDecorator(GameFilter searchDecorator) {
        this.decorator = searchDecorator;
    }

    @Override
    public boolean check(Game game){
        return decorator.check(game);
    }
}
