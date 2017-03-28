package com.example.piccinog.giuliosicecream;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView infoView;
    CheckBox peanutCheckBox;
    CheckBox almondCheckBox;
    CheckBox strawberryCheckBox;
    CheckBox gummyBearCheckBox;
    CheckBox mmCheckBox;
    CheckBox browniesCheckBox;
    CheckBox oreosCheckBox;
    CheckBox marshmellowsCheckBox;
    SeekBar seekbar;
    TextView seekBarTextView;
    Spinner flavorSpinner;
    Spinner sizeSpinner;
    Double amount;
    ArrayList<OrderItem> orders;



    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        orders = new ArrayList<OrderItem>();
        setTitle("Giulio's Ice Cream Shop");
        infoView = (TextView) findViewById(R.id.infoView);
        peanutCheckBox = (CheckBox) findViewById(R.id.peanutCheckBox);
        almondCheckBox = (CheckBox) findViewById(R.id.almondCheckBox);
        strawberryCheckBox = (CheckBox) findViewById(R.id.strawberryCheckBox);
        gummyBearCheckBox = (CheckBox) findViewById(R.id.gummyBearCheckBox);
        mmCheckBox = (CheckBox) findViewById(R.id.mmCheckBox);
        browniesCheckBox = (CheckBox) findViewById(R.id.browniesCheckBox);
        oreosCheckBox = (CheckBox) findViewById(R.id.oreosCheckbox);
        marshmellowsCheckBox = (CheckBox) findViewById(R.id.marshmellowsCheckBox);

        seekBarTextView = (TextView) findViewById(R.id.seekBarTextView);
        seekbar = (SeekBar) findViewById(R.id.seekbar);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarTextView.setText(Integer.toString(progress) + " oz");
                updateInfoDisplay();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        flavorSpinner = (Spinner) findViewById(R.id.flavorSpinner);
        flavorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "You've selected flavor: " + flavorSpinner.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sizeSpinner = (Spinner) findViewById(R.id.sizeSpinner);
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "You've selected size: " + sizeSpinner.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                updateInfoDisplay();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {

            Intent i = new Intent(MainActivity.this, AboutActivity.class);

            i.putExtra("DataKey", "Giulio Piccinonna");

            startActivity(i);

        }

        if (id == R.id.action_order_history) {
            Toast.makeText(this, "Order history selected", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(MainActivity.this,OrderHistoryActivity.class);
            //pass over the ice cream orders
            i.putExtra("DataKey", orders);

            startActivity(i);

        }

        return super.onOptionsItemSelected(item);
    }

    public static String getCurrentTimeStamp(){
        return new SimpleDateFormat("MM-dd-yyyy").format(new Date());
    }

    public void processSubmit(View view) {
        String toppings = "";
        String flavor = flavorSpinner.getSelectedItem().toString();
        String size = sizeSpinner.getSelectedItem().toString();
        String date = getCurrentTimeStamp();
        String amount = String.format("%1.2f", getAmount());
        amount = "$" + amount;

        if (peanutCheckBox.isChecked()){
            toppings += "Peanuts ";
        }
        if (almondCheckBox.isChecked()){
            toppings += "Almonds ";
        }
        if (strawberryCheckBox.isChecked()){
            toppings += "Strawberries ";
        }
        if (gummyBearCheckBox.isChecked()){
            toppings += "Gummy Bears  ";
        }
        if (browniesCheckBox.isChecked()){
            toppings += "Brownies ";
        }
        if (oreosCheckBox.isChecked()){
            toppings += "Oreos ";
        }
        if (marshmellowsCheckBox.isChecked()){
            toppings += "Marshmallows ";
        }
        if (mmCheckBox.isChecked()){
            toppings += "M&M's ";
        }

        if (seekbar.getProgress() > 0){
            toppings += seekbar.getProgress() + "oz Hot Fudge";
        }

        orders.add(new OrderItem(amount, date, flavor, size, toppings));
        Toast.makeText(this, "Order submitted!", Toast.LENGTH_SHORT).show();

    }

    public void processTheWorks(View view) {
        Log.d("DEBUG", "The works pressed");
        peanutCheckBox.setChecked(true);
        almondCheckBox.setChecked(true);
        strawberryCheckBox.setChecked(true);
        gummyBearCheckBox.setChecked(true);
        mmCheckBox.setChecked(true);
        browniesCheckBox.setChecked(true);
        oreosCheckBox.setChecked(true);
        marshmellowsCheckBox.setChecked(true);
        seekbar.setProgress(4);
        updateInfoDisplay();
    }

    public void processReset(View view) {
        Log.d("DEBUG", "Reset pressed");
        peanutCheckBox.setChecked(false);
        almondCheckBox.setChecked(false);
        strawberryCheckBox.setChecked(false);
        gummyBearCheckBox.setChecked(false);
        mmCheckBox.setChecked(false);
        browniesCheckBox.setChecked(false);
        oreosCheckBox.setChecked(false);
        marshmellowsCheckBox.setChecked(false);
        seekbar.setProgress(0);
        infoView.setText("$3.14");
    }

    public void updateInfoDisplay() {
        String msg;
        Double amount = 2.99;
        if (peanutCheckBox.isChecked()){
            amount += .15;
            Log.d("DEBUG", Double.toString(amount));
        }
        if (almondCheckBox.isChecked()){
            amount += .15;
        }
        if (strawberryCheckBox.isChecked()){
            amount += .2;
            Log.d("DEBUG", Double.toString(amount));
        }

        if (gummyBearCheckBox.isChecked()){
            amount += .2;
            Log.d("DEBUG", Double.toString(amount));
        }

        if (mmCheckBox.isChecked()){
            amount += .25;
        }

        if (browniesCheckBox.isChecked()){
            amount += .2;
        }

        if (oreosCheckBox.isChecked()){
            amount += .20;
        }
        if (marshmellowsCheckBox.isChecked()){
            amount += .15;
        }

        if (seekbar.getProgress() == 1){
            amount += .15;
        }
        if (seekbar.getProgress() == 2){
            amount += .25;
        }
        if (seekbar.getProgress() == 3){
            amount += .3;
        }
        if (seekbar.getProgress() == 4){
            amount += .35;
        }
        if (sizeSpinner.getSelectedItemPosition() == 1){
            amount += 1.0;

        }
        if (sizeSpinner.getSelectedItemPosition() == 2){
            amount += 2.0;

        }

        //msg = Double.toString(amount);
        msg = String.format("%1.2f", amount);
        msg = "$"  + msg;
        infoView.setText(msg);
        setAmount(amount);

    }

    public void processCheckBoxChanged(View view) {
        updateInfoDisplay();
    }

}
