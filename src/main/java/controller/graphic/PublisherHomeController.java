package controller.graphic;

import bean.NotifyBean;
import controller.logic.AuthController;
import controller.logic.BuyGameController;
import session.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class PublisherHomeController {

    @FXML
    private ListView<String> notificationList;
    @FXML
    private Label publisherName;
    @FXML
    private Label publisherGrossing;
    @FXML
    private Label publisherCopySold;

    @FXML
    public void initialize() {
        ObservableList<String> sales = FXCollections.observableArrayList();
        BuyGameController buyGameController = new BuyGameController();
        List<NotifyBean> notification = buyGameController.getPublisherNotification();
        BigDecimal total = BigDecimal.ZERO;
        int copySold = 0;
        for(NotifyBean notifyBean : notification){
            BigDecimal subtotal = notifyBean.getPrice().multiply(BigDecimal.valueOf(notifyBean.getGameCopySold()));
            total = total.add(subtotal);
            copySold += notifyBean.getGameCopySold();
            sales.add("Copie vendute di "+notifyBean.getGameTitle()+" :"+notifyBean.getGameCopySold());
        }
        publisherGrossing.setText("Grossing: €"+total.setScale(2, RoundingMode.HALF_UP));
        publisherCopySold.setText("Copy Sold: "+copySold);
        notificationList.setItems(sales);
        publisherName.setText(SessionManager.getInstance().getLoggedUser().getUsername());
    }

    @FXML
    private void openAddGameDialog(Event event) {
        SceneManager.swichScene(event, "/view/AddNewGame.fxml");
    }

    // metodo che permette il refresh delle notifiche.
    @FXML
    private void showDashboard(Event event) {
        System.out.println("Ricarica dashboard...");
    }

    @FXML
    private void showMyGames(Event event) {
        SceneManager.swichScene(event, "/view/PublisherGame.fxml");
    }

    @FXML
    private void handleLogout(Event event) {
        AuthController auth = new AuthController();
        auth.logoutUser();
        SceneManager.swichScene(event, "/view/Login.fxml");
    }
}