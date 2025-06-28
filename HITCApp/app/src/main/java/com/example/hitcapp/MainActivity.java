package com.example.hitcapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View; // Cần import View
import android.widget.ImageButton; // Cần import ImageButton
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager; // Nếu bạn có dùng GridLayoutManager

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        CategoryAdapter.OnCategoryClickListener,
        ProductItemAdapter.OnProductActionListener {

    private RecyclerView productRecyclerView;
    private RecyclerView categoryRecyclerView;
    private SearchView searchView;
    private ProductItemAdapter productItemAdapter;
    private CategoryAdapter categoryAdapter;
    private List<ProductItem> originalProductList;
    private ImageView bannerImageView;
    private BottomNavigationView bottomNavigationView;
    private ImageButton cartImageButton; // Khai báo ImageButton cho giỏ hàng

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_root_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // --- Ánh xạ và thiết lập các View ---
        searchView = findViewById(R.id.search_bar);
        bannerImageView = findViewById(R.id.banner_image);
        categoryRecyclerView = findViewById(R.id.category_recyclerview);
        productRecyclerView = findViewById(R.id.product_recyclerview);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        cartImageButton = findViewById(R.id.imageButton); // Ánh xạ ImageButton của giỏ hàng

        // --- Thiết lập ảnh banner ---
        bannerImageView.setImageResource(R.drawable.sau_rieng_1);

        // --- Thiết lập RecyclerView Danh mục (Horizontal) ---
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        List<Category> categories = getDummyCategories();
        categoryAdapter = new CategoryAdapter(categories, this);
        categoryRecyclerView.setAdapter(categoryAdapter);

        // --- Thiết lập RecyclerView Sản phẩm (Vertical) ---
        originalProductList = getDummyProducts();
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // productRecyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // Nếu muốn dạng lưới

        productItemAdapter = new ProductItemAdapter(this, originalProductList, this);
        productRecyclerView.setAdapter(productItemAdapter);

        // --- Thiết lập Search View Listener ---
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                productItemAdapter.getFilter().filter(newText);
                return true;
            }
        });

        // --- Thiết lập listener cho BottomNavigationView ---
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                Toast.makeText(MainActivity.this, "Bạn đang ở Trang chủ", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_promo) {
                Toast.makeText(MainActivity.this, "Chuyển đến Khuyến mãi", Toast.LENGTH_SHORT).show();
                // TODO: Chuyển đến Activity Khuyến mãi (ví dụ: PromoActivity)
                // startActivity(new Intent(MainActivity.this, PromoActivity.class));
                return true;
            } else if (id == R.id.nav_account) {
                Toast.makeText(MainActivity.this, "Chuyển đến Tài khoản", Toast.LENGTH_SHORT).show();
                // TODO: Chuyển đến Activity Tài khoản (ví dụ: AccountActivity)
                // startActivity(new Intent(MainActivity.this, AccountActivity.class));
                return true;
            }
            return false;
        });

        // Đặt mục được chọn mặc định khi khởi tạo Activity (ví dụ: Trang chủ)
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        // --- Thiết lập OnClickListener cho ImageButton giỏ hàng ---
        cartImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để chuyển từ MainActivity sang CartActivity
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent); // Bắt đầu CartActivity
                Toast.makeText(MainActivity.this, "Chuyển đến Giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // --- Phương thức để tạo Menu trên ActionBar/Toolbar (Nếu bạn không dùng MenuItem cho giỏ hàng, có thể bỏ qua phần này) ---
    // @Override
    // public boolean onCreateOptionsMenu(Menu menu) {
    //     getMenuInflater().inflate(R.menu.main_menu, menu);
    //     return true;
    // }

    // --- Phương thức để xử lý các sự kiện click vào các item trên Menu (Nếu bạn không dùng MenuItem cho giỏ hàng, có thể bỏ qua phần này) ---
    // @Override
    // public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    //     int id = item.getItemId();
    //
    //     if (id == R.id.action_cart) { // Nếu bạn muốn dùng MenuItem cho giỏ hàng thay vì ImageButton
    //         Toast.makeText(this, "Bạn đã click vào Giỏ hàng từ Menu!", Toast.LENGTH_SHORT).show();
    //         Intent intent = new Intent(MainActivity.this, CartActivity.class);
    //         startActivity(intent);
    //         return true;
    //     }
    //
    //     return super.onOptionsItemSelected(item);
    // }

    // --- Hàm tạo dữ liệu giả cho danh mục ---
    private List<Category> getDummyCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Sầu riêng thái", R.drawable.ic_category_fruit));
        categories.add(new Category("Sầu riêng việt", R.drawable.ic_category_vegetable));
        categories.add(new Category("Sầu riêng hạt nhỏ", R.drawable.ic_category_meat));
        categories.add(new Category("Sầu riêng Đặc biệt", R.drawable.ic_category_seafood));
        return categories;
    }

    // --- Hàm tạo dữ liệu giả cho sản phẩm ---
    private List<ProductItem> getDummyProducts() {
        List<ProductItem> products = new ArrayList<>();
        products.add(new ProductItem("Sầu Riêng Thái", "350.000 VNĐ/kg", R.drawable.sau_rieng_thai, "Sầu riêng Thái Lan, múi to, cơm vàng, hạt lép, vị ngọt đậm."));
        products.add(new ProductItem("Sầu Riêng Hạt Nhỏ", "120.000 VNĐ/kg", R.drawable.sau_rieng_hat_nho, "Sầu riêng hạt nhỏ, cơm vàng dẻo, ngọt thanh."));
        products.add(new ProductItem("Sầu Riêng Khổ Qua", "180.000 VNĐ/kg", R.drawable.sau_rieng_1, "Sầu riêng Khổ Qua, hương vị độc đáo, cơm dày, béo."));
        products.add(new ProductItem("Sầu Riêng Musang King", "700.000 VNĐ/kg", R.drawable.sau_rieng_chin, "Sầu riêng Musang King nhập khẩu, vị đắng nhẹ, béo ngậy, hương thơm đặc trưng."));
        products.add(new ProductItem("Sầu Riêng Ri6", "250.000 VNĐ/kg", R.drawable.sau_rieng_thai, "Sầu riêng Ri6, múi to, vàng óng, thơm lừng, vị ngọt sắc."));
        products.add(new ProductItem("Dưa Hấu Sọc", "30.000 VNĐ/kg", R.drawable.sau_rieng_1, "Dưa hấu sọc, ruột đỏ, nhiều nước, ngọt mát."));
        return products;
    }

    // --- Triển khai các phương thức từ OnCategoryClickListener ---
    @Override
    public void onCategoryClick(Category category) {
        Toast.makeText(this, "Bạn đã chọn danh mục: " + category.getName(), Toast.LENGTH_SHORT).show();
    }

    // --- Triển khai các phương thức từ OnProductActionListener ---
    @Override
    public void onBuyClick(ProductItem product) {
        Toast.makeText(this, "Đã thêm " + product.getName() + " vào giỏ hàng!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetailsClick(ProductItem product) {
        Toast.makeText(this, "Đang xem chi tiết: " + product.getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
        intent.putExtra("product_name", product.getName());
        intent.putExtra("product_price", product.getPrice());
        intent.putExtra("product_image", product.getImageResId());
        intent.putExtra("product_description", product.getDescription());
        startActivity(intent);
    }
}