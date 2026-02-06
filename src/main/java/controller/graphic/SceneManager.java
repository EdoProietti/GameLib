package controller.graphic;

import bean.CartBean;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SceneManager {

    private SceneManager(){
        // non serve instanziarlo perché ha tutti i metodo statici
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SceneManager.class.getName());

    public static void swichScene(Event event, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(SceneManager.class.getResource(fxmlPath)));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException | NullPointerException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static void switchToCheckout(Event event, String fxmlPath, List<CartBean> items) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = loader.load();
            CheckoutController controller = loader.getController();
            controller.setCheckoutData(items);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

}