package CoffeeShopTests;

import Service.CoffeeShop;
import Service.CoffeeShopImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

public class numberOfEntriesTest {
    public static void main(String[] args) throws SQLException {
        Connection connection= DriverManager.getConnection(args[0],args[1],args[2]);
        CoffeeShop coffeeShop=new CoffeeShopImpl(connection);
        UUID uuid= UUID.fromString("8fe6b758-2d79-42bd-b6aa-0bfe6cf06ff8");
        System.out.println(coffeeShop.numberOfEntries(uuid));

    }
}
