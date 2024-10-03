package com.example.carsaleapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OrderConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        TextView confirmationMessage = findViewById(R.id.confirmationMessage);
        Button backButton = findViewById(R.id.backButton);

        // แสดงข้อความสั่งจองสำเร็จ
        confirmationMessage.setText("Your order has been successfully placed!");

        // ปุ่มย้อนกลับไปหน้าหลัก
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(OrderConfirmationActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();  // ปิดหน้าปัจจุบันเมื่อย้อนกลับไป
        });
    }
}
