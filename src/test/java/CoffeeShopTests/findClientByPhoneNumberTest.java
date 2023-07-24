package CoffeeShopTests;

import Service.CoffeeShop;
import Service.CoffeeShopImpl;
import CoffeeShop.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

public class findClientByPhoneNumberTest {
    public static void main(String[] args) throws SQLException {
        Connection connection= DriverManager.getConnection(args[0],args[1],args[2]);
        CoffeeShop coffeeShop=new CoffeeShopImpl(connection);
        String phoneNumber="543-123-222";
        Client client=coffeeShop.searchByPhoneNumber(phoneNumber);
        System.out.println(client);
    }
}
