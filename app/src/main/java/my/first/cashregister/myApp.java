package my.first.cashregister;

import android.app.Application;

public class myApp extends Application {
    //ProductList manager = new ProductList();
   public ProductList getManager()
   {
        return manager;
    }
    private ProductList manager = new ProductList();
}
