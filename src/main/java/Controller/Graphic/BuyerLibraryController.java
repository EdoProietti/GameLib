package Controller.Graphic;

import Bean.GameBean;
import Controller.Logic.BuyGameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import Exception.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class BuyerLibraryController {

    @FXML private FlowPane gamesGrid;

    @FXML
    public void initialize() {
        // Chiamo il metodo per ottenere i bean dal controllore logico.
        try{
            BuyGameController buyGameController = new BuyGameController();
            List<GameBean> ownedGames = buyGameController.getLibraryItems();
            setLibraryData(ownedGames);
        } catch (UserNotLogged e) {
            System.out.println("User not logged");
        } catch (UserTypeMissmatch e) {
            System.out.println("Type mismatch");
        }
    }

    public void setLibraryData(List<GameBean> ownedGames) {
        gamesGrid.getChildren().clear();

        for (GameBean game : ownedGames) {
            gamesGrid.getChildren().add(createGameCard(game));
        }
    }

    private VBox createGameCard(GameBean game) {
        VBox card = new VBox(10);
        card.setPrefSize(180, 220);
        card.setAlignment(Pos.CENTER);
        card.setStyle("-fx-background-color: #2a2a3d; -fx-background-radius: 15; -fx-border-color: #3d3d5c; -fx-border-radius: 15;");

        // "Copertina" simbolica (un rettangolo colorato o un'icona)
        Region cover = new Region();
        cover.setPrefSize(160, 100);
        cover.setStyle("-fx-background-color: #3d3d5c; -fx-background-radius: 10;");

        Label title = new Label(game.getTitle());
        title.setTextFill(Color.WHITE);
        title.setStyle("-fx-font-weight: bold;");
        title.setWrapText(true);

        Button playBtn = new Button("GIOCA");
        playBtn.setOnAction(event -> {
            System.out.println("Feature non supportata.");
        });
        playBtn.setStyle("-fx-background-color: #00d4ff; -fx-text-fill: #1e1e2f; -fx-font-weight: bold; -fx-background-radius: 15;");
        playBtn.setPrefWidth(120);

        card.getChildren().addAll(cover, title, playBtn);
        return card;
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneManager.swichScene(event, "/view/BuyerHomepage.fxml");
    }
}
