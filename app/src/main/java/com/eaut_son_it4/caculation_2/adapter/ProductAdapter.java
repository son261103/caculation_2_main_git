package com.eaut_son_it4.caculation_2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.eaut_son_it4.caculation_2.R;
import com.eaut_son_it4.caculation_2.model.Product;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> products;

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText(String.format("Price: %.2f", product.getUnitPrice()));
        holder.quantityTextView.setText(String.format("Quantity: %d", product.getQuantity()));
        holder.totalTextView.setText(String.format("Total: %.2f", product.getTotal()));
        holder.vatTextView.setText(String.format("VAT: %.2f", product.getVAT()));
        holder.grandTotalTextView.setText(String.format("Grand Total: %.2f", product.getGrandTotal()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView, quantityTextView, totalTextView, vatTextView, grandTotalTextView;

        ProductViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            totalTextView = itemView.findViewById(R.id.totalTextView);
            vatTextView = itemView.findViewById(R.id.vatTextView);
            grandTotalTextView = itemView.findViewById(R.id.grandTotalTextView);
        }
    }
}