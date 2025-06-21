package com.example.hitcapp;

// Product.java
public class Product {
    private String name;
    private String price;
    private int imageResId; // ID tài nguyên cho hình ảnh sản phẩm
    private String description; // Để hiển thị ở trang chi tiết

    public Product(String name, String price, int imageResId, String description) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.description = description;
    }

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
}
