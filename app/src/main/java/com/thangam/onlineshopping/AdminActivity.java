package com.thangam.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AdminActivity extends AppCompatActivity {

    TextInputEditText adminEmail, adminPassword;
    TextInputLayout adminEmailLayout, adminPasswordLayout;
    Button adminLoginButton;
    AdminDatabaseHelper adminDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Initialize views and database helper
        adminEmail = findViewById(R.id.adminEmail);
        adminPassword = findViewById(R.id.adminPassword);
        adminEmailLayout = findViewById(R.id.adminEmailLayout);
        adminPasswordLayout = findViewById(R.id.adminPasswordLayout);
        adminLoginButton = findViewById(R.id.adminLoginButton);
        adminDatabaseHelper = new AdminDatabaseHelper(this);

        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAdmin();
            }
        });
    }

    private void loginAdmin() {
        String email = adminEmail.getText().toString();
        String password = adminPassword.getText().toString();

        if (validateInput()) {
            if (adminDatabaseHelper.checkAdmin(email, password)) {
                Intent intent = new Intent(getApplicationContext(), AdminDashboardActivity.class); // Redirect to Admin dashboard
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Invalid Admin Credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validateInput() {
        boolean isValid = true;

        if (adminEmail.getText() == null || adminEmail.getText().toString().isEmpty()) {
            adminEmailLayout.setError("Please enter your email");
            isValid = false;
        } else {
            adminEmailLayout.setError(null);
        }

        if (adminPassword.getText() == null || adminPassword.getText().toString().isEmpty()) {
            adminPasswordLayout.setError("Please enter your password");
            isValid = false;
        } else {
            adminPasswordLayout.setError(null);
        }

        return isValid;
    }
}
