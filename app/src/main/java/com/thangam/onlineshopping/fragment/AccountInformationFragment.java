package com.thangam.onlineshopping.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thangam.onlineshopping.DatabaseHelper;
import com.thangam.onlineshopping.EditActivity;
import com.thangam.onlineshopping.R;
import com.thangam.onlineshopping.myclass.userDetailes;

public class AccountInformationFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account_information, container, false);
        TextView username = view.findViewById(R.id.username);
        TextView userEmail = view.findViewById(R.id.userEmail);
        TextView usernameText = view.findViewById(R.id.usernameText);
        TextView userNumber = view.findViewById(R.id.userNumber);
        TextView userEmailText = view.findViewById(R.id.userEmailText);
        TextView userAddress = view.findViewById(R.id.userAddress);
        TextView dateOfBirthday = view.findViewById(R.id.dateOfBirthday);
        CardView backToHome = view.findViewById(R.id.backToHome);
        ImageView editDetailes = view.findViewById(R.id.editDetailes);

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("userDetails", 0);
        String email = sharedPreferences.getString("email", "null");

        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity().getApplicationContext());
        userDetailes user = databaseHelper.getUser(email);
        username.setText(user.name);
        usernameText.setText(user.name);
        userEmail.setText(user.email);
        userEmailText.setText(user.email);
        userNumber.setText(user.mobile);
        userAddress.setText(user.address);
        dateOfBirthday.setText(user.birthday);

        editDetailes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditActivity.class));
            }
        });

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment homeFragment = new HomeFragment();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, homeFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}