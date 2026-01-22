package Bean;

public class CartBean {

    private String gameTitle;
    private String platform;
    private String price;

    public CartBean() {}

    public CartBean(String gameTitle, String platform, String price) {
        this.gameTitle = gameTitle;
        this.platform = platform;
        this.price = price;
    }

    // Getters e Setters
    public String getGameTitle() { return gameTitle; }
    public void setGameTitle(String gameTitle) { this.gameTitle = gameTitle; }
    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }
    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
}
