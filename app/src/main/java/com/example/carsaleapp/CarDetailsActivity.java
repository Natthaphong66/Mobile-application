package com.example.carsaleapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class CarDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        // ดึงข้อมูลจาก Intent
        Car car = (Car) getIntent().getSerializableExtra("car");

        // การค้นหาวิวใน layout
        ImageView carImage = findViewById(R.id.carDetailImage);
        TextView carName = findViewById(R.id.carDetailName);
        TextView carDescription = findViewById(R.id.carDetailDescription);
        TextView carPrice = findViewById(R.id.carDetailPrice);
        TextView carEngine = findViewById(R.id.carDetailEngine);
        TextView carFuelConsumption = findViewById(R.id.carDetailFuel);
        Button contactButton = findViewById(R.id.contactButton);
        Button backButton = findViewById(R.id.backButton); // ปุ่ม Back

        // ใช้ Glide สำหรับแสดงรูปภาพ
        Glide.with(this)
                .load(getResources().getIdentifier(car.getImageResource(), "drawable", getPackageName()))
                .into(carImage);

        // แสดงข้อมูลของรถยนต์
        carName.setText(car.getName());
        carDescription.setText(car.getDescription());
        carPrice.setText("Price: $" + car.getPrice());
        carEngine.setText("Engine: " + car.getEngine());  // เพิ่มเครื่องยนต์
        carFuelConsumption.setText("Fuel Consumption: " + car.getFuelConsumption()); // เพิ่มการบริโภคน้ำมัน

        // การทำงานของปุ่ม Contact
        contactButton.setOnClickListener(v -> {
            Intent intent = new Intent(CarDetailsActivity.this, OrderConfirmationActivity.class);
            startActivity(intent);
        });

        // การทำงานของปุ่ม Back
        backButton.setOnClickListener(v -> {
            finish(); // ปิด Activity ปัจจุบัน และกลับไปยัง Activity ก่อนหน้า
        });
    }
}
