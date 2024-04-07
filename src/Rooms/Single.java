package Rooms;

public class Single extends Room {
    private int discountedPrice;

    public int getDiscountedPrice() {
        return discountedPrice;
    }

    public Single(String type, int nights, int price, int discountedPrice) {
        super(type, nights, price);
        this.discountedPrice = discountedPrice;
    }

    public Single(String type, int nights) {
        super(type, nights, -1);
        this.discountedPrice = -1;
    }

    @Override
    public String toString() {
        return "single" + super.toString();
    }
}





