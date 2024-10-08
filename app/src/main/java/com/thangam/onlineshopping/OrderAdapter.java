package com.thangam.onlineshopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.thangam.onlineshopping.myclass.Order;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<Order> {
    private Context context;
    private ArrayList<Order> orders;

    public OrderAdapter(Context context, ArrayList<Order> orders) {
        super(context, 0, orders);
        this.context = context;
        this.orders = orders;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Order order = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_item, parent, false);
        }

        // Lookup view for data population
        TextView itemsTextView = convertView.findViewById(R.id.orderItemsTextView);
        TextView totalTextView = convertView.findViewById(R.id.orderTotalTextView);
        TextView addressTextView = convertView.findViewById(R.id.orderAddressTextView);
        TextView phoneTextView = convertView.findViewById(R.id.orderPhoneTextView);
        Button removeButton = convertView.findViewById(R.id.removeOrderButton);

        // Populate the data into the template view using the data object
        itemsTextView.setText(order.getItems());
        totalTextView.setText("Rs." + order.getTotalPrice());
        addressTextView.setText(order.getAddress());
        phoneTextView.setText(order.getPhone());

        // Set up the remove button
        removeButton.setOnClickListener(v -> {
            // Logic to remove the order
            OrderDatabase orderDatabase = new OrderDatabase(context);
            orderDatabase.removeOrder(order.getId());
            orders.remove(position); // Remove order from the list
            notifyDataSetChanged(); // Notify the adapter about the data change
        });

        // Return the completed view to render on screen
        return convertView;
    }
}
