package Rooms;

public class Triple extends Room{
    public Triple(String type, int nights, int price) {
        super(type, nights, price);
    }

    public Triple(String type, int nights) {
        super(type, nights, -1);
    }

    @Override
    public String toString() {
        return "triple" + super.toString();
    }
}

