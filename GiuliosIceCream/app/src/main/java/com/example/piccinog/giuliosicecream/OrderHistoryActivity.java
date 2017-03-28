package com.example.piccinog.giuliosicecream;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    ArrayList<OrderItem> order;
    ArrayList<String> stringOrders;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        setTitle("Order History");
        Intent i = getIntent();
        order = (ArrayList<OrderItem>) i.getSerializableExtra("DataKey");
        Log.d("DEBUG", order.toString());

        stringOrders = new ArrayList<String>();
        for (OrderItem item : order) {
            stringOrders.add(item.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                stringOrders);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //app icon in action bar clicked; go2 parent activity
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
