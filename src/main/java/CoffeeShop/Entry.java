package CoffeeShop;

import java.sql.Date;
import java.util.UUID;

public class Entry {
    private UUID clientID;
    private java.util.Date date;

    public Entry(UUID clientID, Date date) {
        this.clientID = clientID;
        this.date = date;
    }

    public UUID getClientID() {
        return clientID;
    }

    public void setClientID(UUID clientID) {
        this.clientID = clientID;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
