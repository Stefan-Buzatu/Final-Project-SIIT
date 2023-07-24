package CoffeeShopTests;

import Service.CoffeeShop;
import Service.CoffeeShopImpl;
import CoffeeShop.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

public class findClientByNameTest {
    public static void main(String[] args) throws SQLException {
        Connection connection= DriverManager.getConnection(args[0],args[1],args[2]);
        CoffeeShop coffeeShop=new CoffeeShopImpl(connection);
        String firstName="alex";
        String lastName="Down";
        Client client=coffeeShop.searchByName(firstName,lastName);
        System.out.println(client);
    }
}
