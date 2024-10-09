package com.thangam.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Set the toolbar as the ActionBar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_view_order) {
            Intent viewOrdersIntent = new Intent(this, ViewOrdersActivity.class);
            startActivity(viewOrdersIntent);
            return true;
        } else if (id == R.id.menu_add_product) {
            Intent addProductIntent = new Intent(this, AddProductActivity.class);
            startActivity(addProductIntent);
            return true;
        }
        else if (id == R.id.menu_user_feedback) {
            Intent addFeedbackIntent = new Intent(this, AdminViewFeedback.class);
            startActivity(addFeedbackIntent);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}
