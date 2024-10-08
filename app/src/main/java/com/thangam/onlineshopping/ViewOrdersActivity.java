package com.thangam.onlineshopping;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.thangam.onlineshopping.myclass.Order;

import java.util.ArrayList;

public class ViewOrdersActivity extends AppCompatActivity {

    private ListView orderListView;
    private ArrayList<Order> orderList;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        // Initialize the ListView
        orderListView = findViewById(R.id.orderListView);
        orderList = new ArrayList<>();

        // Retrieve orders from the database
        OrderDatabase orderDatabase = new OrderDatabase(this);
        orderList = orderDatabase.getAllOrders();

        // Set up the adapter to display the orders
        orderAdapter = new OrderAdapter(this, orderList);
        orderListView.setAdapter(orderAdapter);
    }
}
