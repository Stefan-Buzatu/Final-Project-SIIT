package CoffeeShopTests;

import Service.*;
import CoffeeShop.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class updateClientTest {
    public static void main(String[] args) throws SQLException{

        Connection connection= DriverManager.getConnection(args[0],args[1],args[2]);
        CoffeeShop coffeeShop=new CoffeeShopImpl(connection);
        UUID uuid= UUID.fromString("8fe6b758-2d79-42bd-b6aa-0bfe6cf06ff8");
        Client client=new Client(UUID.fromString("8fe6b758-2d79-42bd-b6aa-0bfe6cf06ff8"),"Alex","Down","alex_down@gmail.com","543-123-222");
        coffeeShop.updateClient(uuid,client);
    }

}
