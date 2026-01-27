package model.game;

public enum Genre {
    ACTION,
    RPG,
    FPS,
    SPORT;

    public static Genre toGenre(String genre){
        for(Genre g : Genre.values()){
            if(g.toString().equalsIgnoreCase(genre)){
                return g;
            }
        }
        return null;
    }
}
