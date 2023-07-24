package CoffeeShopTests;

import Service.*;
import CoffeeShop.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

public class deleteClientTest {
    public static void main(String[] args) throws SQLException{
        Connection connection= DriverManager.getConnection(args[0],args[1],args[2]);
        CoffeeShop coffeeShop=new CoffeeShopImpl(connection);
        coffeeShop.deleteClient(UUID.fromString("705b8d4a-a62a-412a-8259-74178057294b"));
    }
}
