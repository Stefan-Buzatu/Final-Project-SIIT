package CoffeeShop;

import java.util.UUID;

public class Client {
    private UUID clientID;
    private String clientFirstName;
    private String clientLastName;
    private String clientEmail;
    private String clientPhoneNumber;

    public Client(UUID clientID, String clientFirstName, String clientLastName, String clientEmail, String clientPhoneNumber) {
        this.clientID = clientID;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientEmail = clientEmail;
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public UUID getClientID() {
        return clientID;
    }

    public void setClientID(UUID clientID) {
        this.clientID = clientID;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientID=" + clientID +
                ", clientFirstName='" + clientFirstName + '\'' +
                ", clientLastName='" + clientLastName + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", clientPhoneNumber='" + clientPhoneNumber + '\'' +
                '}';
    }
}
