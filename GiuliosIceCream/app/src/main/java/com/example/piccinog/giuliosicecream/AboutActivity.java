package com.example.piccinog.giuliosicecream;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    TextView aboutTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        aboutTextView = (TextView) findViewById(R.id.aboutTextView);
        setTitle("About");
        Intent i = getIntent();
        String message = i.getStringExtra("DataKey");
        aboutTextView.setText(message);

        setTitle("About");
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
