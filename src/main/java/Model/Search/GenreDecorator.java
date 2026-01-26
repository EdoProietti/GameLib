package Model.Search;

import Model.Game.Game;
import Model.Game.Genre;

public class GenreDecorator extends SearchDecorator {
    private final Genre genre;

    public GenreDecorator(GameFilter searchDecorator, Genre genre) {
        super(searchDecorator);
        this.genre = genre;
    }

    @Override
    public boolean check(Game game){
        return super.check(game) &&
                game.getGenre().equals(genre);
    }
}