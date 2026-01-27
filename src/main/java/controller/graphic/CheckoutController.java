package controller.graphic;

import bean.CartBean;
import bean.PaymentBean;
import controller.logic.BuyGameController;
import session.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import java.util.List;
import exception.*;

public class CheckoutController {

    @FXML private VBox summaryItemsVBox;
    @FXML private Label finalTotalLabel;
    @FXML private TextField cardHolderField;
    @FXML private TextField cardNumberField;
    @FXML private TextField expiryField;
    @FXML private TextField cvvField;

    // Popola il riepilogo
    public void setCheckoutData(List<CartBean> items) {
        summaryItemsVBox.getChildren().clear();
        double total = 0;
        for (CartBean item : items) {
            HBox row = new HBox();
            Label title = new Label(item.getGameTitle());
            title.setTextFill(javafx.scene.paint.Color.WHITE);
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            Label price = new Label("€ " + item.getPrice());
            price.setTextFill(javafx.scene.paint.Color.web("#00d4ff"));
            row.getChildren().addAll(title, spacer, price);
            summaryItemsVBox.getChildren().add(row);
            total += Double.parseDouble(item.getPrice());
        }
        finalTotalLabel.setText(String.format("€ %.2f", total));
    }

    @FXML
    private void handleConfirmPayment(ActionEvent event){
        if (cardHolderField.getText().isEmpty() || cardNumberField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Inserisci i dati della carta!");
            alert.show();
        } else {
            try{
                PaymentBean paymentBean = new PaymentBean();
                paymentBean.setCardNumber(cardNumberField.getText());
                paymentBean.setCvv(cvvField.getText());
                paymentBean.setExpirationDate(expiryField.getText());
                paymentBean.setNameSurname(cardHolderField.getText());
                BuyGameController buyGameController = new BuyGameController();
                buyGameController.addCartGamesToLibrary();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Pagamento eseguito con successo");
                alert.showAndWait();
                SceneManager.swichScene(event, "/view/BuyerHomepage.fxml");
            } catch(IllegalArgumentException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Formato dati carta non valido!");
                alert.showAndWait();
            } catch (UserNotLogged | UserTypeMissmatch e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Azione non possibile!");
                alert.showAndWait();
                SessionManager.getInstance().freeLoggedUser();
                SceneManager.swichScene(event, "/view/Login.fxml");
            }
        }
    }

    @FXML
    private void handleBackToCart(ActionEvent event) {
        SceneManager.swichScene(event, "/view/ShoppingCartPage.fxml");
    }
}