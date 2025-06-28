package com.example.hitcapp;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager; // Import LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView; // Import RecyclerView

import java.util.ArrayList;
import java.util.List;

public class PromotionActivity extends AppCompatActivity {

    private RecyclerView promotionRecyclerView;
    private PromotionAdapter promotionAdapter;
    private List<PromotionItem> promotionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_promotion);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ RecyclerView
        promotionRecyclerView = findViewById(R.id.promotionRecyclerView);

        // Thiết lập LayoutManager cho RecyclerView
        promotionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Chuẩn bị dữ liệu giả cho khuyến mãi
        promotionList = getDummyPromotions();

        // Khởi tạo và thiết lập Adapter
        promotionAdapter = new PromotionAdapter(promotionList);
        promotionRecyclerView.setAdapter(promotionAdapter);
    }

    // Hàm tạo dữ liệu giả cho các chương trình khuyến mãi
    private List<PromotionItem> getDummyPromotions() {
        List<PromotionItem> promotions = new ArrayList<>();
        promotions.add(new PromotionItem(
                "FLASH SALE ĐỒNG GIÁ 99K",
                "Giảm giá cực sốc nhiều sản phẩm chỉ còn 99.000 VNĐ. Duy nhất trong 24 giờ!",
                R.drawable.sau_rieng_1)); // Thay bằng ảnh bạn có, ví dụ: promotion_banner_1

        promotions.add(new PromotionItem(
                "GIẢM 50% CHO ĐƠN HÀNG ĐẦU TIÊN",
                "Ưu đãi đặc biệt dành cho khách hàng mới! Giảm ngay 50% khi đăng ký và mua hàng lần đầu.",
                R.drawable.sau_rieng_thai)); // Thay bằng ảnh bạn có, ví dụ: promotion_banner_2

        promotions.add(new PromotionItem(
                "MUA 1 TẶNG 1 SẦU RIÊNG RI6",
                "Mua một trái sầu riêng Ri6, tặng ngay một trái cùng loại. Số lượng có hạn!",
                R.drawable.sau_rieng_chin)); // Thay bằng ảnh bạn có, ví dụ: promotion_banner_3

        promotions.add(new PromotionItem(
                "MIỄN PHÍ VẬN CHUYỂN TOÀN QUỐC",
                "Áp dụng cho mọi đơn hàng từ 500.000 VNĐ. Nhanh tay đặt hàng ngay hôm nay!",
                R.drawable.sau_rieng_hat_nho)); // Thay bằng ảnh bạn có, ví dụ: promotion_banner_4

        return promotions;
    }
}