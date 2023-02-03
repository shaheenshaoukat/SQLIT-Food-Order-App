package com.example.foodorder_app_sqlit;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.foodorder_app_sqlit.databinding.ActivityDetailsBinding;

public class Details_Activity extends AppCompatActivity {

    ActivityDetailsBinding binding;
    int count=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final DBHelper helper = new DBHelper(this);





        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              count++;
                binding.quntity.setText(String.valueOf(count));




            }
        });
        binding.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (count > 1) {
                    count--;
                    binding.quntity.setText(String.valueOf(count));

                } else{
                    Toast.makeText(Details_Activity.this, "value less", Toast.LENGTH_SHORT).show();

            }
            }
        });



        if (getIntent().getIntExtra("type", 0) == 1) {

            int image = getIntent().getIntExtra("image", 0);
            int price = Integer.parseInt(getIntent().getStringExtra("price"));
            String name = getIntent().getStringExtra("name");
            String description = getIntent().getStringExtra("desc");
            binding.detailImage.setImageResource(image);
            binding.tvorderPrices.setText(String.format("%d", price));
            binding.namebox.setText(name);
            binding.DetailDescription.setText(description);


            binding.btnOrderNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    boolean isInserted = helper.insertOrder(
                            binding.etName.getText().toString(),
                            binding.etPhone.getText().toString(),
                            price,
                            image,
                            name,
                            description,
                            Integer.parseInt(binding.quntity.getText().toString())

                    );

                    if (isInserted) {
                        Toast.makeText(Details_Activity.this, "succesfuly", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Details_Activity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {

            int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = helper.getOrderByid(id);

            int image = cursor.getInt(4);
            binding.detailImage.setImageResource(image);
            binding.tvorderPrices.setText(String.format("%d", cursor.getInt(3)));
            binding.namebox.setText(cursor.getString(6));
            binding.DetailDescription.setText(cursor.getString(7));

            binding.etName.setText(cursor.getString(1));
            binding.etPhone.setText(cursor.getString(2));

            binding.btnOrderNow.setText("Upadte Now");







            binding.btnOrderNow.setOnClickListener(v -> {
                boolean isUpdated= helper.updateOrder
                        (binding.etName.getText().toString()
                                ,binding.etPhone.getText().toString(),
                                Integer.parseInt(binding.tvorderPrices.getText().toString()),
                                image,
                                binding.namebox.getText().toString(),

                                binding.DetailDescription.getText().toString(),
                                Integer.parseInt(binding.quntity.getText().toString()),
                                id) ;


                if (isUpdated){
                    Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();

                }







            });

        }


    }
}