import Rooms.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
    private final List<Room> wishlist = new ArrayList<>();
    private String name;
    private int budget;
    private Basket basket;

    public Customer(String name, int budget) {
        this.name = name;
        this.budget = budget;
    }

    public void add(Room room) {
        wishlist.add(room);
    }

    public WishList fetchWishlist() {
        List<Room> tempList = new ArrayList<>();
        for (Room room : wishlist) {
            PriceList.ROOM_LIST.stream()
                    .filter(pricedRoom -> pricedRoom.getClass().getSimpleName().equals(room.getClass().getSimpleName()) && pricedRoom.getPrice() != -1) //check if room with the given name exists
                    .filter(pricedRoom -> pricedRoom.getType().equals(room.getType())) // check if such room type exists in the pricelist
                    .findAny()
                    .ifPresentOrElse(pricedRoom -> setPrice(tempList, room, pricedRoom), () -> tempList.add(room));
        }
        return new WishList(tempList, this);
    }

    private void setPrice(List<Room> tempList, Room room, Room pricedRoom) {
        if (isSingle(room, pricedRoom)) {
            Single single = (Single) PriceList.ROOM_LIST.stream()
                    .filter(pricedRoom1 -> pricedRoom1.getClass().getSimpleName().equals("Single") && pricedRoom1.getType().equals(room.getType())) //check if it's a single
                    .findAny()
                    .get();
            room.setPrice(single.getDiscountedPrice()); //if so, price with discount
        } else {
            room.setPrice(pricedRoom.getPrice()); //if not a single then normal price
        }
        tempList.add(room);
    }

    public void repack(Basket basket) {
        List<Room> toRemove = new ArrayList<>();
        for (Room room : wishlist) {
            PriceList.ROOM_LIST.stream()
                    .filter(pricedRoom -> pricedRoom.getClass().getSimpleName().equals(room.getClass().getSimpleName()) && pricedRoom.getPrice() != -1) //check if it's the right room
                    .filter(pricedRoom -> pricedRoom.getType().equals(room.getType()))
                    .findAny()
                    .ifPresentOrElse(pricedRoom -> {
                        exchange(basket, toRemove, room, pricedRoom); //if yes then exchange
                    }, () -> {
                    });
        }
        for (Room room : toRemove) {
            wishlist.remove(room);
        }
    }

    private void exchange(Basket basket, List<Room> toRemove, Room room, Room pricedRoom) {
        if (isSingle(room, pricedRoom)) {
            Single single = (Single) PriceList.ROOM_LIST.stream()
                    .filter(pricedRoom1 -> pricedRoom1.getClass().getSimpleName().equals("Single") && pricedRoom1.getType().equals(room.getType())) //check if it's a single and the right type
                    .findAny()
                    .get();
            room.setPrice(single.getDiscountedPrice()); //setting the right price with discount
        } else {
            room.setPrice(pricedRoom.getPrice()); //setting the right price without discount
        }
        basket.addToBasket(room); //adding rooms to basket
        toRemove.add(room); //determining which rooms to remove from wishlist
    }

    private boolean isSingle(Room room, Room pricedRoom) {
        return room.getClass().getSimpleName().equals("Single") && room.getNights() >= pricedRoom.getNights();
    }

    public void pay(String paymentMethod) {
        int total = 0;
        for (Room room : basket.getBasket()) {
            total += room.getPrice() * room.getNights();
        }
        if ("card".equals(paymentMethod)) {
            if (budget - total - 10 >= 0) {
                budget -= total + 10;
                basket.getBasket().clear();
                repack(basket);
            } else {
                reducePrice(paymentMethod);
            }
        } else {
            if (budget - total >= 0) {
                budget -= total;
                basket.getBasket().clear();
                repack(basket);
            } else {
                reducePrice(paymentMethod);
            }
        }
    }

    private void reducePrice(String paymentMethod) {
        Room cheapest = findCheapest();
        wishlist.stream()
                .filter(room -> room.getClass().getSimpleName().equals(cheapest.getClass().getSimpleName()) && Objects.equals(room.getType(), cheapest.getType())) //searches for the right room
                .findAny()
                .ifPresentOrElse(room -> { //if present
                    room.setNights(room.getNights() + 1); //adds one night to the wishlist
                    cheapest.setNights(cheapest.getNights() - 1); //reduces nights in the basket by one
                }, () -> reAddRoom(cheapest)); //if not present
        pay(paymentMethod);
    }

    private void reAddRoom(Room cheapest) {
        cheapest.setNights(cheapest.getNights() - 1);
        switch (cheapest.getClass().getSimpleName()) {
            case "Single" -> {
                Room newRoom = new Single(cheapest.getType(), 1);
                wishlist.add(newRoom);
            }
            case "Doubly" -> {
                Room newRoom = new Doubly(cheapest.getType(), 1, cheapest.getPrice());
                wishlist.add(newRoom);
            }
            case "Triple" -> {
                Room newRoom = new Triple(cheapest.getType(), 1, cheapest.getPrice());
                wishlist.add(newRoom);
            }
            case "Family" -> {
                Room newRoom = new Family(cheapest.getType(), 1, cheapest.getPrice());
                wishlist.add(newRoom);
            }
        }
    }

    private Room findCheapest() {
        int price = Integer.MAX_VALUE;
        Room cheapest = null;
        for (Room room : basket.getBasket()) {
            if (room.getPrice() < price && room.getNights() > 0) {
                price = room.getPrice();
                cheapest = room;
            }
        }
        return cheapest;
    }

    public int fetchWallet() {
        return budget;
    }

    public Basket fetchBasket() {
        return basket;
    }

    public String getName() {
        return name;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
