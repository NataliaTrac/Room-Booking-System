package Rooms;

public class Doubly extends Room {

    public Doubly(String type, int nights, int price) {
        super(type, nights, price);
    }

    public Doubly(String type, int nights) {
        super(type, nights, -1);
    }

    @Override
    public String toString() {
        return "doubly" + super.toString();
    }
}
