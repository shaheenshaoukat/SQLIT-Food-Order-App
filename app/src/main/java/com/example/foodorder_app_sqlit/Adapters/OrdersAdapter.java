package com.example.foodorder_app_sqlit.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder_app_sqlit.DBHelper;
import com.example.foodorder_app_sqlit.Details_Activity;
import com.example.foodorder_app_sqlit.Models.OrderModel;
import com.example.foodorder_app_sqlit.R;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.viewholder>{

    ArrayList<OrderModel> list;
    Context context;

    public OrdersAdapter(ArrayList<OrderModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.order_sample,parent,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        final OrderModel model=list.get(position);
        holder.orderimage.setImageResource(model.getOrderimage());
        holder.soldItem.setText(model.getSoldItemName());
        holder.ordernumber.setText(model.getOrderNumber());
        holder.price.setText(model.getPrice());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Details_Activity.class);
                intent.putExtra("id",Integer.parseInt(model.getOrderNumber()));
                intent.putExtra("type",2);

                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                new AlertDialog.Builder(context)
                        .setTitle("Deleted Item")
                        .setMessage("Are You sure to deleted this item=?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                DBHelper helper=new DBHelper(context);
                                if (helper.deleteOrder(model.getOrderNumber())>0){
                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();






                return false;
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        ImageView orderimage;
        TextView soldItem,ordernumber,price;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            orderimage=itemView.findViewById(R.id.orderimage);
            soldItem=itemView.findViewById(R.id.orderItemName);
            ordernumber=itemView.findViewById(R.id.orderNo);
            price=itemView.findViewById(R.id.orderprice);
        }
    }
}
