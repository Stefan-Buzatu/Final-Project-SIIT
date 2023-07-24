package CoffeeShopTests;

import Service.*;
import CoffeeShop.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class listClientsTest {
    public static void main(String[] args) throws SQLException{
        Connection connection= DriverManager.getConnection(args[0],args[1],args[2]);
        CoffeeShop coffeeShop=new CoffeeShopImpl(connection);
        List<Client> clientList=coffeeShop.getClients();
        for (Client client:clientList) {
            System.out.println(client);
        }
    }
}
