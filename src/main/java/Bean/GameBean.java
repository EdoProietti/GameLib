package Bean;

public class GameBean {
    private String title;
    private String price;
    private String platform;
    private String genre;

    public GameBean(){}

    public GameBean(String title, String genre, String platform, String price) {
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

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
}
