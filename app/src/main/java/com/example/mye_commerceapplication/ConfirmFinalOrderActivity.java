package com.example.mye_commerceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mye_commerceapplication.Model.Order;
import com.example.mye_commerceapplication.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfirmFinalOrderActivity extends AppCompatActivity {

    private EditText cityEdtText ,nameEditText,phoneEditText,addressEditText;
    private Button confirmOrderBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);

        nameEditText=this.findViewById(R.id.shippement_name);
        cityEdtText=this.findViewById(R.id.shippement_city_name);
        phoneEditText=this.findViewById(R.id.shippement_phone_number);
        addressEditText=this.findViewById(R.id.shippement_home_address);
        confirmOrderBtn=this.findViewById(R.id.confirm_order_btn);

        confirmOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFields();
            }
        });


    }

    private void checkFields(){

        if(TextUtils.isEmpty(nameEditText.getText().toString())){
            Toast.makeText(this, "name is missing ...", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(cityEdtText.getText().toString())){
            Toast.makeText(this, "city is missing ...", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(addressEditText.getText().toString())){
            Toast.makeText(this, "adress is missing ...", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(phoneEditText.getText().toString())){
            Toast.makeText(this, "phone Number is missing ...", Toast.LENGTH_SHORT).show();
        }

        else {
            //Toast.makeText(this, "name is missing ...", Toast.LENGTH_SHORT).show();
            confirmOrder();
        }

    }

    private void confirmOrder() {
        final DatabaseReference mRef;
        mRef=FirebaseDatabase.getInstance().getInstance().getReference("Orders").child(Prevalent.currentOnlineUser.getPhone());

        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        String dateOrder=df.format(dateobj);

        final Order order=new Order(nameEditText.getText().toString(),phoneEditText.getText().toString(),addressEditText.getText().toString(),dateOrder,"not shipped",getIntent().getStringExtra("totalAmount"));
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mRef.setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ConfirmFinalOrderActivity.this, "your order has been registered succesfully...", Toast.LENGTH_SHORT).show();

                            FirebaseDatabase.getInstance().getReference("ligneCommande")
                                    .child(Prevalent.currentOnlineUser.getPhone())
                                    .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(ConfirmFinalOrderActivity.this, "your orders have been registered...", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(ConfirmFinalOrderActivity.this,CartActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ConfirmFinalOrderActivity.this, "Error Data ...", Toast.LENGTH_SHORT).show();

            }
        });


    }

}
