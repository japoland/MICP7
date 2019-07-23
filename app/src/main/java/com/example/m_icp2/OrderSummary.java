package com.example.m_icp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderSummary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        Bundle extras = getIntent().getExtras();
        if (extras!= null){
            String summary = extras.getString("order");
            TextView orderSummary = (TextView)findViewById(R.id.order_view);
            orderSummary.setText(summary);
        }
    }
}
