package model.library;

import connection.ConnectionFactory;
import factoryDAO.FactoryDAO;
import model.user.Buyer;
import model.game.Game;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

public class LibraryDBDAO extends LibraryDAO {

    private static final HashMap<String, Library> libraries = new HashMap<>();

    public LibraryDBDAO() {
        super();
    }

    @Override
    public Library getLibrary(String username){
        if(libraries.get(username) == null){
            try(FileInputStream input = new FileInputStream("src/main/resources/utils/query.properties")){
                Properties prop = new Properties();
                prop.load(input);
                Connection conn = ConnectionFactory.getInstance().getConnection();
                PreparedStatement pStmt = conn.prepareStatement(prop.getProperty("GET_USER_LIBRARY"));
                pStmt.setString(1,username);
                ResultSet rs = pStmt.executeQuery();
                libraries.put(username, new Library());
                while(rs.next()){
                    libraries.get(username).getGames().add(
                            FactoryDAO.getInstance().createGameDAO().getGame(rs.getString(1),
                                    rs.getString(2)
                            )
                    );
                }
            }catch(IOException e){
                System.out.println("Errore nell'apertura del file: "+e.getMessage());
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return libraries.get(username);
    }

    @Override
    public void addBuyerGame(Buyer buyer, Game game){
        try(FileInputStream input = new FileInputStream("src/main/resources/utils/query.properties")){
            Properties prop = new Properties();
            prop.load(input);
            Connection conn = ConnectionFactory.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(prop.getProperty("ADD_GAME_TO_LIBRARY"));
            preparedStatement.setString(1, game.getTitle());
            preparedStatement.setString(2, buyer.getUsername());
            preparedStatement.setString(3, game.getPublisher().getUsername());
            preparedStatement.execute();
        } catch(IOException e){
            System.out.println("Errore nell'apertura del file: "+e.getMessage());
        } catch(SQLException e){
            System.out.println("Errore sql: "+e.getMessage());
        }
    }
}
