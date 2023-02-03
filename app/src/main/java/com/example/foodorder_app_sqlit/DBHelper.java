package com.example.foodorder_app_sqlit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodorder_app_sqlit.Models.OrderModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME = "mydatabase.db";
    final static int DBVERSION = 1;


    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(
                "create table orders " +
                        "(id integer primary key autoincrement ," +
                        "name text," +
                        "phone text," +
                        "price int," +
                        "image int," +
                        "quantity int," +
                        "description text," +
                        "foodname text)"

        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP table if exists orders");
        onCreate(sqLiteDatabase);

    }
    public boolean insertOrder(String name,String phone,int price,int image,String desc,String foodname,int quantity){
        SQLiteDatabase database=getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("description",desc);
        values.put("foodname",foodname);
        values.put("quantity",quantity);
       long id= database.insert("orders",null,values);

       if (id<=0){
           return false;
       }
       else {
           return true;
       }


    }
    public ArrayList<OrderModel> getOrders() {
        ArrayList<OrderModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,foodname,image,price from  orders", null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {

                OrderModel model = new OrderModel();
                model.setOrderNumber(cursor.getInt(0) + "");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderimage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3) + "");
                orders.add(model);


            }

        }
        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderByid(int id){

        ArrayList<OrderModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders where id = " +id, null);
        if (cursor != null)
            cursor.moveToNext();


        return cursor;
    }


    public boolean updateOrder(String name, String phone, int price, int image, String desc, String foodname, int quantity, int id) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", desc);
        values.put("foodname", foodname);
        values.put("quantity", quantity);

        long row = database.update("orders",values, "id="+id,null);

        if (row <= 0) {
            return false;
        } else {
            return true;
        }

    }

    public int deleteOrder(String id){

        SQLiteDatabase database=this.getWritableDatabase();
        return database.delete("orders","id="+id,null);

    }


}
