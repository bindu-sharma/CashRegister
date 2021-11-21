package my.first.cashregister;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProductList{


    ListView productList;
    ArrayList<my.first.cashregister.ProductItem> items = new ArrayList<>();

    ProductList(){
        // Creating Product item
        my.first.cashregister.ProductItem pants = new my.first.cashregister.ProductItem("Pants", 40.99, 10);
        my.first.cashregister.ProductItem shoes = new my.first.cashregister.ProductItem("Shoes", 100.99, 100);
        my.first.cashregister.ProductItem hats = new my.first.cashregister.ProductItem("Hats", 59.99, 30);

        // Adding product items to arraylist
        items.add(pants);
        items.add(shoes);
        items.add(hats);
    }

}


