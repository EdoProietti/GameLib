package start;

import controller.cli.HomeCLI;
import factory_dao.FactoryDAO;
import file_path_classes.PropertyPath;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;


public class Main extends Application{

    // utilizzando questo metodo senza classe Launcher, il programma non riesce a caricare le risorse JavaFX
    public static void startApplication(String[] args) {
        FactoryDAO.getInstance().createUserDAO();
        FactoryDAO.getInstance().createGameDAO();
        FactoryDAO.getInstance().createLibraryDAO();
        FactoryDAO.getInstance().createNotifyDAO();
        try(FileInputStream input = new FileInputStream(PropertyPath.getConfigPath())){
            Properties prop = new Properties();
            prop.load(input);
            String graphic =  prop.getProperty("INTERFACE_TYPE");
            if (graphic.equals("cli")) {
                HomeCLI homeCLI = new HomeCLI();
                homeCLI.startHomepage();
            } else {
                launch(args);
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Homepage.fxml")));
            Scene scene = new Scene(root);
            primaryStage.setTitle("GameLib - La tua Libreria Videogiochi");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Errore fatale: Impossibile caricare la Home Page.");
        }
    }
}
