package com.example.carsaleapp;

import java.io.Serializable;

public class Car implements Serializable {
    private String name;
    private String imageResource;
    private String description;
    private double price;
    private String brand;
    private String engine; // ข้อมูลเครื่องยนต์
    private String fuelConsumption; // ข้อมูลการบริโภคน้ำมัน
    private int year; // ปีของรถยนต์

    public Car(String name, String imageResource, String description, double price, String brand, String engine, String fuelConsumption, int year) {
        this.name = name;
        this.imageResource = imageResource;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.engine = engine;
        this.fuelConsumption = fuelConsumption;
        this.year = year;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getImageResource() {
        return imageResource;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public String getEngine() {
        return engine;
    }

    public String getFuelConsumption() {
        return fuelConsumption;
    }

    public int getYear() {
        return year;
    }
}
