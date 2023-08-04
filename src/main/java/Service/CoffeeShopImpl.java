package Service;

import CoffeeShop.Client;
import CoffeeShop.Entry;
import CoffeeShop.Reward;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CoffeeShopImpl implements CoffeeShop{
    private Connection dbConnection;

    public CoffeeShopImpl(Connection connection){
        this.dbConnection = connection;
    }
    @Override
    public void addClient(Client client) throws SQLException {
        String insertStatement = "INSERT INTO clients VALUES (?, ?, ?, ?, ?)";
        PreparedStatement createClientStatement = dbConnection.prepareStatement(insertStatement);
        createClientStatement.setObject(1, client.getClientID());
        createClientStatement.setString(2, client.getClientFirstName());
        createClientStatement.setString(3, client.getClientLastName());
        createClientStatement.setString(4, client.getClientEmail());
        createClientStatement.setString(5, client.getClientPhoneNumber());
        createClientStatement.executeUpdate();
    }

    @Override
    public List<Client> getClients() throws SQLException {
        Statement getClientsStatement = dbConnection.createStatement();
        ResultSet clientsResultSet = getClientsStatement.executeQuery("SELECT * from clients");
        List<Client> clientList = new ArrayList<>();
        while(clientsResultSet.next()){
            UUID clientId = UUID.fromString(clientsResultSet.getObject("client_id").toString());
            String clientFirstName = clientsResultSet.getString(2);
            String clientLastName = clientsResultSet.getString(3);
            String clientEmail = clientsResultSet.getString("client_email");
            String clientPhone = clientsResultSet.getString(5);

            clientList.add(new Client(clientId,clientFirstName,clientLastName,clientEmail,clientPhone));
        }
        return clientList;
    }


    @Override
    public void updateClient(UUID clientID, Client client) throws SQLException {
        String updateStatement = "UPDATE clients SET client_first_name = ?, client_last_name = ?, client_email = ?, client_phone_number = ? WHERE client_id = ?";
        PreparedStatement updateClientStatement = dbConnection.prepareStatement(updateStatement);
        updateClientStatement.setString(1, client.getClientFirstName());
        updateClientStatement.setString(2, client.getClientLastName());
        updateClientStatement.setString(3, client.getClientEmail());
        updateClientStatement.setString(4, client.getClientPhoneNumber());
        updateClientStatement.setObject(5, clientID);

        updateClientStatement.executeUpdate();
    }


    @Override
    public void deleteClient(UUID clientID) throws SQLException {
        PreparedStatement deleteClientStatement = dbConnection.prepareStatement("DELETE from clients WHERE client_id = ?");
        deleteClientStatement.setObject(1, clientID);
        deleteClientStatement.executeUpdate();
    }

    @Override
    public Client findClientByID(UUID clientID) throws SQLException {
        PreparedStatement findClientByIdStatement = dbConnection.prepareStatement("SELECT * from clients WHERE client_id = ?");
        findClientByIdStatement.setObject(1, clientID);
        ResultSet clientResultSet = findClientByIdStatement.executeQuery();
        clientResultSet.next();

        return searchByResultSet(clientResultSet);
    }

    @Override
    public Client searchByName(String firstName, String lastName) throws SQLException {
        PreparedStatement findClientByNameStatement = dbConnection.prepareStatement("SELECT * from clients WHERE (client_first_name ilike ?) AND (client_last_name ilike ?)");
        findClientByNameStatement.setString(1,firstName);
        findClientByNameStatement.setString(2,lastName);
        ResultSet clientResultSet = findClientByNameStatement.executeQuery();
        clientResultSet.next();

        return searchByResultSet(clientResultSet);
    }

    @Override
    public Client searchByPhoneNumber(String phoneNumber) throws SQLException {
        PreparedStatement findClientByIdStatement = dbConnection.prepareStatement("SELECT * from clients WHERE client_phone_number = ?");
        findClientByIdStatement.setObject(1, phoneNumber);
        ResultSet clientResultSet = findClientByIdStatement.executeQuery();
        clientResultSet.next();

        return searchByResultSet(clientResultSet);
    }

    @Override
    public Client searchByEmail(String email) throws SQLException {
        PreparedStatement findClientByIdStatement = dbConnection.prepareStatement("SELECT * from clients WHERE client_email = ?");
        findClientByIdStatement.setObject(1, email);
        ResultSet clientResultSet = findClientByIdStatement.executeQuery();
        clientResultSet.next();

        return searchByResultSet(clientResultSet);
    }

    @Override
    public int numberOfEntries(UUID clientID) throws SQLException {
        PreparedStatement numberOfEntriesByIdStatement = dbConnection.prepareStatement("SELECT COUNT(client_id) from entries WHERE client_id = ?");
        numberOfEntriesByIdStatement.setObject(1,clientID);
        ResultSet clientResultSet=numberOfEntriesByIdStatement.executeQuery();
        clientResultSet.next();
        return clientResultSet.getInt(1);
    }

    @Override
    public List<Reward> getRewards() throws SQLException {
        Statement getRewardsStatement = dbConnection.createStatement();
        ResultSet rewardsResultSet = getRewardsStatement.executeQuery("SELECT * from rewards");
        List<Reward> rewardList = new ArrayList<>();
        while(rewardsResultSet.next()){
            UUID rewardId = UUID.fromString(rewardsResultSet.getObject(1).toString());
            String reward=rewardsResultSet.getString(2);
            int numberOfEntries=rewardsResultSet.getInt(3);

            rewardList.add(new Reward(rewardId,reward,numberOfEntries));
        }
        return rewardList;
    }

    @Override
    public List<String> getImages(){
        List<String> images=new ArrayList<>();

        images.add("americano.jpg");
        images.add("vanilla_cake.jpg");
        images.add("chocolate_cake.jpg");
        images.add("chocolate_ice_cream.jpg");
        images.add("vanilla_ice_cream.jpg");
        images.add("black_coffee.jpg");
        images.add("cappuccino.jpg");
        images.add("latte.jpg");
        images.add("espresso.jpg");
        images.add("croissant.jpg");

        return images;
    }

    @Override
    public void addEntry(Entry entry) throws SQLException {
        String insertStatement = "INSERT INTO entries VALUES (?, ?)";
        PreparedStatement createEntryStatement = dbConnection.prepareStatement(insertStatement);
        createEntryStatement.setObject(1, entry.getClientID());
        createEntryStatement.setDate(2, new java.sql.Date(entry.getDate().getTime()));

        createEntryStatement.executeUpdate();
    }

    public Client searchByResultSet(ResultSet clientResultSet) throws SQLException {
        UUID clientId = UUID.fromString(clientResultSet.getObject("client_id").toString());
        String clientFirstName = clientResultSet.getString(2);
        String clientLastName = clientResultSet.getString(3);
        String clientEmail = clientResultSet.getString("client_email");
        String clientPhone = clientResultSet.getString(5);
        return new Client(clientId,clientFirstName,clientLastName,clientEmail,clientPhone);
    }

}
