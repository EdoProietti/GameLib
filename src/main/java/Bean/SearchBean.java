package Bean;

import java.math.BigDecimal;

public class SearchBean {
    private String title;
    private String platform;
    private String genre;
    private BigDecimal price;

    public SearchBean() {
        this.platform = null;
        this.genre = null;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPlatform() {
        return platform;
    }
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
