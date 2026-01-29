package model.notify;

import model.game.Game;

public class Notify {
    // l'attributo game è di tipo final perché quando un publisher segna come letto la notifica
    // la notifica viene tolta dalla lista delle notifiche del publisher.
    private final Game game;
    private int sold;

    public Notify(Game game){
        this.game = game;
        this.sold = 1;
    }

    public void addCopySold(){
        this.sold++;
    }

    public void setCopySold(int sold){
        this.sold = sold;
    }

    public int getSold(){
        return this.sold;
    }

    public Game getGame(){
        return this.game;
    }
}