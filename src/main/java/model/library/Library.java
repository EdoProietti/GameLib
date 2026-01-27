package model.library;

import model.game.Game;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final ArrayList<Game> libraryGames;

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

    public List<Game> getGames(){
        return this.libraryGames;
    }
}
