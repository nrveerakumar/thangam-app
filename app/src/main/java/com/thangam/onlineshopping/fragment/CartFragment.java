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

import com.thangam.onlineshopping.CartAdapter;
import com.thangam.onlineshopping.CartDatabase;
import com.thangam.onlineshopping.HomeDatabase;
import com.thangam.onlineshopping.ItemClass;
import com.thangam.onlineshopping.R;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    View view;
    ArrayList<ItemClass> cartArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        cartArrayList = new ArrayList<>();
        ListView listView = view.findViewById(R.id.listView);
        TextView textItemDesc = view.findViewById(R.id.textItemDesc);
        TextView overallTotalTextView = view.findViewById(R.id.overallTotalTextView); // New TextView for overall total
        Button pleaseOrderButton = view.findViewById(R.id.pleaseOrderButton);

        CartDatabase cartDatabase = new CartDatabase(getContext().getApplicationContext());
        cartArrayList = cartDatabase.getAllDataUser();
        if (cartArrayList.size()==0){
            textItemDesc.setText("Cart is Empty");
        }
        CartAdapter cartAdapter = new CartAdapter(getActivity().getApplicationContext(), cartArrayList, overallTotalTextView);
        HomeDatabase homeDatabase = new HomeDatabase(getActivity().getApplicationContext());

        listView.setAdapter(cartAdapter);

        pleaseOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ItemClass> cartArrayList = cartDatabase.getAllDataUser();
                for (int i = 0; i < cartArrayList.size(); i++) {
                    ItemClass itemClass = cartArrayList.get(i);

                    // Reset isCart to 0 and change color to default after placing the order
                    homeDatabase.updateIsCart(itemClass.itemName, 0, R.color.item_default_color);
                }

                Toast.makeText(getActivity().getApplicationContext(), "Your order has been successfully placed", Toast.LENGTH_SHORT).show();
                cartDatabase.clear();

                // Reload the fragment to reflect the changes
                getParentFragmentManager().beginTransaction().detach(CartFragment.this).commit();
                getParentFragmentManager().beginTransaction().attach(CartFragment.this).commit();
            }
        });

        return view;
    }
}
