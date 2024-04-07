# Room Booking System

This document provides a comprehensive overview and technical guide for a Room Booking System implemented in Java. The system allows users to book rooms of various types and calculate the total price based on room type, duration, and predefined pricing criteria.

## Project Overview
The Room Booking System is designed to manage room bookings for a hotel or similar establishment. It includes functionalities to add rooms to a wishlist, transfer them to a shopping cart (basket), and calculate the total cost. The system accommodates discounts based on the length of stay and room type.

### System Components
- **Room Classes (`Pokoje` package)**: Defines different room types, such as **`Jedynka`** (Single), **`Dwojka`** (Double), **`Trojka`** (Triple), and **`Rodzina`** (Family). Each class extends the ---+ --- **`Pokoj`** (Room) base class, specifying room type, nights stayed, and pricing.
- **Pricing (`Cennik` class)**: Manages the price list, offering functionalities to add new room prices with or without discounts based on the stay's duration.
- **Client (`Klient` class)**: Represents a client making a booking, including wishlist management, budget tracking, and payment processing.
- **Basket (`Koszyk` class)**: Represents a shopping basket to which rooms from the wishlist can be transferred for booking.
- **Wishlist (`ListaZyczen` class)**: A collection of rooms a client wishes to book.

### Key Features
- **Dynamic Pricing** - Supports dynamic pricing based on room type and stay duration, with discounts for longer stays.
- **Room Wishlist and Basket** - Allows clients to add rooms to a wishlist, transfer them to a shopping basket, and calculate the total cost.
- **Payment Processing** - Handles payments, adjusting the client's budget accordingly and clearing the basket upon successful payment.

## Installation and Setup
To set up the Room Booking System, ensure you have Java installed on your system. Download the project files to your local machine and compile the Java classes.
```bash
javac Pokoje/*.java BookingTest.java Cennik.java Koszyk.java ListaZyczen.java Klient.java
```
Run the main application **(`BookingTest`)** to start the system.
```bash
java BookingTest
```

## Usage
The system's entry point is in the **(`BookingTest`)** class, which demonstrates adding rooms to a client's wishlist, transferring rooms to the shopping basket, calculating prices based on room type and stay duration, and processing payments.

Clients can perform the following actions:
-Add rooms of various types to their wishlist.
-Transfer wishlist items to their shopping basket.
-Calculate the total price for rooms in the basket.
-Make payments, which are processed based on the specified payment method.


