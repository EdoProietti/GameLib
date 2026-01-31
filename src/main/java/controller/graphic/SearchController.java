package controller.graphic;

import bean.GameBean;
import bean.SearchBean;
import controller.logic.AuthController;
import controller.logic.BuyGameController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import session.SessionManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.util.List;

public class SearchController {

    private static final String ALL = "Tutti";
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);
    @FXML private ComboBox<String> filterGenre;
    @FXML private ComboBox<String> filterPlatform;
    @FXML private Slider priceSlider;
    @FXML private Label priceLabel;
    @FXML private TextField searchField;

    @FXML private FlowPane resultsPane;

    @FXML
    private void handleBack(ActionEvent event) {
        if(AuthController.getLoggedUser() != null){
            SceneManager.swichScene(event, "/view/BuyerHomepage.fxml");
        }
        else{
            SceneManager.swichScene(event, "/view/Homepage.fxml");
        }
    }

    public void initialize() {
        // Aggiunge opzioni nel menu a tendina
        filterGenre.setItems(FXCollections.observableArrayList("Action", "RPG", "FPS", "Sport"));
        filterPlatform.setItems(FXCollections.observableArrayList("PC", "PS5", "Xbox", "Switch"));

        // Listener per lo slider
        priceSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            priceLabel.setText(String.format("%.0f€", newVal));
        });
    }

    @FXML
    private void executeSearch() {
        String title = searchField.getText();
        resultsPane.getChildren().clear();

        String genre = (filterGenre.getValue() != null) ? filterGenre.getValue() : ALL;
        String platform = (filterPlatform.getValue() != null) ? filterPlatform.getValue() : ALL;
        BigDecimal maxPrice = BigDecimal.valueOf(priceSlider.getValue());
        SearchBean searchBean = new SearchBean();
        if(!genre.equals(ALL)) {
            searchBean.setGenre(genre);
        }
        if(!platform.equals(ALL)) {
            searchBean.setPlatform(platform);
        }
        searchBean.setTitle(title);
        searchBean.setPrice(maxPrice);
        BuyGameController buyGameController = new BuyGameController();
        List<GameBean> games = buyGameController.searchGame(searchBean);
        for(GameBean g: games){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SearchCard.fxml"));
                VBox card = loader.load();
                GameCardController controller = loader.getController();
                controller.setData(g);
                resultsPane.getChildren().add(card);
            } catch(Exception e){
                LOGGER.error(e.getMessage());
            }
        }
    }
}