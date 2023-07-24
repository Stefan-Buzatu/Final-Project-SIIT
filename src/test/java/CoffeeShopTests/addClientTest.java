package CoffeeShopTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Service.*;
import CoffeeShop.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

public class addClientTest {
    public static void main(String[] args) throws SQLException {
        Connection connection= DriverManager.getConnection(args[0],args[1],args[2]);
        CoffeeShop coffeeShop=new CoffeeShopImpl(connection);
        Client client=new Client(UUID.randomUUID(),"John","Doe","john_doe@gmail.com","123-123-123");
        coffeeShop.addClient(client);
    }



}
