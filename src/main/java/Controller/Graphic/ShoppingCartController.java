package Controller.Graphic;

import Bean.CartBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ShoppingCartController {

    @FXML private TableView<CartBean> cartTable;
    @FXML private TableColumn<CartBean, String> colTitle;
    @FXML private TableColumn<CartBean, String> colPlatform;
    @FXML private TableColumn<CartBean, String> colPrice;
    @FXML private TableColumn<CartBean, Void> colAction;

    @FXML private Label totalLabel;
    @FXML private Label subtotalLabel;

    private ObservableList<CartBean> cartItems = FXCollections.observableArrayList();

    @FXML
    private void handleBack(Event event){
        SceneManager.swichScene(event, "/view/BuyerHomepage.fxml");
    }

    @FXML
    public void initialize() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("gameTitle"));
        colPlatform.setCellValueFactory(new PropertyValueFactory<>("platform"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Esempio inserimento dati
        cartItems.add(new CartBean("Elden Ring", "PC", "59.99"));
        cartItems.add(new CartBean("Hades II", "PC", "24.50"));

        setupActionColumn();
        cartTable.setItems(cartItems);
        updateTotals();
    }

    private void setupActionColumn() {
        // Per ogni cella creo un button per l'eliminazione degli oggetti dal controller
        colAction.setCellFactory(param -> new TableCell<>() {
            private final Button deleteBtn = new Button("X");
            {
                deleteBtn.setStyle("-fx-background-color: #ff4c4c; -fx-text-fill: white; -fx-font-weight: bold;");
                deleteBtn.setOnAction(event -> {
                    cartItems.remove(getTableView().getItems().get(getIndex()));
                    // Qui chiamiamo il controllore applicativo per eliminare dal carrello gli oggetti.
                    updateTotals();
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteBtn);
            }
        });
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
