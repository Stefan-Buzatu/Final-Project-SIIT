package CoffeeShopTests;

import Service.CoffeeShop;
import Service.CoffeeShopImpl;
import CoffeeShop.Reward;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class getRewardsTest {
    public static void main(String[] args) throws SQLException {
        Connection connection= DriverManager.getConnection(args[0],args[1],args[2]);
        CoffeeShop coffeeShop=new CoffeeShopImpl(connection);
        List<Reward> rewards =coffeeShop.getRewards();
        for (Reward reward:rewards) {
            System.out.println(reward);
        }
    }
}
