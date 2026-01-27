package Controller.Graphic;

import Bean.CartBean;
import Controller.Logic.BuyGameController;
import Session.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Exception.*;

import java.util.List;

public class ShoppingCartController {

    @FXML private TableView<CartBean> cartTable;
    @FXML private TableColumn<CartBean, String> colTitle;
    @FXML private TableColumn<CartBean, String> colPlatform;
    @FXML private TableColumn<CartBean, String> colPrice;
    @FXML private TableColumn<CartBean, Void> colAction;

    @FXML private Label totalLabel;
    @FXML private Label subtotalLabel;

    private final ObservableList<CartBean> cartItems = FXCollections.observableArrayList();

    @FXML
    private void handleBack(Event event){
        SceneManager.swichScene(event, "/view/BuyerHomepage.fxml");
    }

    @FXML
    public void initialize() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("gameTitle"));
        colPlatform.setCellValueFactory(new PropertyValueFactory<>("platform"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        BuyGameController buyGameController = new BuyGameController();
        List<CartBean> cart = buyGameController.getCartItems();
        cartItems.addAll(cart);

        setupActionColumn();
        cartTable.setItems(cartItems);
        updateTotals();
    }

    private void setupActionColumn() {
        colAction.setCellFactory(param -> new DeleteButtonCell());
    }

    // classe interna per gestire la cella con il tasto di eliminazione
    private class DeleteButtonCell extends TableCell<CartBean, Void> {
        private final Button deleteBtn = new Button("X");

        public DeleteButtonCell() {
            deleteBtn.setStyle("-fx-background-color: #ff4c4c; -fx-text-fill: white; -fx-font-weight: bold;");
            deleteBtn.setOnAction(event -> {
                CartBean cartBean = getTableView().getItems().get(getIndex());
                BuyGameController buyGameController = new BuyGameController();
                try {
                    buyGameController.removeCartItem(cartBean);
                } catch (UserNotLogged e) {
                    System.out.println("User not logged");
                    SceneManager.swichScene(event, "/view/Login.fxml");
                } catch (UserTypeMissmatch e) {
                    System.out.println("UserTypeMissmatch");
                    SessionManager.getInstance().freeLoggedUser();
                    SceneManager.swichScene(event, "/view/Login.fxml");
                }
                // rimuovo dall'interfaccia il gioco eliminato
                cartItems.remove(cartBean);
                updateTotals();
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            // Gestione della visibilità del bottone
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(deleteBtn);
            }
        }
    }

    private void updateTotals() {
        double total = 0;
        for (CartBean item : cartItems) {
            total += Double.parseDouble(item.getPrice());
        }
        String totalStr = String.format("€ %.2f", total);
        totalLabel.setText(totalStr);
        subtotalLabel.setText(totalStr);
    }

    @FXML
    private void handleCheckout(ActionEvent event) {
        if (cartItems.isEmpty()) {
            System.out.println("Il carrello è vuoto!");
        } else {
            System.out.println("Navigazione verso la pagina di pagamento...");
            SceneManager.switchToCheckout(event, "/view/CheckoutPage.fxml", cartItems);
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.swichScene(event, "/view/BuyerHomepage.fxml");
    }
}
