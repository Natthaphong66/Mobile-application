package com.example.carsaleapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView; // เพิ่มการนำเข้า SearchView
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CarAdapter.OnCarClickListener {
    private RecyclerView recyclerView;
    private CarAdapter carAdapter;
    private List<Car> allCars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // เชื่อมโยงกับ layout ของ activity_main

        // เพิ่ม TextView แสดงชื่อร้านขายรถ
        setTitle("Car Sale Shop");

        // เชื่อมต่อ SearchView
        SearchView searchView = findViewById(R.id.searchView); // ตรวจสอบให้แน่ใจว่ามีการกำหนด ID นี้ใน layout
        recyclerView = findViewById(R.id.recyclerView);

        // ตั้งค่า GridLayoutManager ที่มี 2 คอลัมน์เพื่อให้ไอเท็มขนาดเล็กลง
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        allCars = new ArrayList<>();
        allCars.add(new Car("Audi R8", "audi_r8", "High-performance sports car", 150000, "Audi", "V10 5.2L", "13 MPG", 2020));
        allCars.add(new Car("Bentley Continental", "bentley", "Luxury grand tourer", 200000, "Bentley", "W12 6.0L", "12 MPG", 2021));
        allCars.add(new Car("BMW Z4", "bmw_z4", "Roadster sports car", 50000, "BMW", "I4 2.0L", "27 MPG", 2019));
        allCars.add(new Car("Honda Civic FE", "civic_fe", "Compact car", 25000, "Honda", "I4 1.5L", "32 MPG", 2022));
        allCars.add(new Car("Mercedes E-Class", "eclass", "Executive car", 55000, "Mercedes", "I6 3.0L", "23 MPG", 2021));
        allCars.add(new Car("Ferrari 458", "fer458", "Sports car", 250000, "Ferrari", "V8 4.5L", "15 MPG", 2015));
        allCars.add(new Car("Lamborghini Aventador", "lamborghini_aventador", "Supercar", 400000, "Lamborghini", "V12 6.5L", "10 MPG", 2021));
        allCars.add(new Car("Nissan GT-R", "r35", "High-performance sports car", 110000, "Nissan", "V6 3.8L", "16 MPG", 2021));
        allCars.add(new Car("Lexus RX", "rx7", "Luxury crossover SUV", 45000, "Lexus", "V6 3.5L", "20 MPG", 2020));
        allCars.add(new Car("Toyota Supra", "supra", "Sports car", 50000, "Toyota", "I6 3.0L", "24 MPG", 2021));

        carAdapter = new CarAdapter(this, allCars, this);
        recyclerView.setAdapter(carAdapter);

        // ตั้งค่าการค้นหา
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // เมื่อผู้ใช้กดปุ่มค้นหา
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // กรองรายการเมื่อมีการเปลี่ยนแปลงข้อความ
                carAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    @Override
    public void onCarClick(Car car) {
        // สร้าง Intent เพื่อเปิด CarDetailsActivity เมื่อผู้ใช้คลิกเลือกรถ
        Intent intent = new Intent(MainActivity.this, CarDetailsActivity.class);
        intent.putExtra("car", car); // ส่งข้อมูล Car object
        startActivity(intent); // เปิด CarDetailsActivity
    }
}
