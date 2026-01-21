package Controller.Graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameCardController {

    @FXML
    public Button btnAddToCart;
    @FXML
    private Label gameTitle;
    @FXML
    private Label genreGame;
    @FXML
    private Label gamePrice;

    public void setData(String title) {
        gameTitle.setText(title);
    }

    public void handleAddToCart(ActionEvent event) {
        System.out.println("Aggiunto al carrello il gioco.");
    }
}
