package my.first.cashregister;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class HistoryItem extends ProductItem implements Parcelable {
    private String date;
   private Double totalPrice;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @NonNull
    public String toString(){
        return "[" + getProductName() + "," + getProductQuantity() + "," + getProductPrice()
                 + "," + getDate() + "," + getTotalPrice() + "]";
    }

    HistoryItem(String name, Double price, int quantity, String date, Double totalPrice) {
        super(name, price, quantity);
        this.date = date;
        this.totalPrice = totalPrice;

    }

    protected HistoryItem(Parcel in) {
        super(in);
        date = in.readString();
        totalPrice = in.readDouble();
        }

    public static final Creator<HistoryItem> CREATOR = new Creator<HistoryItem>() {
        @Override
        public HistoryItem createFromParcel(Parcel in) {
            return new HistoryItem(in);
        }

        @Override
        public HistoryItem[] newArray(int size) {
            return new HistoryItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest,flags);
        dest.writeString(date);
            dest.writeDouble(totalPrice);
        }

}
