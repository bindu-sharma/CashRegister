package my.first.cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<HistoryItem> purchaseHistory;

    //Creating interface for click listener for one row in the recycler view

    public interface HistoryItemClickListener{
        void onHistoryItemSelected(HistoryItem selectedHistoryItem);
    }

    public HistoryItemClickListener listener;

    // Creating viewholder i.e each row in the list

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView purchased_ItemName;
        private final TextView purchase_total;
        private final TextView purchased_quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            purchased_ItemName = itemView.findViewById(R.id.purchasedItemName);
            purchase_total = itemView.findViewById(R.id.totalPriceOfPurchasedItems);
            purchased_quantity = itemView.findViewById(R.id.quantityPurchased);
        }
//Getters for instance variables

        public TextView getPurchased_ItemName() {
            return purchased_ItemName;
        }

        public TextView getPurchase_total() {
            return purchase_total;
        }

        public TextView getPurchased_quantity() {
            return purchased_quantity;
        }
    }

    HistoryRecyclerAdapter(Context mycontext, ArrayList<HistoryItem> history){

        context = mycontext;
        purchaseHistory = history;

    }
    @NonNull
    @Override

    // Function to inflate row xml in the list xml
    public HistoryRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_item_viewholder,parent,false);
        return new ViewHolder(view);
    }

    // Function to populate the rows in the list
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.getPurchased_ItemName().setText(purchaseHistory.get(position).getProductName());
      holder.getPurchase_total().setText(purchaseHistory.get(position).getTotalPrice().toString());
       holder.getPurchased_quantity().setText(purchaseHistory.get(position).getProductQuantity()+"");

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               listener.onHistoryItemSelected(purchaseHistory.get(position));
           }
       });

    }

    @Override
    public int getItemCount() {
        return purchaseHistory.size();
    }
}
