package com.example.hitcapp;

import android.content.Intent; // Import để dùng Intent
import android.os.Bundle;
import android.view.View; // Import để dùng View.OnClickListener
import android.widget.Button; // Import Button
import android.widget.TextView; // Import TextView
import android.widget.Toast; // Import Toast

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AccountActivity extends AppCompatActivity {

    private TextView tvUserEmail;
    private TextView tvUserName;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ các View từ layout
        tvUserEmail = findViewById(R.id.tvUserEmail);
        tvUserName = findViewById(R.id.tvUserName);
        btnLogout = findViewById(R.id.btnLogout);

        // --- Hiển thị thông tin người dùng (dữ liệu giả định) ---
        // Trong ứng dụng thực tế, bạn sẽ lấy dữ liệu này từ SharedPreferences, cơ sở dữ liệu cục bộ,
        // hoặc từ một đối tượng người dùng đang đăng nhập (ví dụ: từ Firebase, API của bạn).
        displayUserInfo("user@example.com", "Nguyễn Văn A");

        // Thiết lập OnClickListener cho nút Đăng xuất
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện hành động đăng xuất
                // Ví dụ: Xóa token đăng nhập, xóa thông tin người dùng khỏi SharedPreferences
                // FirebaseAuth.getInstance().signOut(); // Nếu dùng Firebase

                Toast.makeText(AccountActivity.this, "Đã đăng xuất!", Toast.LENGTH_SHORT).show();

                // Chuyển về màn hình đăng nhập (LoginActivity)
                // FLAG_ACTIVITY_CLEAR_TASK | FLAG_ACTIVITY_NEW_TASK sẽ xóa tất cả các Activity
                // trên stack và tạo một task mới cho LoginActivity, ngăn người dùng quay lại
                // các màn hình trước đó bằng nút Back.
                Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish(); // Kết thúc AccountActivity
            }
        });
    }

    // Phương thức để hiển thị thông tin người dùng
    private void displayUserInfo(String email, String name) {
        tvUserEmail.setText(email);
        tvUserName.setText(name);
    }
}