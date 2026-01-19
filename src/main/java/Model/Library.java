package Model;

import java.util.ArrayList;

public class Library {
    private ArrayList<Game> libraryGames;

    public Library(){
        this.libraryGames = new ArrayList<Game>();
    }

    public void addGame(Game game){
        this.libraryGames.add(game);
    }

    public ArrayList<Game> getLibraryGames() {
        return libraryGames;
    }

    public boolean isInLibrary(Game game){
        if(this.libraryGames.isEmpty()){
            return false;
        }
        for(Game g: this.libraryGames){
            if(game.equals(g)){
                return true;
            }
        }
        return false;
    }
}
