package my.first.cashregister;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;

public class ProductItem  extends AppCompatActivity implements Parcelable {

    String productName;
    Double productPrice;
    int productQuantity;

    ProductItem(String name, Double price, int quantity){
        this.productName = name;
        this.productPrice = price;
        this.productQuantity = quantity;
    }

public ProductItem(Parcel in) {
    productName = in.readString();
        productPrice = in.readDouble();
    productQuantity = in.readInt();
}


    @Override
    public String toString() {
        return "ProductItem{" +
                "productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productQuantity=" + productQuantity +
                '}';
    }

    public static final Creator<ProductItem> CREATOR = new Creator<ProductItem>() {
        @Override
        public ProductItem createFromParcel(Parcel in) {
            return new ProductItem(in);
        }

        @Override
        public ProductItem[] newArray(int size) {
            return new ProductItem[size];
        }
    };

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
            dest.writeDouble(productPrice);
        dest.writeInt(productQuantity);
    }

}
