package Model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Game {
    private String title;
    private BigDecimal price;
    private Publisher publisher;
    private ArrayList<Platform> platforms;
    private Genre genre;

    public Game(String title, BigDecimal price, Publisher publisher, Genre genre){
        this.title = title;
        this.price = price;
        this.publisher = publisher;
        this.platforms = new ArrayList<Platform>();
        this.genre = genre;
    }

    public void addPlatform(Platform platform){
        if(this.platforms.isEmpty()){
            this.platforms.add(platform);
        }
        else{
            for(Platform pl : this.platforms){
                if(!pl.equals(platform)){
                    this.platforms.add(platform);
                    break;
                }
            }
        }
    }

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
