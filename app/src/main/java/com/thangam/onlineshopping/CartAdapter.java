package com.thangam.onlineshopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    Context context;
    ArrayList<ItemClass> cartArrayList;

    public CartAdapter(Context context, ArrayList<ItemClass> cartArrayList){
        this.context = context;
        this.cartArrayList = cartArrayList;
    }

    @Override
    public int getCount() {
        return cartArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartArrayList.get(position);
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
            view = inflater.inflate(R.layout.cart_list_menu, parent, false);
        }

        final ItemClass item = cartArrayList.get(position);

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
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.quantity++;
                itemQuantity.setText(String.valueOf(item.quantity));
                updateTotalPrice(totalPrice, item.prise, item.quantity);
            }
        });

        // Decrease quantity button logic
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.quantity > 1) { // Ensure quantity doesn't go below 1
                    item.quantity--;
                    itemQuantity.setText(String.valueOf(item.quantity));
                    updateTotalPrice(totalPrice, item.prise, item.quantity);
                }
            }
        });

        return view;
    }

    // Method to update the total price TextView
    private void updateTotalPrice(TextView totalPriceTextView, double pricePerItem, int quantity) {
        double total = pricePerItem * quantity;
        totalPriceTextView.setText("Total: Rs." + total);
    }
}
