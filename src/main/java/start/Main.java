package start;

import FactoryDAO.FactoryDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Homepage.fxml")));
            Scene scene = new Scene(root);
            primaryStage.setTitle("GameLib - La tua Libreria Videogiochi");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Errore fatale: Impossibile caricare la Home Page.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        FactoryDAO.getInstance().createUserDAO();
        FactoryDAO.getInstance().createGameDAO();
        FactoryDAO.getInstance().createLibraryDAO();
        FactoryDAO.getInstance().createNotifyDAO();
        // Avvia l'applicazione JavaFX
        launch(args);
    }
}
