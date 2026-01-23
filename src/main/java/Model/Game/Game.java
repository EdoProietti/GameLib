package Model.Game;

import Model.User.Publisher;

import java.math.BigDecimal;

public class Game {
    private String title;
    private BigDecimal price;
    private Publisher publisher;
    private Platform platform;
    private Genre genre;

    public Game(String title, BigDecimal price, Publisher publisher, Genre genre, Platform platform){
        this.title = title;
        this.price = price;
        this.publisher = publisher;
        this.platform = platform;
        this.genre = genre;
    }

    public void setPlatform(Platform platform){this.platform = platform;}

    public Platform getPlatform(){return this.platform;}

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public BigDecimal getPrice(){
        return this.price;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public Publisher getPublisher(){
        return this.publisher;
    }

    public void setPublisher(Publisher publisher){
        this.publisher = publisher;
    }

    public Genre getGenre(){return this.genre;}

    public void setGenre(Genre genre){this.genre = genre;}
}
