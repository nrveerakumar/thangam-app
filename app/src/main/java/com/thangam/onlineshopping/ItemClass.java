package com.thangam.onlineshopping;

public class ItemClass {
    public String itemName;
    public String itemDisc;
    public int prise;
    public int image;
    public int itemCartColor;
    public int isCart;
    public int quantity; // New field to store quantity

    // Constructor with quantity parameter
    public ItemClass(String itemName, String itemDisc, int prise, int image, int itemCartColor, int isCart, int quantity) {
        this.itemName = itemName;
        this.itemDisc = itemDisc;
        this.prise = prise;
        this.image = image;
        this.itemCartColor = itemCartColor;
        this.isCart = isCart;
        this.quantity = quantity; // Initialize quantity
    }

    // Getters and setters (if needed)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void setCartColor(int itemCartColor) {
        this.itemCartColor = itemCartColor;
    }
}
