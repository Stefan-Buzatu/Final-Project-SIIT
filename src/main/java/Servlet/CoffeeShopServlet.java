package Servlet;

import CoffeeShop.Client;
import CoffeeShop.Entry;
import CoffeeShop.Reward;
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
                addEditForm(req,resp);
                break;
            case SEARCH_CLIENT:
                req.setAttribute("action",Action.SEARCH_CLIENT);
                searchClientForm(req,resp);
                break;
            case VIEW_CLIENTS:
                req.setAttribute("action",Action.VIEW_CLIENTS);
                listClients(req,resp);
                break;
            case VIEW_REWARDS:
                req.setAttribute("action",Action.VIEW_REWARDS);
                viewRewards(req,resp);
                break;
            case EDIT_CLIENT:
                loadClient(req, resp);
                req.setAttribute("action",Action.EDIT_CLIENT);
                addEditForm(req, resp);
                break;
            case DELETE_CLIENT:
                deleteClient(req, resp);
                listClients(req, resp);
                break;
            case ADD_ENTRY:
                req.setAttribute("action",Action.ADD_ENTRY);
                addEntry(req, resp);
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
            case EDIT_CLIENT:
                editClient(req,resp);
                break;
            case SEARCH_CLIENT:
                searchClient(req,resp);
                break;
            default:
                home(req, resp);
        }
    }

    private void addClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID clientID=UUID.randomUUID();
        Client client = new Client(clientID,
                req.getParameter("first_name"),
                req.getParameter("last_name"),
                req.getParameter("email"),
                req.getParameter("phone"));
        try {
            if(client.getClientEmail()==null || client.getClientFirstName()==null || client.getClientLastName()==null || client.getClientPhoneNumber()==null)
                throw new SQLException("Fields are mandatory");
            if(client.getClientEmail().isEmpty() || client.getClientFirstName().isEmpty() || client.getClientLastName().isEmpty() || client.getClientPhoneNumber().isEmpty())
            throw new SQLException("Fields are mandatory");
            coffeeShop.addClient(client);

            long millis=System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            Entry entry=new Entry(clientID,date);
            coffeeShop.addEntry(entry);

            listClients(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.setAttribute("action", Action.ADD_CLIENT);
            addEditForm(req, resp);
        }
    }

    private void listClients(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Client> clients = coffeeShop.getClients();

            req.setAttribute("clientsList", clients);
            req.setAttribute("coffeeShop",coffeeShop);
            req.setAttribute("action_delete_client",Action.DELETE_CLIENT);
            req.setAttribute("action_edit_client",Action.EDIT_CLIENT);
            req.setAttribute("action_add_entry",Action.ADD_ENTRY);


            req.getRequestDispatcher("/jsps/ClientsList.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


    private void addEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsps/Add-EditForm.jsp").forward(req, resp);
    }
    private void searchClientForm (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/jsps/SearchClientForm.jsp").forward(req, resp);
    }

    private void viewRewards (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        try{
            List<Reward> rewards=coffeeShop.getRewards();

            req.setAttribute("rewards",rewards);

            List<String> images=coffeeShop.getImages();
            req.setAttribute("images",images);

            req.getRequestDispatcher("/jsps/ViewRewards.jsp").forward(req, resp);
        }catch (SQLException e){
            e.printStackTrace();

        }

    }



    private void home (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
    req.setAttribute("action_add_client", Action.ADD_CLIENT);
    req.setAttribute("action_search_client",Action.SEARCH_CLIENT);
    req.setAttribute("action_view_rewards",Action.VIEW_REWARDS);
    req.setAttribute("action_view_clients",Action.VIEW_CLIENTS);

    req.getRequestDispatcher("/jsps/Home.jsp").forward(req, resp);
    }catch (Exception e){
    e.printStackTrace();
    }

    }

    private void searchClient (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client;
        String searchOption;
        boolean showTable=false;

    try{
        if(req.getParameter("option")==null)
            throw new NullPointerException("Must select an option");
        if(req.getParameter("option").isEmpty())
            throw new Exception("must insert in field");

        searchOption=req.getParameter("option");
        req.setAttribute("action_add_entry",Action.ADD_ENTRY);
        req.setAttribute("action_delete_client",Action.DELETE_CLIENT);
        req.setAttribute("action_edit_client",Action.EDIT_CLIENT);
        req.setAttribute("coffeeShop",coffeeShop);
        switch (searchOption){
            case "phone":
                if(req.getParameter("phone")==null || req.getParameter("phone").isEmpty())
                throw new Exception("This field is mandatory");
                showTable=true;
                req.setAttribute("showTable",showTable);
                client=coffeeShop.searchByPhoneNumber(req.getParameter("phone"));
                req.setAttribute("client",client);
                searchClientForm(req,resp);
                break;
            case "name":
                if(req.getParameter("first_name")==null || req.getParameter("first_name").isEmpty() || req.getParameter("last_name")==null || req.getParameter("last_name").isEmpty())
                    throw new Exception("This field is mandatory");
                showTable=true;
                req.setAttribute("showTable",showTable);
                client=coffeeShop.searchByName(req.getParameter("first_name"),req.getParameter("last_name"));
                req.setAttribute("client",client);
                searchClientForm(req,resp);
                break;
            case "email":
                if(req.getParameter("email")==null || req.getParameter("email").isEmpty())
                    throw new Exception("This field is mandatory");
                showTable=true;
                req.setAttribute("showTable",showTable);
                client=coffeeShop.searchByEmail(req.getParameter("email"));
                req.setAttribute("client",client);
                searchClientForm(req,resp);
                break;
            default:
                searchClientForm(req,resp);
                break;
        }
    }catch (Exception e){
        e.printStackTrace();
        req.setAttribute("error", e.getMessage());
        searchClientForm(req,resp);
    }

    }

    private void addEntry (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try{

            UUID clientID  =UUID.fromString(req.getParameter("clientId"));


            long millis=System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);

            Entry entry=new Entry(clientID,date);
            coffeeShop.addEntry(entry);
            listClients(req,resp);
        }catch (Exception e)
        {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            listClients(req,resp);
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


    private void deleteClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String clientId = req.getParameter("clientId");
            UUID clientID = UUID.fromString(clientId);
            coffeeShop.deleteClient(clientID);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        }
    }

    private void loadClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String clientId = req.getParameter("clientId");
            UUID clientID = UUID.fromString(clientId);
            Client client = coffeeShop.findClientByID(clientID);
            req.setAttribute("client", client);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
        }
    }

    private void editClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID clientID  =UUID.fromString(req.getParameter("clientId"));
        Client client = new Client(UUID.fromString(req.getParameter("clientId")),
                req.getParameter("first_name"),
                req.getParameter("last_name"),
                req.getParameter("email"),
                req.getParameter("phone"));
        try {
            if(client.getClientEmail()==null || client.getClientFirstName()==null || client.getClientLastName()==null || client.getClientPhoneNumber()==null)
                throw new SQLException("Fields are mandatory");
            if(client.getClientEmail().isEmpty() || client.getClientFirstName().isEmpty() || client.getClientLastName().isEmpty() || client.getClientPhoneNumber().isEmpty())
                throw new SQLException("Fields are mandatory");
            coffeeShop.updateClient(clientID, client);
            listClients(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            loadClient(req, resp);
            req.setAttribute("action", Action.EDIT_CLIENT);
            req.setAttribute("error", e.getMessage());
            addEditForm(req, resp);
        }
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
