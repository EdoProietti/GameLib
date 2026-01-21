package Controller.Graphic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class PublisherHomeController {

    @FXML
    private ListView<String> notificationList;

    @FXML
    public void initialize() {
        // Simuliamo le notifiche delle vendite
        ObservableList<String> sales = FXCollections.observableArrayList(
                "✅ Venduta copia di 'Cyber Adventure' a user99 - 2 minuti fa",
                "✅ Venduta copia di 'Space Warrior' a pro_gamer - 15 minuti fa",
                "✅ Venduta copia di 'Cyber Adventure' a luca_88 - 1 ora fa",
                "✅ Venduta copia di 'Old School RPG' a player_one - 3 ore fa"
        );
        notificationList.setItems(sales);
    }

    @FXML
    private void openAddGameDialog(Event event) {
        SceneManager.swichScene(event, "/view/AddNewGame.fxml");
    }

    @FXML
    private void showDashboard(Event event) {
        System.out.println("Ricarica dashboard...");
    }

    @FXML
    private void showMyGames(Event event) {
        System.out.println("Mostra lista giochi pubblicati...");
    }

    @FXML
    private void handleLogout(Event event) {
        SceneManager.swichScene(event, "/view/Login.fxml");
    }
}