package model.notify;

import connection.ConnectionFactory;
import factoryDAO.FactoryDAO;
import model.user.Publisher;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import exception.NotifyException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class NotifyDBDAO extends NotifyDAO{

    private static final HashMap<String, List<Notify>> notifications = new HashMap<>();

    public NotifyDBDAO(){
        super();
    }

    private Notify isInList(String title, List<Notify> list){
        for(Notify notify : list){
            if(notify.getGame().getTitle().equals(title)){
                return notify;
            }
        }
        return null;
    }

    @Override
    public List<Notify> getPublisherNotification(Publisher publisher) {
        try(FileInputStream input = new FileInputStream("src/main/resources/utils/query.properties")){
            List<Notify> notifyList;
            if(notifications.get(publisher.getUsername()) == null){
                notifications.put(publisher.getUsername(), new ArrayList<>());
            }
            notifyList = notifications.get(publisher.getUsername());
            Properties prop = new Properties();
            prop.load(input);
            Connection conn = ConnectionFactory.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(prop.getProperty("GET_PUBLISHER_NOTIFICATION"));
            preparedStatement.setString(1, publisher.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String title = resultSet.getString(1);
                Notify notify = isInList(title, notifyList);
                if(notify != null){
                    notify.setCopySold(resultSet.getInt(3));
                } else{
                    notify = new Notify(FactoryDAO.getInstance().createGameDAO().getGame(title, publisher.getUsername()));
                    notify.setCopySold(resultSet.getInt(3));
                    notifyList.add(notify);
                }
            }
            return notifyList;
        } catch(IOException e){
            throw new NotifyException("Errore nell'apertura del file query.properties.");
        } catch(SQLException e){
            throw new NotifyException("Errore sql: "+e.getMessage());
        }
    }

    @Override
    public void addNotify(Notify notify){
        try(FileInputStream input = new FileInputStream("src/main/resources/utils/query.properties")){
            Properties prop = new Properties();
            prop.load(input);
            // AGGIUNGERE QUERY PER SAPERE QUANTE COPIE SONO STATE VENDUTE.
            Connection conn = ConnectionFactory.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(prop.getProperty("GET_GAME_NOTIFY"));
            preparedStatement.setString(1, notify.getGame().getTitle());
            preparedStatement.setString(2, notify.getGame().getPublisher().getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int prevSold = resultSet.getInt(1);
                List<Notify> publisherNotification = getPublisherNotification(notify.getGame().getPublisher());
                Notify n = isInList(notify.getGame().getTitle(), publisherNotification);
                if(n == null){
                    n = new Notify(notify.getGame());
                    n.setCopySold(prevSold+1);
                    publisherNotification.add(n);
                } else{
                    n.setCopySold(prevSold+1);
                }
                saveNotifyOnDB(n);
            }
            preparedStatement.execute();
        }catch(SQLException e){
            throw new NotifyException("Errore sql addNotify: "+e.getMessage());
        } catch(IOException e){
            throw new NotifyException("Errore nell'apertura del file query.properties.");
        }
    }

    private void saveNotifyOnDB(Notify n){
        try(FileInputStream input = new FileInputStream("src/main/resources/utils/query.properties")){
            Properties prop = new Properties();
            prop.load(input);
            Connection conn = ConnectionFactory.getInstance().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(prop.getProperty("UPDATE_GAME_NOTIFY"));
            preparedStatement.setInt(1, n.getSold());
            preparedStatement.setString(2, n.getGame().getTitle());
            preparedStatement.setString(3, n.getGame().getPublisher().getUsername());
            preparedStatement.execute();
        }catch(IOException e){
            throw new NotifyException("Errore nell'apertura del file query.properties.");
        }catch (SQLException e){
            System.out.println(n.getGame().getTitle()+n.getGame().getPublisher().getUsername());
            throw new NotifyException("Errore sql: "+e.getMessage());
        }
    }
}
