package model.game;

import connection.ConnectionFactory;
import factoryDAO.FactoryDAO;
import filePathClasses.PropertyPath;
import model.user.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GameDBDAO extends GameDAO{

    public GameDBDAO(){
        super();
    }

    private static final List<Game> gameList = new ArrayList<>();
    private final static Logger LOGGER = LoggerFactory.getLogger(GameDBDAO.class);

    @Override
    public Game getGame(String title, String publisher){
        try(FileInputStream input = new FileInputStream(PropertyPath.getQueryPath())){
            for(Game g : gameList){
                if(g.getTitle().equals(title) &&
                        g.getPublisher().equals(FactoryDAO.getInstance().createUserDAO().getUserByUsername(publisher))){
                    return g;
                }
            }
            Properties prop = new Properties();
            prop.load(input);
            Connection conn = ConnectionFactory.getInstance().getConnection();
            PreparedStatement pStmt = conn.prepareStatement(prop.getProperty("GET_GAME"));
            pStmt.setString(1,title);
            pStmt.setString(2,publisher);
            ResultSet rs = pStmt.executeQuery();
            Game game = null;
            while(rs.next()){
                game = new Game(
                        rs.getString(1),
                        BigDecimal.valueOf(rs.getFloat(3)),
                        (Publisher) FactoryDAO.getInstance().createUserDAO().getUserByUsername(rs.getString(2)),
                        Genre.toGenre(rs.getString(5)),
                        Platform.toPlatform(rs.getString(4))
                );
                gameList.add(game);
            }
            return game;
        } catch (SQLException | IOException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Game> getGames(String title){
        List<Game> games = new ArrayList<>();
        try(FileInputStream input = new FileInputStream(PropertyPath.getQueryPath())){
            Properties prop = new Properties();
            prop.load(input);
            Connection conn = ConnectionFactory.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(prop.getProperty("GET_GAMES"));
            preparedStatement.setString(1,"%"+title+"%");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Game g = isInList(rs.getString(1), rs.getString(2));
                if(g != null){
                    games.add(g);
                }else{
                    g = new Game(rs.getString(1),
                            BigDecimal.valueOf(rs.getFloat(3)),
                            (Publisher) FactoryDAO.getInstance().createUserDAO().getUserByUsername(rs.getString(2)),
                            Genre.toGenre(rs.getString(5)),
                            Platform.toPlatform(rs.getString(4))
                    );
                    games.add(g);
                    gameList.add(g);
                }
            }
            return games;
        } catch(SQLException | IOException e){
            LOGGER.error(e.getMessage());
        }
        return games;
    }

    @Override
    public void addGame(Game game){
        try(FileInputStream input = new FileInputStream(PropertyPath.getQueryPath())){
            Properties prop = new Properties();
            prop.load(input);
            Connection conn = ConnectionFactory.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(prop.getProperty("ADD_GAME"));
            preparedStatement.setString(1,game.getTitle());
            preparedStatement.setString(2, game.getPublisher().getUsername());
            preparedStatement.setFloat(3, game.getPrice().floatValue());
            preparedStatement.setString(4, game.getPlatform().toString());
            preparedStatement.setString(5, game.getGenre().toString());
            preparedStatement.setInt(6, 0);
            preparedStatement.execute();
            gameList.add(game);
        } catch (IOException | SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Game> getPublisherGames(Publisher publisher){
        List<Game> games = new ArrayList<>();
        try(FileInputStream input = new FileInputStream(PropertyPath.getQueryPath())){
            Properties prop = new Properties();
            prop.load(input);
            Connection conn = ConnectionFactory.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(prop.getProperty("GET_PUBLISHER_GAME"));
            preparedStatement.setString(1,publisher.getUsername());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Game g = isInList(rs.getString(1), rs.getString(2));
                if(g != null){
                    games.add(g);
                } else {
                    g = new Game(rs.getString(1),
                            BigDecimal.valueOf(rs.getFloat(3)),
                            publisher,
                            Genre.toGenre(rs.getString(5)),
                            Platform.toPlatform(rs.getString(4))
                    );
                    games.add(g);
                    gameList.add(g);
                }
            }
            return games;
        }catch (IOException | SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return games;
    }

    private Game isInList(String title, String publisher){
        for(Game g: gameList){
            if(g.getTitle().equals(title) && g.getPublisher().getUsername().equals(publisher)){
                return g;
            }
        }
        return null;
    }
}