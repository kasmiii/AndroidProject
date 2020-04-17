package com.example.mye_commerceapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mye_commerceapplication.Model.Product;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SalesActivity extends AppCompatActivity {

    private DatabaseReference mProductsRef,mSalesRef;
    private RecyclerView listViewShopping;
    private ArrayList<Product> list_shopping;
    private ArrayList<Product> list_products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        mProductsRef= FirebaseDatabase.getInstance().getReference("Products");
        mSalesRef=FirebaseDatabase.getInstance().getReference("Ventes");


    }

    @Override
    protected void onStart() {
        super.onStart();


    }

}
