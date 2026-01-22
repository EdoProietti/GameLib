package Controller.Graphic;

import Bean.CartBean;
import Bean.GameBean;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SceneManager {
    public static void swichScene(Event event, String fxmlPath) {
        try {
            // Carichiamo il file FXML
            Parent root = FXMLLoader.load(Objects.requireNonNull(SceneManager.class.getResource(fxmlPath)));

            // Recuperiamo lo Stage dall'evento
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println("Errore: Impossibile caricare il file FXML in " + fxmlPath);
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Errore: File FXML non trovato al percorso: " + fxmlPath);
        }
    }

    public static void switchToEditGame(Event event, String fxmlPath, GameBean bean) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = loader.load();

            Object controller = loader.getController();

            if (controller instanceof AddGameController && bean != null) {
                ((AddGameController) controller).setGameBean(bean);
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToCheckout(Event event, String fxmlPath, List<CartBean> items) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = loader.load();
            CheckoutController controller = loader.getController();

            // Passiamo i dati richiamando il metodo che abbiamo creato nel CheckoutController
            controller.setCheckoutData(items);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Errore nel caricamento della pagina di checkout.");
        }
    }

}