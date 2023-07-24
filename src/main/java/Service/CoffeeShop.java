package Service;

import CoffeeShop.Client;
import CoffeeShop.Entry;
import CoffeeShop.Reward;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface CoffeeShop {
    //Client methods
    void addClient(Client client) throws SQLException;
    List<Client> getClients() throws SQLException;
    void updateClient(UUID clientID,Client client) throws SQLException;
    void deleteClient(UUID clientID) throws SQLException;
    Client findClientByID(UUID clientID) throws SQLException;
    Client searchByName(String firstName,String lastName) throws SQLException;
    Client searchByPhoneNumber(String phoneNumber) throws SQLException;
    Client searchByEmail(String email) throws SQLException;

    //Entry methods
    void addEntry(Entry entry) throws SQLException;
    int numberOfEntries(UUID clientID) throws SQLException;

    //Reward methods
    List<Reward> getRewards() throws SQLException;



}
