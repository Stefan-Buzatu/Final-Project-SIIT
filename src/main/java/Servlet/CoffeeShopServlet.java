package Servlet;

import Service.CoffeeShop;
import Service.CoffeeShopImpl;
import Connection.ConnectionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/coffee_shop"})
public class CoffeeShopServlet extends HttpServlet {
    private Connection connection;
    private CoffeeShop coffeeShop;

    @Override
    public void init() throws ServletException {
        super.init();
        connection= ConnectionManager.getConnection();
        coffeeShop=new CoffeeShopImpl(connection);
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
