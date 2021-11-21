package my.first.cashregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryPage extends AppCompatActivity implements HistoryRecyclerAdapter.HistoryItemClickListener {

    TextView managerPanelTextView;
    RecyclerView historyListRecycleView;
    ArrayList<HistoryItem> historyListFromManagerPanelBundle = new ArrayList<>();

    String purchaseDetail;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_history_page);
        Intent history_page_intent = new Intent(HistoryPage.this,PurchaseDetail.class);
        managerPanelTextView = (TextView) (findViewById(R.id.manager_panel_textview));
        historyListRecycleView = (RecyclerView) (findViewById(R.id.recyclerView));

        historyListRecycleView.setLayoutManager(new LinearLayoutManager(this));
        historyListFromManagerPanelBundle = HistoryPage.this.getIntent().getExtras().getParcelableArrayList("historyListFromManagerPanel");

        HistoryRecyclerAdapter obj = new HistoryRecyclerAdapter(HistoryPage.this,historyListFromManagerPanelBundle);
        historyListRecycleView.setAdapter(obj);
        obj.listener = this;

        System.out.println("****History list from history page");
        System.out.println(historyListFromManagerPanelBundle);

        if(historyListFromManagerPanelBundle.size()==0){
            managerPanelTextView.setText("NO HISTORY!");
        }
    }

    @Override
    public void onHistoryItemSelected(HistoryItem selectedHistoryItem) {

        //Creating string to be displayed in detail page

            purchaseDetail =  "Product :" + selectedHistoryItem.getProductName() +
                    "\n Price :" + selectedHistoryItem.getTotalPrice() +
                    "\n Purchase Date :" + selectedHistoryItem.getDate();

        Intent purchase_detail_intent = new Intent(HistoryPage.this,PurchaseDetail.class);
        purchase_detail_intent.putExtra("purchaseDetailFromHistoryArray",purchaseDetail);
        startActivity(purchase_detail_intent);

        }
}