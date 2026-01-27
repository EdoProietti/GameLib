package model.search;

import model.game.Game;
import model.game.Platform;

public class PlatformDecorator extends SearchDecorator{
    private final Platform platform;

    public PlatformDecorator(GameFilter searchDecorator, Platform platform) {
        super(searchDecorator);
        this.platform = platform;
    }

    @Override
    public boolean check(Game game){
        return super.check(game) &&
                game.getPlatform().equals(platform);
    }
}
