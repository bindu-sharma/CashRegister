package my.first.cashregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ManagerPanel extends AppCompatActivity {
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    Button History;
    Button Restock;
    ArrayList<HistoryItem> historyListFromMainActivityBundle = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_panel);

        History = (Button) findViewById(R.id.history_button);
        Restock = (Button) findViewById(R.id.restock_button);

        Intent restockIntent = new Intent(ManagerPanel.this,RestockActivity.class);

        Intent manager_panel_intent = new Intent(ManagerPanel.this, HistoryPage.class);
        historyListFromMainActivityBundle = ManagerPanel.this.getIntent().getExtras().getParcelableArrayList("historyListFromMainActivity");

        System.out.println("******History List from bundle*********");
        System.out.println(historyListFromMainActivityBundle);

        Restock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(restockIntent);
            }
        });

      History.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Bundle bundleFromManagerPanel = new Bundle();
              bundleFromManagerPanel.putParcelableArrayList("historyListFromManagerPanel",historyListFromMainActivityBundle);
              manager_panel_intent.putExtras(bundleFromManagerPanel);
              startActivity(manager_panel_intent);
          }


      });


    }
}