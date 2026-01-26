package Bean;

import java.math.BigDecimal;

public class NotifyBean {
    private final String gameTitle;
    private final int copySold;
    private final BigDecimal price;

    public NotifyBean(String gameTitle, int copySold, BigDecimal price) {
        this.gameTitle = gameTitle;
        this.copySold = copySold;
        this.price = price;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public int getGameCopySold() {
        return copySold;
    }

    public BigDecimal getPrice() {
        return price;
    }
}