package CoffeeShopTests;
import Service.*;
import CoffeeShop.Client;
import CoffeeShop.Entry;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;
public class addEntryTest {
    public static void main(String[] args) throws SQLException {
        Connection connection= DriverManager.getConnection(args[0],args[1],args[2]);
        CoffeeShop coffeeShop=new CoffeeShopImpl(connection);
        Entry entry=new Entry(UUID.fromString("8fe6b758-2d79-42bd-b6aa-0bfe6cf06ff8"), Date.valueOf("2017-02-11"));
        coffeeShop.addEntry(entry);
    }

}
