package Rooms;

public class Family extends Room {
    public Family(String type, int nights, int price) {
        super(type, nights, price);
    }

    public Family(String type, int nights) {
        super(type, nights, -1);
    }

    @Override
    public String toString() {
        return "family" + super.toString();
    }
}
