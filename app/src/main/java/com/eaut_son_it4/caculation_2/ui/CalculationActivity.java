package com.eaut_son_it4.caculation_2.ui;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.eaut_son_it4.caculation_2.R;
import com.eaut_son_it4.caculation_2.data.ProductDao;
import com.eaut_son_it4.caculation_2.model.Product;
import com.eaut_son_it4.caculation_2.util.Errors;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CalculationActivity extends AppCompatActivity {
    private TextInputLayout nameInputLayout, unitPriceInputLayout, quantityInputLayout;
    private TextInputEditText nameEditText, unitPriceEditText, quantityEditText;
    private MaterialButton calculateButton;
    private ProductDao productDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        nameInputLayout = findViewById(R.id.nameInputLayout);
        unitPriceInputLayout = findViewById(R.id.unitPriceInputLayout);
        quantityInputLayout = findViewById(R.id.quantityInputLayout);
        nameEditText = findViewById(R.id.nameEditText);
        unitPriceEditText = findViewById(R.id.unitPriceEditText);
        quantityEditText = findViewById(R.id.quantityEditText);
        calculateButton = findViewById(R.id.calculateButton);

        productDao = new ProductDao(this);
        productDao.open();

        calculateButton.setOnClickListener(v -> calculateProduct());
    }

    private void calculateProduct() {
        try {
            String name = nameEditText.getText().toString();
            String unitPriceStr = unitPriceEditText.getText().toString();
            String quantityStr = quantityEditText.getText().toString();

            Errors.validateName(name);
            Errors.validatePrice(unitPriceStr);
            Errors.validateQuantity(quantityStr);

            double unitPrice = Double.parseDouble(unitPriceStr);
            int quantity = Integer.parseInt(quantityStr);

            Product product = new Product(name, unitPrice, quantity);
            product.calculateValues();

            long insertId = productDao.insertProduct(product);

            if (insertId != -1) {
                Toast.makeText(this, "Sản phẩm đã được lưu thành công", Toast.LENGTH_SHORT).show();
                clearInputs();
            } else {
                Toast.makeText(this, "Lỗi khi lưu sản phẩm", Toast.LENGTH_SHORT).show();
            }

        } catch (Errors.InvalidInputException e) {
            showError(e.getMessage());
        } catch (Exception e) {
            Errors.handleException(e);
            showError(getString(R.string.general_error));
        }
    }

    private void showError(String message) {
        if (message.contains("Tên sản phẩm")) {
            nameInputLayout.setError(message);
        } else if (message.contains("Đơn giá")) {
            unitPriceInputLayout.setError(message);
        } else if (message.contains("Số lượng")) {
            quantityInputLayout.setError(message);
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    private void clearInputs() {
        nameEditText.setText("");
        unitPriceEditText.setText("");
        quantityEditText.setText("");
        nameInputLayout.setError(null);
        unitPriceInputLayout.setError(null);
        quantityInputLayout.setError(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        productDao.close();
    }

}