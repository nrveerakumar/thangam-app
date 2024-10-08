package com.thangam.onlineshopping.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.thangam.onlineshopping.CartDatabase;
import com.thangam.onlineshopping.HomeDatabase;
import com.thangam.onlineshopping.ItemClass;
import com.thangam.onlineshopping.R;
import com.thangam.onlineshopping.OrderDatabase;
import com.thangam.onlineshopping.adapter.CartItemAdapter; // Import the custom adapter
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    private Button pleaseOrderButton;
    private TextView overallTotalTextView;
    private ListView listView;
    private ArrayList<ItemClass> cartArrayList; // Define cartArrayList
    private CartItemAdapter cartItemAdapter; // Declare the custom adapter

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        listView = view.findViewById(R.id.listView);
        pleaseOrderButton = view.findViewById(R.id.pleaseOrderButton);
        overallTotalTextView = view.findViewById(R.id.overallTotalTextView);

        // Initialize the cartArrayList with items from the database
        cartArrayList = new ArrayList<>();
        CartDatabase cartDatabase = new CartDatabase(getContext());
        cartArrayList = cartDatabase.getCartItems(); // Assuming this method fetches items from the cart

        // Set up the adapter and bind it to the ListView
        CartItemAdapter adapter = new CartItemAdapter(getContext(), cartArrayList, overallTotalTextView);
        listView.setAdapter(adapter);

        // Calculate total price and display it
        double overallTotal = calculateTotal();
        overallTotalTextView.setText("Overall Total: Rs." + overallTotal);

        // Set onClick listener for the 'Place Order' button
        pleaseOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a dialog to input phone number and address
                showPlaceOrderDialog();
            }
        });

        return view;
    }

    // Function to calculate the overall total
    private double calculateTotal() {
        double total = 0.0;
        for (ItemClass item : cartArrayList) {
            total += item.prise * item.quantity;
        }
        return total;
    }

    // Show dialog for collecting phone number and address
    private void showPlaceOrderDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Enter Address and Phone Number");

        // Create a layout to hold the EditTexts
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);

        // Create EditText for phone number
        final EditText phoneInput = new EditText(getActivity());
        phoneInput.setHint("Enter your phone number");
        phoneInput.setInputType(android.text.InputType.TYPE_CLASS_PHONE);
        layout.addView(phoneInput);

        // Create EditText for address
        final EditText addressInput = new EditText(getActivity());
        addressInput.setHint("Enter your address");
        layout.addView(addressInput);

        builder.setView(layout);

        // Set Submit button and handle data submission
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String phone = phoneInput.getText().toString();
                String address = addressInput.getText().toString();

                if (phone.isEmpty() || address.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter both phone number and address", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle order submission here
                    submitOrder(phone, address);
                    dialog.dismiss();
                }
            }
        });

        // Set Cancel button to dismiss the dialog
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    // Submit order logic and save it to the database
    private void submitOrder(String phone, String address) {
        try {
            // Collect the list of items and their quantities from the cart
            StringBuilder itemsList = new StringBuilder();
            double overallTotal = 0.0;

            // Get an instance of HomeDatabase
            HomeDatabase homeDatabase = new HomeDatabase(getContext());

            for (ItemClass item : cartArrayList) {
                itemsList.append(item.itemName)
                        .append(" (Quantity: ")
                        .append(item.quantity)
                        .append("), ");
                overallTotal += item.prise * item.quantity; // Calculate overall total price

                // Update the quantity in the home database to 0
                homeDatabase.updateIsCart(item.itemName, 0, R.color.item_default_color); // Assuming you have an update method
            }

            // Remove trailing comma and space
            if (itemsList.length() > 0) {
                itemsList.setLength(itemsList.length() - 2);
            }

            // Save the order to the database
            OrderDatabase orderDatabase = new OrderDatabase(getContext());
            boolean success = orderDatabase.addOrder(phone, address, itemsList.toString(), overallTotal);

            if (success) {
                Toast.makeText(getActivity(), "Order placed successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Failed to place order", Toast.LENGTH_SHORT).show();
            }

            // Clear the cart after placing the order
            CartDatabase cartDatabase = new CartDatabase(getContext());
            cartDatabase.clear();

            // Update the UI
            overallTotalTextView.setText("Overall Total: Rs.0");
            getParentFragmentManager().beginTransaction().detach(CartFragment.this).commit();
            getParentFragmentManager().beginTransaction().attach(CartFragment.this).commit();
        } catch (Exception e) {
            // Log the error and show a user-friendly message
            e.printStackTrace(); // Logcat will display this
            Toast.makeText(getActivity(), "An error occurred: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
