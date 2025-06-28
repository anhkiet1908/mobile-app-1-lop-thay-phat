package com.example.hitcapp; // Đảm bảo đúng package của bạn

import androidx.annotation.NonNull;

public class ProductItem { // Đổi tên từ ProductItem sang Product nếu muốn thống nhất hơn với tên gợi ý ban đầu
    private String name;
    private String price; // Thêm thuộc tính giá
    private int imageResId;
    private String description; // Thêm thuộc tính mô tả chi tiết

    // Constructor cũ của bạn
    public ProductItem(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
        this.price = ""; // Gán mặc định hoặc bỏ nếu không dùng
        this.description = ""; // Gán mặc định hoặc bỏ nếu không dùng
    }

    // Constructor mới với giá và mô tả
    public ProductItem(String name, String price, int imageResId, String description) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.description = description;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }

    // Nếu cần setters thì thêm vào
}
