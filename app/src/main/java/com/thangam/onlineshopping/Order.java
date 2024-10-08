package com.thangam.onlineshopping.myclass;

public class Order {
    private int id;
    private String items;
    private double totalPrice;
    private String address;
    private String phone;

    // Constructor
    public Order(int id, String items, double totalPrice, String address, String phone) {
        this.id = id;
        this.items = items;
        this.totalPrice = totalPrice;
        this.address = address;
        this.phone = phone;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    // Optional: Setters (if needed)
    public void setId(int id) {
        this.id = id;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Order ID: " + id + "\n" +
                "Items: " + items + "\n" +
                "Total Price: Rs." + totalPrice + "\n" +
                "Address: " + address + "\n" +
                "Phone: " + phone;
    }
}
