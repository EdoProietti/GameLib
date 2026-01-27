package Controller.Logic;

import Bean.GameBean;
import FactoryDAO.FactoryDAO;
import Model.Game.Genre;
import Model.Game.Platform;
import Model.User.Publisher;
import Model.User.User;
import Model.User.UserType;
import Model.Game.Game;
import Session.SessionManager;
import Exception.*;

import java.util.ArrayList;
import java.util.List;

public class ManageGameController {

    private boolean isNotPublisher(){
        User user = SessionManager.getInstance().getLoggedUser();
        return user == null || !user.getUserType().equals(UserType.PUBLISHER);
    }

    private Publisher getPublisherSession(){
        return (Publisher) SessionManager.getInstance().getLoggedUser();
    }

    private boolean gameTitleExist(Publisher publisher, String title){
        for(Game g: FactoryDAO.getInstance().createGameDAO().getPublisherGames(publisher)){
            if(g.getTitle().equals(title)){
                return true;
            }
        }
        return false;
    }

    public void addNewGame(GameBean gameBean) throws UserTypeMissmatch, GameExist{
        if(isNotPublisher()){
            throw new UserTypeMissmatch();
        }
        Publisher publisher = getPublisherSession();
        if(gameTitleExist(publisher, gameBean.getTitle())){
            throw new GameExist();
        }
        Game game = new Game(
                gameBean.getTitle(),
                gameBean.getPrice(),
                publisher,
                Genre.toGenre(gameBean.getGenre()),
                Platform.toPlatform(gameBean.getPlatform())
        );
        FactoryDAO.getInstance().createGameDAO().addGame(game);
        publisher.getCatalog().add(game);
    }

    public List<GameBean> getGameList() throws UserTypeMissmatch{
        if(isNotPublisher()) {
            throw new UserTypeMissmatch();
        }
        Publisher publisher = getPublisherSession();
        List<GameBean> gameBeans = new ArrayList<>();
        for(Game g: publisher.getCatalog()){
            gameBeans.add(new GameBean(g.getTitle(), g.getGenre().toString(), g.getPlatform().toString(), g.getPrice()));
        }
        return gameBeans;
    }
}
