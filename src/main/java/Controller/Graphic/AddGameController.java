package Controller.Graphic;

import Bean.GameBean;
import Controller.Logic.ManageGameController;
import Session.SessionManager;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import Exception.*;
import java.math.BigDecimal;

public class AddGameController {

    @FXML private TextField titleField;
    @FXML private ComboBox<String> genreCombo;
    @FXML private ComboBox<String> platformCombo;
    @FXML private TextField priceField;
    @FXML private TextArea descArea;
    @FXML private Label statusLabel;

    @FXML
    public void initialize() {
        genreCombo.setItems(FXCollections.observableArrayList("Action", "RPG", "FPS", "Sport"));
        platformCombo.setItems(FXCollections.observableArrayList("PC", "PS5", "Xbox", "Nintendo Switch"));
    }

    @FXML
    private void handleSaveGame(Event event) {
        String title = titleField.getText();
        String genre = genreCombo.getValue();
        String platform = platformCombo.getValue();
        String priceText = priceField.getText();

        if (title.isEmpty() || genre == null || platform == null || priceText.isEmpty()) {
            showError("Errore: Tutti i campi sono obbligatori!");
            return;
        }

        try {
            double price = Double.parseDouble(priceText.replace(",", "."));
            if (price < 0) throw new NumberFormatException();
            GameBean gameBean = new GameBean(
                    title,
                    genre,
                    platform,
                    BigDecimal.valueOf(price)
            );
            ManageGameController manage = new ManageGameController();
            manage.addNewGame(gameBean);
            System.out.println("Salvataggio gioco: " + title + " (" + platform + ") a €" + price);
            statusLabel.setTextFill(Color.web("#00ff00"));
            statusLabel.setText("Gioco aggiunto correttamente al catalogo!");
        } catch (NumberFormatException e) {
            showError("Errore: Inserisci un prezzo valido (es: 29.99)");
        } catch (GameExist e){
            showError("Errore: Gioco già presente nel catalogo.");
        } catch(UserTypeMissmatch e){
            SessionManager.getInstance().freeLoggedUser();
            SceneManager.swichScene(event, "/view/Login.fxml");
        }
    }

    @FXML
    private void handleCancel(Event event) {
        SceneManager.swichScene(event, "/view/PublisherHome.fxml");
    }

    private void showError(String message) {
        statusLabel.setTextFill(Color.web("#ff4c4c"));
        statusLabel.setText(message);
    }

    public void setGameBean(GameBean bean) {
        // Pre-compiliamo i campi grafici con i dati del Bean nel caso di modifica
        titleField.setText(bean.getTitle());
        genreCombo.setValue(bean.getGenre());
        platformCombo.setValue(bean.getPlatform());
        priceField.setText(bean.getPrice().toString());
    }
}