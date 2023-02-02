package com.example.foodorder_app_sqlit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.foodorder_app_sqlit.Adapters.MainAdpater;
import com.example.foodorder_app_sqlit.Models.MainModel;
import com.example.foodorder_app_sqlit.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list=new ArrayList<>();
        list.add(new MainModel(R.drawable.burgers,"Burgers","7","Chicken Burger With Extra Cheese"));
        list.add(new MainModel(R.drawable.pizzas,"Pizza","9","Chicken Pizza With Extra Cheese"));
        list.add(new MainModel(R.drawable.pasti,"Pasta","15","Chicken pasta With Extra Cheese"));
        list.add(new MainModel(R.drawable.rasspic,"raspic","8","Chicken Burger With Extra Cheese"));
        list.add(new MainModel(R.drawable.rassmilai,"Rasmalai","9","Chicken Burger With Extra Cheese"));
        list.add(new MainModel(R.drawable.rice,"Rice","4","Chicken Burger With Extra Cheese"));
        list.add(new MainModel(R.drawable.cakes,"Cake","1","Chicken Burger With Extra Cheese"));
        list.add(new MainModel(R.drawable.icecream,"IceCream","22","Chicken Burger With Extra Cheese"));
        list.add(new MainModel(R.drawable.kabab,"Kabab","9","Chicken Burger With Extra Cheese"));
        list.add(new MainModel(R.drawable.seekh,"Seekh","17","Chicken Burger With Extra Cheese"));
        list.add(new MainModel(R.drawable.sheerqurma,"Sheerquorma","11","Chicken Burger With Extra Cheese"));
        list.add(new MainModel(R.drawable.shuwrams,"Shorma","10","Chicken Burger With Extra Cheese"));

        MainAdpater adapater=new MainAdpater(list,this);
        binding.recyclerview.setAdapter(adapater);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this,order_activity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}