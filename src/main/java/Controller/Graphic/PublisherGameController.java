package Controller.Graphic;

import Bean.GameBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class PublisherGameController {
    @FXML private TableView<GameBean> inventoryTable;
    @FXML private TableColumn<GameBean, String> colTitle;
    @FXML private TableColumn<GameBean, String> colGenre;
    @FXML private TableColumn<GameBean, String> colPlatform;
    @FXML private TableColumn<GameBean, String> colPrice;
    @FXML private TableColumn<GameBean, Void> colActions;

    @FXML
    public void initialize() {
        // Colleghiamo le colonne alle variabili del GameBean
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colPlatform.setCellValueFactory(new PropertyValueFactory<>("platform"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Carichiamo dati di esempio (che normalmente arriverebbero da una logica di business)
        ObservableList<GameBean> data = FXCollections.observableArrayList(
                new GameBean("Cyberpunk 2077", "RPG", "PC", "59.99"),
                new GameBean("Elden Ring", "Action", "PS5", "69.99"),
                new GameBean("Hades", "Indie", "Switch", "24.50")
        );

        setupActionColumn();
        inventoryTable.setItems(data);
    }

    private void setupActionColumn() {
        Callback<TableColumn<GameBean, Void>, TableCell<GameBean, Void>> cellFactory = param -> new TableCell<>() {
            private final Button editBtn = new Button("Modifica");
            {
                editBtn.setStyle("-fx-background-color: #00d4ff; -fx-text-fill: #1e1e2f; -fx-font-weight: bold; -fx-background-radius: 10;");
                editBtn.setOnAction(event -> {
                    GameBean selected = getTableView().getItems().get(getIndex());
                    handleEdit(event, selected);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : editBtn);
            }
        };
        colActions.setCellFactory(cellFactory);
    }

    private void handleEdit(ActionEvent event, GameBean bean) {
        SceneManager.switchToEditGame(event, "/view/AddNewGame.fxml", bean);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.swichScene(event, "/view/PublisherHome.fxml");
    }
}
