package com.example.foodorder_app_sqlit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.foodorder_app_sqlit.Adapters.OrdersAdapter;
import com.example.foodorder_app_sqlit.Models.MainModel;
import com.example.foodorder_app_sqlit.Models.OrderModel;
import com.example.foodorder_app_sqlit.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class order_activity extends AppCompatActivity {

    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        DBHelper helper=new DBHelper(this);

        ArrayList<OrderModel> list=helper.getOrders();

        OrdersAdapter adapter=new OrdersAdapter(list,this);
        binding.OderRecyclerview.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.OderRecyclerview.setLayoutManager(layoutManager);
    }
}