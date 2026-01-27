package Controller.Logic;

import Bean.CartBean;
import Bean.GameBean;
import Bean.NotifyBean;
import Bean.SearchBean;
import FactoryDAO.FactoryDAO;
import Model.Game.Game;
import Model.Game.Genre;
import Model.Game.Platform;
import Model.Library.Library;
import Model.Notify.Notify;
import Model.Search.*;
import Model.ShoppingCart;
import Model.User.Buyer;
import Model.User.Publisher;
import Model.User.User;
import Model.User.UserType;
import Session.SessionManager;
import Exception.*;

import java.util.ArrayList;
import java.util.List;

public class BuyGameController {

    public void addGameToCart(GameBean gameBean) throws  UserNotLogged, UserTypeMissmatch{
        Buyer buyer = getBuyer();
        Game game = FactoryDAO.getInstance().createGameDAO().getGame(gameBean.getTitle(), gameBean.getPublisher());
        Library library = buyer.getLibrary();
        if(!library.isInLibrary(game)){
            buyer.getCart().addGame(game);
        }
    }

    public void addCartGamesToLibrary() throws UserNotLogged, UserTypeMissmatch{
        Buyer buyer = getBuyer();
        ShoppingCart cart = buyer.getCart();
        Library library = buyer.getLibrary();
        for(Game game: cart.getItems()){
            FactoryDAO.getInstance().createNotifyDAO().addNotify(new Notify(game));
            library.addGame(game);
            FactoryDAO.getInstance().createLibraryDAO().addBuyerGame(buyer, game);
        }
        buyer.freeCart();
    }

    public List<GameBean> searchGame(SearchBean searchBean){
        List<GameBean> gameBeans = new ArrayList<>();

        GameFilter filter = new DefaultFilter();
        if(searchBean.getGenre() != null){
            filter = new GenreDecorator(filter, Genre.toGenre(searchBean.getGenre()));
        }
        if(searchBean.getPrice() != null){
            filter = new PriceDecorator(filter, searchBean.getPrice());
        }
        if(searchBean.getPlatform() != null){
            filter = new PlatformDecorator(filter, Platform.toPlatform(searchBean.getPlatform()));
        }
        List<Game> games = FactoryDAO.getInstance().createGameDAO().getGames(searchBean.getTitle());
        for(Game game: games){
            if(filter.check(game)){
                gameBeans.add(new GameBean(game.getTitle(),
                        game.getPublisher().getUsername(),
                        game.getGenre().toString(),
                        game.getPlatform().toString(),
                        game.getPrice())
                );
            }
        }
        return gameBeans;
    }

    private boolean isNotBuyer(User user){
        return !user.getUserType().equals(UserType.BUYER);
    }

    public List<CartBean> getCartItems(){
        Buyer buyer = (Buyer) SessionManager.getInstance().getLoggedUser();
        List<CartBean> cartBeans = new ArrayList<>();
        ShoppingCart shoppingCart = buyer.getCart();
        for(Game game: shoppingCart.getItems()){
            cartBeans.add(new CartBean(
                    game.getTitle()+"---"+game.getPublisher().getUsername(),
                    game.getPlatform().toString(),
                    game.getPrice().toString()
                    ));
        }
        return cartBeans;
    }

    public void removeCartItem(CartBean cartBean) throws UserNotLogged, UserTypeMissmatch{
        String[] parse = cartBean.getGameTitle().split("---");
        String gameTitle = parse[0];
        String publisher = parse[1];
        Game game = FactoryDAO.getInstance().createGameDAO().getGame(gameTitle, publisher);
        Buyer buyer = getBuyer();
        buyer.getCart().getItems().remove(game);
    }

    public Buyer getBuyer() throws UserNotLogged, UserTypeMissmatch{
        User user = SessionManager.getInstance().getLoggedUser();
        if(user == null){
            throw new UserNotLogged();
        }
        if(isNotBuyer(user)){
            throw new UserTypeMissmatch();
        }
        return (Buyer) user;
    }

    public List<GameBean> getLibraryItems() throws UserNotLogged, UserTypeMissmatch{
        List<GameBean> gameBeans = new ArrayList<>();
        Buyer buyer = getBuyer();
        Library library = buyer.getLibrary();
        for(Game game: library.getGames()){
            GameBean g = new GameBean();
            g.setTitle(game.getTitle());
            g.setPublisher(game.getPublisher().getUsername());
            g.setGenre(game.getGenre().toString());
            gameBeans.add(g);
            System.out.println("Aggiunto alla libreria: "+game.getTitle());
        }
        return gameBeans;
    }

    public List<NotifyBean> getPublisherNotification(){
        Publisher publisher = (Publisher) SessionManager.getInstance().getLoggedUser();
        List<NotifyBean> notifyBeans = new ArrayList<>();
        for(Notify notify: publisher.getNotification()){
            notifyBeans.add(new NotifyBean(notify.getGame().getTitle(), notify.getSold(), notify.getGame().getPrice()));
        }
        return notifyBeans;
    }
}