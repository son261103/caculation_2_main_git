package com.eaut_son_it4.caculation_2.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.eaut_son_it4.caculation_2.R;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {
    private CardView calculateCard;
    private CardView viewProductsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculateCard = findViewById(R.id.calculateCard);
        viewProductsCard = findViewById(R.id.viewProductsCard);

        calculateCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CalculationActivity.class);
            startActivity(intent);
        });

        viewProductsCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
            startActivity(intent);
        });
    }
}