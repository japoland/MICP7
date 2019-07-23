package com.example.m_icp2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final int PIZZA_PRICE = 10;
    final int ONION_PRICE = 1;
    final int MUSHROOM_PRICE = 1;
    final int OLIVE_PRICE = 1;
    final int PEPPER_PRICE = 1;
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

       /* Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:47.6,-122.3"));
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }*/

//        get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();
//        check if onions is selected
        CheckBox onions = (CheckBox) findViewById(R.id.onions_checked);
        boolean hasOnions = onions.isChecked();
//        check if mushrooms is selected
        CheckBox mushrooms = (CheckBox) findViewById(R.id.mushrooms_checked);
        boolean hasMushrooms = mushrooms.isChecked();
//        check if olives is selected
        CheckBox olives = (CheckBox) findViewById(R.id.olives_checked);
        boolean hasOlives = olives.isChecked();
//        check if peppers is selected
        CheckBox peppers = (CheckBox) findViewById(R.id.peppers_checked);
        boolean hasPeppers = peppers.isChecked();
//        calculate and store the total price
        float totalPrice = calculatePrice(hasOnions, hasMushrooms, hasOlives, hasPeppers);
//        create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasOnions, hasMushrooms, hasOlives, hasPeppers, totalPrice);
// Write the relevant code for making the buttons work(i.e implement the implicit and explicit intents

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","japolandkc@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "You pizza order has been placed");
        emailIntent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(userInputName, hasOnions, hasMushrooms, hasOlives, hasPeppers, totalPrice));
        startActivity(Intent.createChooser(emailIntent, "Send email..."));

    }
    private String boolToString(boolean bool){
        return bool?(getString(R.string.yes)):(getString(R.string.no));

    }
    public void viewSummary (View view){
        //        get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();
//        check if onions is selected
        CheckBox onions = (CheckBox) findViewById(R.id.onions_checked);
        boolean hasOnions = onions.isChecked();
//        check if mushrooms is selected
        CheckBox mushrooms = (CheckBox) findViewById(R.id.mushrooms_checked);
        boolean hasMushrooms = mushrooms.isChecked();
//        check if olives is selected
        CheckBox olives = (CheckBox) findViewById(R.id.olives_checked);
        boolean hasOlives = olives.isChecked();
//        check if peppers is selected
        CheckBox peppers = (CheckBox) findViewById(R.id.peppers_checked);
        boolean hasPeppers = peppers.isChecked();
//        calculate and store the total price
        float totalPrice = calculatePrice(hasOnions, hasMushrooms, hasOlives, hasPeppers);
        String summaryMessage = createOrderSummary(userInputName, hasOnions, hasMushrooms, hasOlives, hasPeppers, totalPrice);

        Intent intent = new Intent(MainActivity.this, OrderSummary.class);
        intent.putExtra("order", summaryMessage);
        startActivity(intent);
    }


    private String createOrderSummary(String userInputName, boolean hasOnions, boolean hasMushrooms, boolean hasOlives, boolean hasPeppers, float price) {
        String orderSummaryMessage = getString(R.string.order_summary_name,userInputName) +"\n"+
                getString(R.string.order_summary_onions,boolToString(hasOnions))+"\n"+
                getString(R.string.order_summary_mushrooms,boolToString(hasMushrooms)) +"\n"+
                getString(R.string.order_summary_olives,boolToString(hasOlives)) +"\n"+
                getString(R.string.order_summary_peppers,boolToString(hasPeppers)) +"\n"+
                getString(R.string.order_summary_quantity,quantity)+"\n"+
                getString(R.string.order_summary_total_price,price) +"\n"+
                getString(R.string.thank_you);
        return orderSummaryMessage;

    }


    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasOnions, boolean hasMushrooms, boolean hasOlives, boolean hasPeppers) {
        int basePrice = PIZZA_PRICE;
        if (hasOnions) {
            basePrice += ONION_PRICE;
        }
        if (hasMushrooms) {
            basePrice += MUSHROOM_PRICE;
        }
        if (hasOlives) {
            basePrice += OLIVE_PRICE;
        }
        if (hasPeppers) {
            basePrice += PEPPER_PRICE;
        }

        return quantity * basePrice;

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }


    /**
     * This method increments the quantity of pizza by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {

            Log.i("MainActivity", "Please select less than one hundred pizzas");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_pizza);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;

        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {

            Log.i("MainActivity", "Please select at least one pizza");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_pizza);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;


        }
    }

    public void showCalendar(View view) {
        Intent intent = new Intent(MainActivity.this, CalendarView.class);
        //intent.putExtra("order", summaryMessage);
        startActivity(intent);
    }
}

