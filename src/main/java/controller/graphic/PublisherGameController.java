package controller.graphic;

import bean.GameBean;
import controller.logic.AuthController;
import controller.logic.ManageGameController;
import exception.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublisherGameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherGameController.class);

    @FXML private TableView<GameBean> inventoryTable;
    @FXML private TableColumn<GameBean, String> colTitle;
    @FXML private TableColumn<GameBean, String> colGenre;
    @FXML private TableColumn<GameBean, String> colPlatform;
    @FXML private TableColumn<GameBean, String> colPrice;
    @FXML private TableColumn<GameBean, Void> colActions;

    @FXML
    public void initialize() {
        // Diamo i nomi alle colonne della tabella.
        try{
            colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
            colPlatform.setCellValueFactory(new PropertyValueFactory<>("platform"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            ObservableList<GameBean> data = FXCollections.observableArrayList();
            ManageGameController manage = new ManageGameController();
            data.addAll(manage.getGameList());
            inventoryTable.setItems(data);
        }catch (UserTypeMissmatch e) {
            LOGGER.error(e.getMessage());
            AuthController a = new AuthController();
            a.logoutUser();
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.swichScene(event, "/view/PublisherHome.fxml");
    }
}
