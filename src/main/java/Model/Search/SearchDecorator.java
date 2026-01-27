package Model.Search;

import Model.Game.Game;

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
