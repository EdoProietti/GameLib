package controller.graphic;

import bean.GameBean;
import controller.logic.BuyGameController;
import exception.*;
import session.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class GameCardController {

    @FXML
    public Button btnAddToCart;
    @FXML
    private Label gameTitle;
    @FXML
    private Label genreGame;
    @FXML
    private Label gamePrice;
    @FXML
    private Label gamePublisher;

    public void setData(GameBean game) {
        gameTitle.setText(game.getTitle());
        genreGame.setText(game.getGenre());
        gamePrice.setText("€"+game.getPrice().setScale(2, RoundingMode.HALF_UP));
        gamePublisher.setText(game.getPublisher());
    }

    public void handleAddToCart(ActionEvent event) {
        GameBean gameBean = new GameBean();
        gameBean.setTitle(gameTitle.getText());
        gameBean.setGenre(genreGame.getText());
        gameBean.setPublisher(gamePublisher.getText());
        gameBean.setPrice(new BigDecimal(gamePrice.getText().substring(1)));
        BuyGameController buyGameController = new BuyGameController();
        try{
            buyGameController.addGameToCart(gameBean);
        }  catch (UserNotLogged e) {
            showPopup("Non hai effettuato il login.", "Effettua il login per aggiungere giochi al carrello");
            SceneManager.swichScene(event, "/view/Login.fxml");
        } catch (UserTypeMissmatch e) {
            showPopup("Azione consentita solamente ai compratori.", "Ritorno alla schermata di login");
            SessionManager.getInstance().freeLoggedUser();
            SceneManager.swichScene(event, "/view/Login.fxml");
        }
        btnAddToCart.setDisable(true);
        btnAddToCart.setText("Aggiunto al carrello");
    }

    private void showPopup(String typeError, String description) {
        // creo alert di tipo informazione
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(typeError);
        alert.setContentText(description);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #2a2a3d;");
        dialogPane.lookupAll(".label").forEach(node -> node.setStyle("-fx-text-fill: white;"));
        alert.showAndWait();
    }
}
