import Rooms.*;

import java.util.ArrayList;
import java.util.List;

public class PriceList {
    private static PriceList priceList;
    public static final List<Room> ROOM_LIST = new ArrayList<>();
    private PriceList(){}

    public static PriceList fetchPriceList(){
        if(priceList == null){
            priceList = new PriceList();
        }
        return priceList;
    }

    public void add(String name, String type, int price, int nights, int discount){
        if("single".equals(name)){
            Single single = new Single(type, nights, price, discount);
            ROOM_LIST.add(single);
        }
    }

    public void add(String name, String type, int price){
        switch (name){
            case "doubly" :
                Doubly doubleRoom = new Doubly(type, 1, price);
                ROOM_LIST.add(doubleRoom);
                break;
            case "triple" :
                Triple triple = new Triple(type, 1, price);
                ROOM_LIST.add(triple);
                break;
            case "family" :
                Family family = new Family(type, 1, price);
                ROOM_LIST.add(family);
                break;
        }
    }
}
