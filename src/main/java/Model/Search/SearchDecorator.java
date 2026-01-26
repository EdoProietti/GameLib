package Model.Search;

import Model.Game.Game;

public abstract class SearchDecorator implements GameFilter{
    protected GameFilter searchDecorator;

    public SearchDecorator(GameFilter searchDecorator) {
        this.searchDecorator = searchDecorator;
    }

    @Override
    public boolean check(Game game){
        return searchDecorator.check(game);
    }
}
