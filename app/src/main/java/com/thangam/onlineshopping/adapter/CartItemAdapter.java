package com.thangam.onlineshopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.thangam.onlineshopping.ItemClass;
import com.thangam.onlineshopping.R;

import java.util.ArrayList;

public class CartItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ItemClass> cartItems;
    private TextView overallTotalTextView;

    public CartItemAdapter(Context context, ArrayList<ItemClass> cartItems, TextView overallTotalTextView) {
        this.context = context;
        this.cartItems = cartItems;
        this.overallTotalTextView = overallTotalTextView; // TextView for overall total
        updateOverallTotal(); // Calculate initial total when adapter is created
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.cart_list_menu, parent, false); // Adjust to your item layout
        }

        final ItemClass item = cartItems.get(position);

        ImageView itemImage = view.findViewById(R.id.cartItemImage);
        TextView itemName = view.findViewById(R.id.cartItemName);
        TextView itemDec = view.findViewById(R.id.cardItemDec);
        final TextView itemPrice = view.findViewById(R.id.cardItemPrise);
        final TextView itemQuantity = view.findViewById(R.id.cartItemQuantity);
        final TextView totalPrice = view.findViewById(R.id.totalPrice);

        Button btnIncrease = view.findViewById(R.id.btnIncrease);
        Button btnDecrease = view.findViewById(R.id.btnDecrease);

        // Set item details
        itemImage.setImageResource(item.image);
        itemName.setText(item.itemName);
        itemDec.setText(item.itemDisc);
        itemPrice.setText("Rs." + item.prise);
        itemQuantity.setText(String.valueOf(item.quantity));

        // Update total price when loading the view
        updateTotalPrice(totalPrice, item.prise, item.quantity);

        // Increase quantity button logic
        btnIncrease.setOnClickListener(v -> {
            item.quantity++;
            itemQuantity.setText(String.valueOf(item.quantity));
            updateTotalPrice(totalPrice, item.prise, item.quantity);
            updateOverallTotal(); // Update overall total when quantity changes
        });

        // Decrease quantity button logic
        btnDecrease.setOnClickListener(v -> {
            if (item.quantity > 1) { // Ensure quantity doesn't go below 1
                item.quantity--;
                itemQuantity.setText(String.valueOf(item.quantity));
                updateTotalPrice(totalPrice, item.prise, item.quantity);
                updateOverallTotal(); // Update overall total when quantity changes
            }
        });

        return view;
    }

    // Method to update the total price TextView for individual item
    private void updateTotalPrice(TextView totalPriceTextView, double pricePerItem, int quantity) {
        double total = pricePerItem * quantity;
        totalPriceTextView.setText("Total: Rs." + total);
    }

    // Method to calculate and update the overall total
    private void updateOverallTotal() {
        double overallTotal = 0;
        for (ItemClass item : cartItems) {
            overallTotal += item.prise * item.quantity;
        }
        overallTotalTextView.setText("Overall Total: Rs." + overallTotal);
    }
}
