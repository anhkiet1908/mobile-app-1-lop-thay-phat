package com.example.hitcapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;
import com.example.hitcapp.Product;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem; // Thêm import này nếu chưa có

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        CategoryAdapter.OnCategoryClickListener,
        ProductAdapter.OnProductActionListener {

    private RecyclerView productRecyclerView;
    private RecyclerView categoryRecyclerView;
    private SearchView searchView;
    private ProductAdapter productAdapter;
    private CategoryAdapter categoryAdapter;
    private List<Product> originalProductList;
    private ImageView bannerImageView;
    private BottomNavigationView bottomNavigationView;
    private ImageButton cartImageButton;
    private ImageButton refreshButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> { // SỬA ID TẠI ĐÂY
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
        cartImageButton = findViewById(R.id.cartImageButton); // ĐÃ SỬA ID ĐỂ KHỚP VỚI activity_main.xml
        refreshButton = findViewById(R.id.refresh_button);

        // --- Thiết lập ảnh banner ---
        bannerImageView.setImageResource(R.drawable.sau_rieng_1); // Đảm bảo bạn có tài nguyên này

        // --- Thiết lập RecyclerView Danh mục (Horizontal) ---
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        List<Category> categories = getDummyCategories();
        categoryAdapter = new CategoryAdapter(categories, this);
        categoryRecyclerView.setAdapter(categoryAdapter);

        // --- Thiết lập RecyclerView Sản phẩm (Vertical) ---
        originalProductList = getDummyProducts();
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productAdapter = new ProductAdapter(this, originalProductList, this);
        productRecyclerView.setAdapter(productAdapter);

        // --- Thiết lập Search View Listener ---
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // ĐÃ XÓA PHƯƠNG THỨC public boolean onOnQueryTextSubmit(String query) {...} TRÙNG LẶP
            @Override
            public boolean onQueryTextSubmit(String query) { // Giữ lại phương thức override đúng tên
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                productAdapter.getFilter().filter(newText);
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
                startActivity(new Intent(MainActivity.this, PromotionActivity.class)); // Đảm bảo PromotionActivity tồn tại
                return true;
            } else if (id == R.id.nav_account) {
                Toast.makeText(MainActivity.this, "Chuyển đến Tài khoản", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, AccountActivity.class)); // Đảm bảo AccountActivity tồn tại
                return true;
            }
            return false;
        });

        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        // --- Thiết lập OnClickListener cho ImageButton giỏ hàng ---
        cartImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Chuyển đến Giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        });

        // --- THIẾT LẬP ONCLICKLISTENER CHO NÚT LÀM MỚI ---
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Đang làm mới dữ liệu...", Toast.LENGTH_SHORT).show();
                originalProductList = getDummyProducts();
                productAdapter.setProducts(originalProductList);
            }
        });
    }

    // --- Các phương thức tạo dữ liệu giả ---
    private List<Category> getDummyCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Sầu riêng thái", R.drawable.ic_category_fruit));
        categories.add(new Category("Sầu riêng việt", R.drawable.ic_category_vegetable));
        categories.add(new Category("Sầu riêng hạt nhỏ", R.drawable.ic_category_meat));
        categories.add(new Category("Sầu riêng Đặc biệt", R.drawable.ic_category_seafood));
        return categories;
    }

    private List<Product> getDummyProducts() { // Dùng Product
        List<Product> products = new ArrayList<>();
        products.add(new Product("SRT001", "Sầu Riêng Thái", 350000.0, R.drawable.sau_rieng_thai, "Sầu riêng Thái Lan, múi to, cơm vàng, hạt lép, vị ngọt đậm."));
        products.add(new Product("SRHN002", "Sầu Riêng Hạt Nhỏ", 120000.0, R.drawable.sau_rieng_hat_nho, "Sầu riêng hạt nhỏ, cơm vàng dẻo, ngọt thanh."));
        products.add(new Product("SRKQ003", "Sầu Riêng Khổ Qua", 180000.0, R.drawable.sau_rieng_1, "Sầu riêng Khổ Qua, hương vị độc đáo, cơm dày, béo."));
        products.add(new Product("SRMK004", "Sầu Riêng Musang King", 700000.0, R.drawable.sau_rieng_chin, "Sầu riêng Musang King nhập khẩu, vị đắng nhẹ, béo ngậy, hương thơm đặc trưng."));
        products.add(new Product("SRR6005", "Sầu Riêng Ri6", 250000.0, R.drawable.sau_rieng_thai, "Sầu riêng Ri6, múi to, vàng óng, thơm lừng, vị ngọt sắc."));
        products.add(new Product("DH006", "Dưa Hấu Sọc", 30000.0, R.drawable.sau_rieng_1, "Dưa hấu sọc, ruột đỏ, nhiều nước, ngọt mát."));
        return products;
    }

    // --- Triển khai các phương thức từ OnCategoryClickListener và OnProductActionListener ---
    @Override
    public void onCategoryClick(Category category) {
        Toast.makeText(this, "Bạn đã chọn danh mục: " + category.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBuyClick(Product product) { // Dùng Product
        CartManager.getInstance().addProduct(product, 1);
        Toast.makeText(this, "Đã thêm " + product.getName() + " vào giỏ hàng!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetailsClick(Product product) { // Dùng Product
        Toast.makeText(this, "Đang xem chi tiết: " + product.getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
        intent.putExtra("product", product); // Truyền đối tượng Product
        startActivity(intent);
    }
}