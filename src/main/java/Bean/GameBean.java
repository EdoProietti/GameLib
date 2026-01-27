package Bean;

import java.math.BigDecimal;

public class GameBean {
    private String title;
    private String publisher;
    private BigDecimal price;
    private String platform;
    private String genre;

    public GameBean(){}

    public GameBean(String title, String publisher, String genre, String platform, BigDecimal price) {
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.platform = platform;
        this.price = price;
    }

    public GameBean(String title, String genre, String platform, BigDecimal price) {
        this.title = title;
        this.genre = genre;
        this.platform = platform;
        this.price = price;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public  String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
}
