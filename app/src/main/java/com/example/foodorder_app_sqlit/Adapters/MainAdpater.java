package com.example.foodorder_app_sqlit.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder_app_sqlit.Details_Activity;
import com.example.foodorder_app_sqlit.Models.MainModel;
import com.example.foodorder_app_sqlit.R;

import java.util.ArrayList;

public class MainAdpater  extends RecyclerView.Adapter<MainAdpater.viewholder>{

    ArrayList<MainModel> list;
    Context context;

    public MainAdpater(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_main_food,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        final MainModel model=list.get(position);
        holder.foodimage.setImageResource(model.getImage());

        holder.mainname.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.description.setText(model.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Details_Activity.class);
                intent.putExtra("image",model.getImage());
                intent.putExtra("price",model.getPrice());
                intent.putExtra("desc",model.getDescription());
                intent.putExtra("name",model.getName());
                intent.putExtra("type",1);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        ImageView foodimage;
        TextView mainname,price,description;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            foodimage=itemView.findViewById(R.id.imageview);
            mainname=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.orderPrices);
            description=itemView.findViewById(R.id.description);

        }
    }
}
