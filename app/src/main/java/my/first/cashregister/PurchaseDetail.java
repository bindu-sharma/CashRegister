package my.first.cashregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PurchaseDetail extends AppCompatActivity {
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    String detailString;
    TextView purchaseDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_detail);

        purchaseDetail = (TextView) findViewById(R.id.purchase_detail);

        Intent purchaseDetailIntent = new Intent();
        detailString = PurchaseDetail.this.getIntent().getStringExtra("purchaseDetailFromHistoryArray");

        purchaseDetail.setText(detailString);

        System.out.print("***********"+detailString);

    }
}