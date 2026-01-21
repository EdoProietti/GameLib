package Controller.Graphic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class SceneManager {
    public static void swichScene(ActionEvent event, String fxmlPath) {
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
}