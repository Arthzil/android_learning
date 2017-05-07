package com.example.android.justjava;
/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.add_whipped_cream);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.add_chocolate);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText clientNameBox = (EditText) findViewById(R.id.name_edit_text);
        String clientName = clientNameBox.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String orderMessage = createOrderSummary(clientName, price, hasWhippedCream, hasChocolate);

        sendEmail(clientName,orderMessage);
    }

    private void sendEmail (String clientName, String orderMessage) {
        Intent sendMessage = new Intent(Intent.ACTION_SENDTO);
        sendMessage.setData(Uri.parse("mailto:"));
        sendMessage.putExtra(Intent.EXTRA_SUBJECT, "Java Order for " + clientName);
        sendMessage.putExtra(Intent.EXTRA_TEXT, orderMessage);
        if (sendMessage.resolveActivity(getPackageManager()) != null) {
            startActivity(sendMessage);
        }
    }

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int price = 5;

        if (hasWhippedCream) {
            price += 1;
        }
        if (hasChocolate) {
            price += 2;
        }

        return quantity * price;
    }

    private String createOrderSummary(String clientName, int price, boolean hasWhippedCream, boolean hasChocolate){
        String priceMessage = getString(R.string.os_client_name, clientName);
        priceMessage += "\n" + getString(R.string.os_add_whipped_cream, hasWhippedCream);
        priceMessage += "\n" + getString(R.string.os_add_chocolate, hasChocolate);
        priceMessage += "\n" + getString(R.string.os_quantity, quantity);
        priceMessage += "\n" + getString(R.string.os_total, price);
        priceMessage += "\n"+getString(R.string.os_thank_you);
        return priceMessage;
    }

    public void increment(View view){
        if (quantity == 100) {
            return;
        }
        quantity++;
        displayQuantity(quantity);
    }

    public void decrement(View view){
        if (quantity == 1) {
            return;
        }
        quantity--;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int amount) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + amount);
    }
//    /**
//     * This method displays the given price on the screen.
//     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }
}