import Rooms.Room;

import java.util.ArrayList;
import java.util.List;

import static Utils.ListPrinting.printList;

public class Basket {

    private Customer customer;
    private final List<Room> rooms = new ArrayList<>();

    public Basket(Customer customer) {
        this.customer = customer;
        this.customer.setBasket(this);
    }

    public void addToBasket(Room room) {
        rooms.add(room);
    }

    public List<Room> getBasket() {
        return rooms;
    }

    @Override
    public String toString() {
        return printList(rooms) + "\n";
    }
}
