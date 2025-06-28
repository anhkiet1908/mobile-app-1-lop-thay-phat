package com.example.hitcapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat; // Thêm import này để định dạng giá
import java.util.Locale; // Thêm import này để định dạng giá

public class ProductDetailActivity extends AppCompatActivity {

    // Khai báo biến để lưu trữ đối tượng Product hiện tại
    private Product currentProduct; // Đã đổi ProductItem thành Product

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);

        // Đảm bảo R.id.detail_root_layout hoặc ID gốc của layout tồn tại trong activity_product_detail.xml của bạn
        // Tôi khuyến nghị đặt một ID rõ ràng như detail_root_layout cho layout gốc.
        // Nếu layout gốc của bạn có id là @+id/main thì dùng R.id.main
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.product_detail_root_layout), (v, insets) -> { // Sử dụng ID rõ ràng hơn
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ các View
        ImageView productImage = findViewById(R.id.detail_product_image);
        TextView productName = findViewById(R.id.detail_product_name);
        TextView productPrice = findViewById(R.id.detail_product_price);
        TextView productDescription = findViewById(R.id.detail_product_description);
        Button buyButton = findViewById(R.id.detail_buy_button);

        // Lấy đối tượng Product từ Intent
        // Vì Product implements Serializable, bạn có thể truyền nó trực tiếp
        if (getIntent().hasExtra("product")) {
            currentProduct = (Product) getIntent().getSerializableExtra("product");

            if (currentProduct != null) {
                // Hiển thị thông tin sản phẩm lên giao diện
                productName.setText(currentProduct.getName());
                productPrice.setText(formatCurrency(currentProduct.getPrice())); // Định dạng giá
                productImage.setImageResource(currentProduct.getImageResId());
                productDescription.setText(currentProduct.getDescription());

                // Xử lý sự kiện click cho nút "Mua"
                buyButton.setOnClickListener(v -> {
                    // Gọi CartManager để thêm sản phẩm vào giỏ hàng với số lượng 1
                    CartManager.getInstance().addProduct(currentProduct, 1);
                    Toast.makeText(ProductDetailActivity.this, "Đã thêm " + currentProduct.getName() + " vào giỏ hàng!", Toast.LENGTH_SHORT).show();
                    // Có thể thêm logic để cập nhật số lượng trên icon giỏ hàng ở MainActivity nếu cần
                });
            } else {
                Toast.makeText(this, "Không thể tải thông tin sản phẩm.", Toast.LENGTH_SHORT).show();
                finish(); // Đóng Activity nếu đối tượng Product là null
            }
        } else {
            // Trường hợp không có dữ liệu sản phẩm được truyền
            Toast.makeText(this, "Không tìm thấy thông tin sản phẩm.", Toast.LENGTH_SHORT).show();
            finish(); // Đóng Activity nếu không có dữ liệu
        }
    }

    // Phương thức định dạng tiền tệ
    private String formatCurrency(double amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return format.format(amount).replace("₫", "đ");
    }
}