package com.example.hitcapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ImageView productImage = findViewById(R.id.detail_product_image);
        TextView productName = findViewById(R.id.detail_product_name);
        TextView productPrice = findViewById(R.id.detail_product_price);
        TextView productDescription = findViewById(R.id.detail_product_description);
        Button buyButton = findViewById(R.id.detail_buy_button);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("product_name");
            String price = extras.getString("product_price");
            int imageResId = extras.getInt("product_image");
            String description = extras.getString("product_description");

            productName.setText(name);
            productPrice.setText(price);
            productImage.setImageResource(imageResId);
            productDescription.setText(description);

            buyButton.setOnClickListener(v -> {
                Toast.makeText(ProductDetailActivity.this, "Đã thêm " + name + " vào giỏ hàng từ chi tiết!", Toast.LENGTH_SHORT).show();
                // Logic để thêm sản phẩm vào giỏ hàng từ trang chi tiết
            });
        }
    }
}