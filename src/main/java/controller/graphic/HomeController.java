package controller.graphic;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class HomeController {
    @FXML
    private Button btnSearch;

    @FXML
    private Button btnLogin;

    @FXML
    private void handleSearch(ActionEvent event) {
        SceneManager.swichScene(event, "/view/Search.fxml");
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        SceneManager.swichScene(event, "/view/Login.fxml");
    }
}