package Servlet;

import CoffeeShop.Client;
import Connection.ConnectionManager;
import Service.CoffeeShop;
import Service.CoffeeShopImpl;
import Util.Action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = getActionFromRequest(req, resp);
        switch (action){
            case ADD_CLIENT:
                req.setAttribute("action", Action.ADD_CLIENT);
                addClientForm(req,resp);
                break;
            case SEARCH_CLIENT:
                req.setAttribute("action",Action.SEARCH_CLIENT);
                searchClientForm(req,resp);
                break;
            case VIEW_CLIENTS:
                req.setAttribute("action",Action.VIEW_CLIENTS);
                listClients(req,resp);
                break;
            case ADD_ENTRY:
                req.setAttribute("action",Action.ADD_ENTRY);
                addEntryForm(req,resp);
                break;
            case VIEW_NUMBER_OF_ENTRIES_FOR_A_CLIENT:
                req.setAttribute("action",Action.VIEW_NUMBER_OF_ENTRIES_FOR_A_CLIENT);
                viewNumbersOfEntriesForm(req,resp);
                break;
            case VIEW_REWARDS:
                req.setAttribute("action",Action.VIEW_REWARDS);
                viewRewards(req,resp);
                break;
            default:
                home(req,resp);
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = getActionFromRequest(req, resp);
        switch (action){
            case ADD_CLIENT:
                addClient(req,resp);
                break;
            case SEARCH_CLIENT:
                break;
            case ADD_ENTRY:
                break;
            case VIEW_NUMBER_OF_ENTRIES_FOR_A_CLIENT:
                break;
            default:
                home(req, resp);
        }
    }

    private void addClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = new Client(UUID.randomUUID(),
                req.getParameter("first_name"),
                req.getParameter("last_name"),
                req.getParameter("email"),
                req.getParameter("phone"));
        try {
            coffeeShop.addClient(client);
            listClients(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            addClientForm(req, resp);
        }
    }

    private void listClients(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Client> clients = coffeeShop.getClients();

            req.setAttribute("clientsList", clients);

            req.getRequestDispatcher("/jsps/clients_list.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void addClientForm (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsps/addClientForm.jsp").forward(req, resp);
    }
    private void searchClientForm (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsps/searchClientForm.jsp").forward(req, resp);
    }
    private void clientsList (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsps/clients_list.jsp").forward(req, resp);
    }
    private void addEntryForm (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsps/addEntryForm.jsp").forward(req, resp);
    }
    private void viewNumbersOfEntriesForm (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsps/viewNumbersOfEntriesForm.jsp").forward(req, resp);
    }
    private void viewRewards (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsps/viewRewards.jsp").forward(req, resp);
    }



    private void home (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
    req.setAttribute("action_add_client", Action.ADD_CLIENT);
    req.setAttribute("action_search_client",Action.SEARCH_CLIENT);
    req.setAttribute("action_view_rewards",Action.VIEW_REWARDS);
    req.setAttribute("action_view_number_of_entries_for_a_client",Action.VIEW_NUMBER_OF_ENTRIES_FOR_A_CLIENT);
    req.setAttribute("action_add_entry",Action.ADD_ENTRY);
    req.setAttribute("action_view_clients",Action.VIEW_CLIENTS);

    req.getRequestDispatcher("/jsps/home.jsp").forward(req, resp);
    }catch (Exception e){
    e.printStackTrace();
    }

    }




    private Action getActionFromRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionAsString = req.getParameter("action");
        actionAsString = (actionAsString != null) ? actionAsString : "HOME";

        Action action = Action.HOME;
        try{
            action = Action.valueOf(actionAsString);
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return action;
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
