import Rooms.Room;
import java.util.List;

import static Utils.ListPrinting.printList;


public class WishList {

    private List<Room> list;
    private Customer customer;

    public WishList(List<Room> list, Customer customer) {
        this.list = list;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return customer.getName()+ ": " + printList(list) + "\n";
    }
}
