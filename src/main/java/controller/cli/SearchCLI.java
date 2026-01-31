package controller.cli;

import bean.GameBean;
import bean.SearchBean;
import controller.logic.AuthController;
import controller.logic.BuyGameController;
import exception.UserNotLogged;
import exception.UserTypeMissmatch;

import java.math.BigDecimal;
import java.util.*;

public class SearchCLI {

    private final Scanner scanner = new Scanner(System.in);
    private static final String ALL = "Tutti";

    private String genre = "Tutti";
    private String platform = "Tutti";
    private double maxPrice = 150.0;
    private String searchQuery = "";
    private final Map<Integer, GameBean> LASTSEARCHGAME = new HashMap<>();

    public void displaySearchCLI() {
        boolean back = false;
        while (!back) {
            System.out.println("\nCerca giochi, azioni disponibili:");
            System.out.println("1. Inserisci termine di ricerca (Testo)");
            System.out.println("2. Imposta Filtri (Genere, Piattaforma, Prezzo)");
            System.out.println("3. ESEGUI RICERCA");
            System.out.println("0. Torna Indietro");
            System.out.print("\nScelta: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> updateSearchQuery();
                case "2" -> configureFilters();
                case "3" -> executeSearch();
                case "0" -> back = true;
                default -> System.out.println("Opzione non valida.");
            }
        }
    }

    private void updateSearchQuery() {
        System.out.print("Inserisci il nome del gioco: ");
        this.searchQuery = scanner.nextLine();
    }

    private void configureFilters() {
        System.out.print("Genere (Action, RPG, Sport, FPS): ");
        this.genre = scanner.nextLine();
        System.out.print("Piattaforma (PC, PS5, Xbox, Switch): ");
        this.platform = scanner.nextLine();
        System.out.print("Prezzo Massimo (0-150): ");
        try {
            this.maxPrice = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Valore non valido, mantengo " + maxPrice + "€");
        }
    }

    private void executeSearch() {
        System.out.println("\n[Sistema] Ricerca in corso con i parametri impostati...");
        SearchBean searchBean = new SearchBean();
        if(!Objects.equals(genre, ALL)) {
            searchBean.setGenre(genre);
        }
        if(!Objects.equals(platform, ALL)) {
            searchBean.setPlatform(platform);
        }
        searchBean.setPrice(BigDecimal.valueOf(maxPrice));
        searchBean.setTitle(searchQuery);
        List<GameBean> gameBeans = new BuyGameController().searchGame(searchBean);
        LASTSEARCHGAME.clear();
        for(int i = 0; i < gameBeans.size(); i++){
            int visual_id = i+1;
            GameBean gameBean = gameBeans.get(i);
            LASTSEARCHGAME.put(visual_id, gameBean);
            System.out.println(visual_id+") "+gameBean.getTitle()+"|"+gameBean.getPublisher()+"|"+gameBean.getPlatform()+"|"+gameBean.getPrice());
        }
        System.out.println("Fine risultati.");
        selectGame();
    }

    private void selectGame(){
        System.out.println("Inserisci il numero del gioco che vuoi aggiungere al carrello:");
        try{
            int choice = Integer.parseInt(scanner.nextLine());
            if(LASTSEARCHGAME.containsKey(choice)){
                GameBean gameBean = LASTSEARCHGAME.get(choice);
                BuyGameController buyGameController = new BuyGameController();
                buyGameController.addGameToCart(gameBean);
                System.out.println("Gioco aggiunto al carrello.");
            }
        }catch(UserNotLogged e){
            System.out.println(e.getMessage());
            LoginCLI loginCLI = new LoginCLI();
            loginCLI.displayLogin();
        }catch(UserTypeMissmatch e){
            System.out.println(e.getMessage());
            AuthController authController = new AuthController();
            authController.logoutUser();
            LoginCLI loginCLI = new LoginCLI();
            loginCLI.displayLogin();
        }catch(NumberFormatException e){
            System.out.println("Errore: inserisci un numero valido (intero)");
        }
    }
}