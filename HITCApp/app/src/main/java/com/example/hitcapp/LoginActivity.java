package com.example.hitcapp;

import android.content.Intent; // Import này cần thiết để chuyển Activity
import android.os.Bundle;
import android.view.View; // Import này nếu bạn dùng View.OnClickListener
import android.widget.Button; // Import Button
import android.widget.EditText; // Import EditText
import android.widget.TextView;
import android.widget.Toast; // Import Toast để hiển thị thông báo

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText; // Khai báo biến cho EditText tài khoản
    private EditText passwordEditText; // Khai báo biến cho EditText mật khẩu
    private Button loginButton;      // Khai báo biến cho Button đăng nhập

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login); // Đảm bảo bạn đang sử dụng activity_login.xml

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ các View từ layout
        usernameEditText = findViewById(R.id.editTextText); // ID của EditText tài khoản
        passwordEditText = findViewById(R.id.editTextText2); // ID của EditText mật khẩu
        loginButton = findViewById(R.id.button);            // ID của Button đăng nhập

        // Thiết lập sự kiện click cho nút Đăng nhập
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ EditTexts
                String username = usernameEditText.getText().toString().trim(); // .trim() để loại bỏ khoảng trắng thừa
                String password = passwordEditText.getText().toString().trim();

                // Kiểm tra đăng nhập
                // Đây là logic kiểm tra đơn giản: tài khoản "admin", mật khẩu "123456"
                if (username.equals("admin") && password.equals("123456")) {
                    // Đăng nhập thành công
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                    // Chuyển sang MainActivity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Kết thúc LoginActivity để người dùng không thể quay lại trang đăng nhập bằng nút Back
                } else {
                    // Đăng nhập thất bại
                    Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // TODO: Xử lý sự kiện cho "Quên mật khẩu?" và "Đăng ký ở đây!"
        TextView forgotPasswordTextView = findViewById(R.id.textView4);
        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Chức năng Quên mật khẩu đang được phát triển.", Toast.LENGTH_SHORT).show();
                // TODO: Chuyển đến Activity Quên mật khẩu
            }
        });

        TextView registerTextView = findViewById(R.id.textView5);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Chức năng Đăng ký đang được phát triển.", Toast.LENGTH_SHORT).show();
                // TODO: Chuyển đến RegisterActivity (nếu bạn có)
                // Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                // startActivity(intent);
            }
        });
    }
}