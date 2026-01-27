package controller.graphic;

import bean.CartBean;
import bean.GameBean;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class SceneManager {

    private SceneManager(){
        // non serve instanziarlo perché ha tutti i metodo statici
    }

    public static void swichScene(Event event, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(SceneManager.class.getResource(fxmlPath)));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("Errore: Impossibile caricare il file FXML in " + fxmlPath);
        } catch (NullPointerException e) {
            System.out.println("Errore: File FXML non trovato al percorso: " + fxmlPath);
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
            System.out.println("Errore nell'apertura del file xml");
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
            System.out.println("Errore nel caricamento della pagina di checkout.");
        }
    }

}