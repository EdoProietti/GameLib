package Model.Search;

import Model.Game.Game;

import java.math.BigDecimal;

public class PriceDecorator extends SearchDecorator {
    private final BigDecimal price;

    public PriceDecorator(GameFilter decoratedFilter, BigDecimal price) {
        super(decoratedFilter);
        this.price = price;
    }

    @Override
    public boolean check(Game game){
        return super.check(game) &&
                game.getPrice().compareTo(price) < 0;
    }
}
