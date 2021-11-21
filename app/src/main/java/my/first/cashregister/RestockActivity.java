package my.first.cashregister;

import androidx.activity.result.ActivityResultCaller;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RestockActivity extends AppCompatActivity {
    Button save;
    Button cancel;
    EditText enterQuantity;
    ListView restockList;
    ProductListBaseAdapter Adapter;
    int indexSelected;
    int numberOfItemsInStock;
    int quantityEntered;
    int itemCountInStock;
    int updatedStock;
    ArrayList<ProductItem> restockActivityList = new ArrayList<>();
    //ProductList obj = new ProductList();


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);

        save = (Button) findViewById(R.id.save_button);
        cancel = (Button) findViewById(R.id.cancel);
        enterQuantity = (EditText) findViewById(R.id.editText);
        restockList = (ListView) findViewById(R.id.restock_list);


        restockActivityList = ((myApp)getApplication()).getManager().items;
        Adapter = new ProductListBaseAdapter(restockActivityList,RestockActivity.this);
        //Setting adapter for the listView
        restockList.setAdapter(Adapter);

        //Click listener for cancel button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        restockList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                indexSelected = position;
// Here view returns the view in each cell in a row which is itself an entire layout i.e product_item
                itemCountInStock = restockActivityList.get(position).productQuantity;
                System.out.println("item count in stock"+itemCountInStock);
                System.out.println("*************************");
                System.out.println(restockActivityList.get(position).productName+"selected");
                quantityEntered = Integer.parseInt(enterQuantity.getText().toString());
                updatedStock = itemCountInStock + quantityEntered;
                System.out.println("*************************");
                System.out.println(updatedStock);
            }
        });


        //Click Listener for save button - update inventory
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TextView item_in_stock = findViewById(R.id.itemCount);
//                item_in_stock.setText(numberOfItemsInStock);
                if(enterQuantity.getText().toString()==""){
                    Toast.makeText(RestockActivity.this, "Enter quantity!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    restockActivityList.get(indexSelected).setProductQuantity(updatedStock);
                    ((myApp)getApplication()).getManager().items.get(indexSelected).productQuantity = updatedStock;
                    //obj.productList.getAdapter().notify();
                    System.out.println("*************************");
                    System.out.println(restockActivityList.get(indexSelected).productQuantity + "updated Quantity");
                    System.out.println("Main Activity value"+((myApp)getApplication()).getManager().items.get(indexSelected).productQuantity);

                    Adapter.notifyDataSetChanged();
                   enterQuantity.setText("");

                   Intent myIntent = new Intent(RestockActivity.this, MainActivity.class);
                    startActivity(myIntent);
                }

            }
        });

    }
}