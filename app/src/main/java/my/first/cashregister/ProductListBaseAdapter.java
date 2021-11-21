package my.first.cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductListBaseAdapter extends android.widget.BaseAdapter {
    ArrayList<my.first.cashregister.ProductItem> productList = new ArrayList<>();
    Context myContext;

    ProductListBaseAdapter(ArrayList<my.first.cashregister.ProductItem> list, Context activityContext){
        productList = list;
        myContext = activityContext;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Getting the view
        convertView = LayoutInflater.from(myContext).inflate(R.layout.product_item,null);

        //Creating inflator to insert newlayout i.e product_item layout inside listview
        LayoutInflater.from(myContext).inflate(R.layout.product_item,null);
        TextView name = convertView.findViewById(R.id.itemName);
        TextView quantity = convertView.findViewById(R.id.itemCount);
        TextView price = convertView.findViewById(R.id.itemPrice);

        name.setText(productList.get(position).productName);
        quantity.setText(productList.get(position).productQuantity+"");
        price.setText(productList.get(position).productPrice+"");
        return convertView;
    }
}
