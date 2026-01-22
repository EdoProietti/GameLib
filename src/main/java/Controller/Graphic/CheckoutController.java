package Controller.Graphic;

import Bean.CartBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import java.util.List;

public class CheckoutController {

    @FXML private VBox summaryItemsVBox;
    @FXML private Label finalTotalLabel;
    @FXML private TextField cardHolderField;
    @FXML private TextField cardNumberField;

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
    private void handleConfirmPayment(ActionEvent event) {
        if (cardHolderField.getText().isEmpty() || cardNumberField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Inserisci i dati della carta!");
            alert.show();
        } else {
            System.out.println("Pagamento in corso...");
            // Qui andrebbe la logica di acquisto e poi il cambio scena a "Successo"
        }
    }

    @FXML
    private void handleBackToCart(ActionEvent event) {
        SceneManager.swichScene(event, "/view/ShoppingCartPage.fxml");
    }
}
