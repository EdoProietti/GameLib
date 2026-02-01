package controller.logic;

import bean.*;
import factory_dao.FactoryDAO;
import model.game.Game;
import model.game.Genre;
import model.game.Platform;
import model.library.Library;
import model.notify.Notify;
import model.search.*;
import model.ShoppingCart;
import model.user.Buyer;
import model.user.Publisher;
import model.user.User;
import model.user.UserType;
import session.SessionManager;
import exception.*;

import java.math.RoundingMode;
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

    public void checkout() throws UserNotLogged, UserTypeMissmatch {
        addCartGamesToLibrary();
    }

    private void addCartGamesToLibrary() throws UserNotLogged, UserTypeMissmatch{
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
                    game.getPrice().setScale(2, RoundingMode.HALF_UP).toString()
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
            g.setPlatform(game.getPlatform().toString());
            gameBeans.add(g);
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