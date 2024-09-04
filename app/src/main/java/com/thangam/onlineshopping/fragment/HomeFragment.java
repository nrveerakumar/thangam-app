package com.thangam.onlineshopping.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.thangam.onlineshopping.HomeAdapter;
import com.thangam.onlineshopping.HomeDatabase;
import com.thangam.onlineshopping.ItemClass;
import com.thangam.onlineshopping.MainActivity;
import com.thangam.onlineshopping.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    View view;
    ArrayList<ItemClass> homeArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ListView listView = view.findViewById(R.id.listView);
        HomeDatabase homeDatabase = new HomeDatabase(requireActivity().getApplicationContext());
        homeArrayList = homeDatabase.getAllDataUser();

        HomeAdapter homeAdapter = new HomeAdapter(requireActivity().getApplicationContext(), homeArrayList);
        listView.setAdapter(homeAdapter);

        return view;
    }
    public void onResume() {
        super.onResume();

        MainActivity activity = (MainActivity) requireActivity();
        TextView toolbarText = activity.findViewById(R.id.toolbarText);
        toolbarText.setText("Home");
    }
}