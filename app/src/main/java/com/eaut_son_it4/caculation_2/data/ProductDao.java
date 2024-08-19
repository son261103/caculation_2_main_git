package com.eaut_son_it4.caculation_2.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.eaut_son_it4.caculation_2.model.Product;
import com.eaut_son_it4.caculation_2.util.Errors;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public ProductDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLiteException {
        try {
            database = dbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            Errors.handleException(e);
            throw new SQLiteException("Không thể mở kết nối đến cơ sở dữ liệu.");
        }
    }

    public void close() {
        dbHelper.close();
    }

    public long insertProduct(Product product) throws SQLiteException {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, product.getName());
        values.put(DatabaseHelper.COLUMN_UNIT_PRICE, product.getUnitPrice());
        values.put(DatabaseHelper.COLUMN_QUANTITY, product.getQuantity());
        values.put(DatabaseHelper.COLUMN_TOTAL, product.getTotal());
        values.put(DatabaseHelper.COLUMN_VAT, product.getVAT());
        values.put(DatabaseHelper.COLUMN_GRAND_TOTAL, product.getGrandTotal());

        try {
            return database.insertOrThrow(DatabaseHelper.TABLE_PRODUCTS, null, values);
        } catch (SQLiteException e) {
            Errors.handleException(e);
            throw new SQLiteException("Lỗi khi thêm sản phẩm vào cơ sở dữ liệu.");
        }
    }

    public List<Product> getAllProducts() throws SQLiteException {
        List<Product> products = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = database.query(DatabaseHelper.TABLE_PRODUCTS,
                    null, null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Product product = cursorToProduct(cursor);
                products.add(product);
                cursor.moveToNext();
            }
        } catch (SQLiteException e) {
            Errors.handleException(e);
            throw new SQLiteException("Lỗi khi truy xuất danh sách sản phẩm.");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return products;
    }

    private Product cursorToProduct(Cursor cursor) {
        Product product = new Product();
        product.setId(cursor.getLong(0));
        product.setName(cursor.getString(1));
        product.setUnitPrice(cursor.getDouble(2));
        product.setQuantity(cursor.getInt(3));
        product.setTotal(cursor.getDouble(4));
        product.setVAT(cursor.getDouble(5));
        product.setGrandTotal(cursor.getDouble(6));
        return product;
    }
}