package com.example.carsaleapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import android.widget.Filter;
import android.widget.Filterable; // เพิ่มการนำเข้า Filterable

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> implements Filterable {

    private Context context;
    private List<Car> cars;
    private List<Car> carListFull; // เพิ่มการประกาศตัวแปรนี้
    private OnCarClickListener listener;

    public interface OnCarClickListener {
        void onCarClick(Car car);
    }

    public CarAdapter(Context context, List<Car> cars, OnCarClickListener listener) {
        this.context = context;
        this.cars = cars;
        this.listener = listener;
        this.carListFull = new ArrayList<>(cars); // สร้างสำเนาของรายการทั้งหมด
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_item, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = cars.get(position);
        holder.carName.setText(car.getName());
        holder.carPrice.setText("$" + car.getPrice());
        Glide.with(context).load(context.getResources().getIdentifier(car.getImageResource(), "drawable", context.getPackageName())).into(holder.carImage);

        holder.itemView.setOnClickListener(v -> listener.onCarClick(car));  // เมื่อกดเลือกรถ
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {
        TextView carName, carPrice;
        ImageView carImage;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            carName = itemView.findViewById(R.id.carName);
            carPrice = itemView.findViewById(R.id.carPrice);
            carImage = itemView.findViewById(R.id.carImage);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Car> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(carListFull); // หากไม่มีการค้นหา ให้แสดงรายการทั้งหมด
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim(); // แปลงข้อความให้เป็นตัวพิมพ์เล็ก

                    for (Car car : carListFull) {
                        // กรองตามชื่อหรือแบรนด์
                        if (car.getName().toLowerCase().contains(filterPattern) ||
                                car.getBrand().toLowerCase().contains(filterPattern)) {
                            filteredList.add(car);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList; // ผลลัพธ์ที่กรองแล้ว
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                cars.clear();
                cars.addAll((List) results.values); // อัปเดตรายการใน Adapter
                notifyDataSetChanged(); // แจ้งให้ RecyclerView ทราบว่าข้อมูลเปลี่ยนแปลง
            }
        };
    }
}
