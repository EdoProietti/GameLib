package Controller.Graphic;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class SearchController {


    @FXML private ComboBox<String> filterGenre;
    @FXML private ComboBox<String> filterPlatform;
    @FXML private Slider priceSlider;
    @FXML private Label priceLabel;
    @FXML private TextField searchField;

    @FXML private FlowPane resultsPane;

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.swichScene(event, "/view/Homepage.fxml");
    }

    public void initialize() {
        // Popola i menu a tendina
        filterGenre.setItems(FXCollections.observableArrayList("Action", "RPG", "FPS", "Sport"));
        filterPlatform.setItems(FXCollections.observableArrayList("PC", "PS5", "Xbox", "Switch"));

        // Listener per lo slider (aggiorna il testo del prezzo mentre lo muovi)
        priceSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            priceLabel.setText(String.format("%.0f€", newVal));
        });
    }

    @FXML
    private void executeSearch() {
        String query = searchField.getText();
        resultsPane.getChildren().clear();

        String genre = (filterGenre.getValue() != null) ? filterGenre.getValue() : "Tutti";
        String platform = (filterPlatform.getValue() != null) ? filterPlatform.getValue() : "Tutti";
        BigDecimal maxPrice = BigDecimal.valueOf(priceSlider.getValue());

        // System.out.println("Nome: " + query + " Genere: "+ genre + " Piattaforma: "+ platform + " Prezzo massimo: "+ maxPrice);
        // Funzione controllore logico per recuperare i bean dei giochi.
        String[] mockGames = {"prova gioco"};

        for(String name: mockGames){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SearchCard.fxml"));
                VBox card = loader.load();
                GameCardController controller = loader.getController();
                controller.setData(name);
                resultsPane.getChildren().add(card);
            } catch(Exception e){
                System.out.println("Errore nel caricamento del file xml");
            }
        }
    }
}