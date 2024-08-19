package com.eaut_son_it4.caculation_2.ui;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.eaut_son_it4.caculation_2.R;
import com.eaut_son_it4.caculation_2.adapter.ProductAdapter;
import com.eaut_son_it4.caculation_2.data.ProductDao;
import com.eaut_son_it4.caculation_2.model.Product;
import com.eaut_son_it4.caculation_2.util.Errors;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ProductDao productDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productDao = new ProductDao(this);
        try {
            productDao.open();
            loadProducts();
        } catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void loadProducts() {
        try {
            List<Product> products = productDao.getAllProducts();
            adapter = new ProductAdapter(products);
            recyclerView.setAdapter(adapter);
        } catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        productDao.close();
    }

}