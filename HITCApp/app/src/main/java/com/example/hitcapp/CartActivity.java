package com.example.hitcapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CartActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        // Sửa ID từ R.id.main thành R.id.cart_root_layout (hoặc ID gốc của layout activity_cart.xml của bạn)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cart_root_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // TODO: Thêm logic để hiển thị các sản phẩm trong giỏ hàng tại đây
        // Ví dụ: ánh xạ RecyclerView, tạo Adapter, và thiết lập dữ liệu
        // RecyclerView cartRecyclerView = findViewById(R.id.cart_recyclerview);
        // cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // CartAdapter adapter = new CartAdapter(getCartItems()); // getCartItems() là hàm lấy dữ liệu giỏ hàng
        // cartRecyclerView.setAdapter(adapter);

        // TODO: Ánh xạ nút checkout và thiết lập OnClickListener cho nó
        // Button checkoutButton = findViewById(R.id.checkout_button);
        // checkoutButton.setOnClickListener(v -> {
        //     Toast.makeText(this, "Tiến hành thanh toán...", Toast.LENGTH_SHORT).show();
        //     // Logic xử lý thanh toán
        // });

        // TODO: Logic ẩn/hiện empty_cart_message dựa trên dữ liệu giỏ hàng
    }
}