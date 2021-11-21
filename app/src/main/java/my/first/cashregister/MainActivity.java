package my.first.cashregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button zero;
    Button clear;
    Button buy;
    Button manager;

    TextView productName;
    TextView quantity;
    TextView total;
    ListView productList;


    String nameOfSelectedItem;
    String priceOfSelectedItem;
    double total_price;
    int numberOfItemsInStock;
    int selectedIndex;
    int selectedQuantity;

    ArrayList<HistoryItem> historyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        one = (Button) (findViewById(R.id.one_button));
        two = (Button) (findViewById(R.id.two_button));
        three = (Button) (findViewById(R.id.three_button));
        four = (Button) (findViewById(R.id.four_button));
        five = (Button) (findViewById(R.id.five_button));
        six = (Button) (findViewById(R.id.six_button));
        seven = (Button) (findViewById(R.id.seven_button));
        eight = (Button) (findViewById(R.id.eight_button));
        nine = (Button) (findViewById(R.id.nine_button));
        zero = (Button) (findViewById(R.id.zero_button));
        clear = (Button) (findViewById(R.id.clear_button));
        buy = (Button) (findViewById(R.id.buy_button));
        manager = (Button) findViewById(R.id.manager_button);

        productList = (ListView) (findViewById(R.id.list_of_products));
        productName = (TextView) (findViewById(R.id.product_name));
        quantity = (TextView) (findViewById(R.id.quantity));
        total = (TextView) (findViewById(R.id.total_price));


        //Creating object of adapter and passing array list of items and context to it
        ProductListBaseAdapter productListAdapter = new ProductListBaseAdapter(((myApp)getApplication()).getManager().items, getApplicationContext());

        //Setting adapter for the listView
        productList.setAdapter(productListAdapter);

        // Setting click listener for clear button, it clears the quantity text view

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity.setText("");
                total.setText("");
            }
        });
// Setting click listener for Manager button, navigates to second screen, manager panel

       manager.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Bundle bundleFromMainActivity = new Bundle();
               bundleFromMainActivity.putParcelableArrayList("historyListFromMainActivity",historyList);
               Intent manager_panel_intent = new Intent(MainActivity.this, ManagerPanel.class);

               manager_panel_intent.putExtras(bundleFromMainActivity);
               startActivity(manager_panel_intent);
           }
       });



// Setting on click listener for multiple buttons
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);

        // When user selects an item in the list, Product Name text should get updated with product name

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedIndex = position;
// Here view returns the view in each cell in a row which is itself an entire layout i.e product_item

//Getting product name of the selected item in the row
                TextView item_name = view.findViewById(R.id.itemName); // storing view in a variable
                nameOfSelectedItem = (String) item_name.getText(); // getting textview in the view containing name of the product
                productName.setText(nameOfSelectedItem);  // setting the text in the Product name textview

// Getting price of the selected item
                TextView item_price = view.findViewById(R.id.itemPrice);
                priceOfSelectedItem = (String) item_price.getText();
                String savedValue = numberOfItemsInStock+"";

//  Getting number of items in stock
                TextView item_in_stock = view.findViewById(R.id.itemCount);
                numberOfItemsInStock = Integer.parseInt(item_in_stock.getText().toString());
            }
        });

//setting click listener for buy button

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity.getText()=="" || productName.getText()==""){
                    Toast.makeText(getApplicationContext(),"All fields are required!!",Toast.LENGTH_SHORT).show();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Thank you for your purchase!!");
                    builder.setMessage("Your purchase is " + quantity.getText().toString() + "\t" + productName.getText().toString());
                    //Date date = Calendar.getInstance().getTime();
                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                    String date = df.format(c);

                    HistoryItem item = new HistoryItem(((myApp)getApplication()).getManager().items.get(selectedIndex).getProductName(),
                            ((myApp)getApplication()).getManager().items.get(selectedIndex).getProductPrice(),selectedQuantity,date,
                            total_price);

                   //Adding history object to history list
                     historyList.add(item);
                    builder.setPositiveButton("OK", null);
                    // Create the Alert dialog
                    AlertDialog alertDialog = builder.create();


                    // Show the Alert Dialog box
                    alertDialog.show();

                    // Updating the stock
                    ((myApp)getApplication()).getManager().items.get(selectedIndex).productQuantity -= Integer.parseInt(quantity.getText().toString());

                    productListAdapter.notifyDataSetChanged();
                    quantity.setText("");
                    total.setText("");
                    productName.setText("");

                }

            }
        });
    }

    /* When user selects a quantity:
        -Quantity text view should get updated with selected quanity
        -Total textview should get updated
         Total = quantity*Price for each item */
    //click listen function for buttons
    @Override
    public void onClick(View v) {

        if(productName.getText()==""){// prompting user to select an item before entering quantity
            Toast.makeText(getApplicationContext(),"Select an item from the list.",Toast.LENGTH_SHORT).show();
        }
        if (quantity.getText() == "") {
            quantity.setText(((Button) v).getText().toString());
            selectedQuantity = Integer.parseInt(quantity.getText().toString());
        } else {
            quantity.setText(quantity.getText() + ((Button) v).getText().toString());
            selectedQuantity = Integer.parseInt(quantity.getText().toString());

        }

        //total_price = selectedQuantity * Float.parseFloat(priceOfSelectedItem);
        total_price = productName.getText()==""?0:
                selectedQuantity * Double.parseDouble(priceOfSelectedItem);
        total.setText(total_price + "");

        //if(selectedQuantity>numberOfItemsInStock){
        if(Integer.parseInt(quantity.getText().toString())>numberOfItemsInStock){
            Toast.makeText(getApplicationContext(),"No enough quantity in stock!!",Toast.LENGTH_SHORT).show();
            quantity.setText("");
            total.setText("");
        }

    }


}


