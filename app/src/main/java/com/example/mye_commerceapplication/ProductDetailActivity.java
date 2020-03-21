package com.example.mye_commerceapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.mye_commerceapplication.Model.LigneCommande;
import com.example.mye_commerceapplication.Model.Product;
import com.example.mye_commerceapplication.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ProductDetailActivity extends AppCompatActivity {

    private String  productId="";
    private Button addToCart;
    private  ImageView product_image;
    private ElegantNumberButton numberButton;
    private TextView description_product,name_product,price_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        productId=getIntent().getStringExtra("pid");

        addToCart=this.findViewById(R.id.add_product_cart);
        product_image=this.findViewById(R.id.product_image_detail);
        numberButton=this.findViewById(R.id.number_btn);
        description_product=this.findViewById(R.id.product_description_details);

        name_product=this.findViewById(R.id.product_name_details);
        price_product=this.findViewById(R.id.product_price_details);


        getProductId(productId);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductToCart(productId);
            }


        });
    }

    public void addProductToCart(String productId) {

        DatabaseReference mRefLigneCommande;
        mRefLigneCommande=FirebaseDatabase.getInstance().getReference().child("ligneCommande");

        final HashMap<String,String> hashMap=new HashMap<>();

        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        String idLigneCommande;
        idLigneCommande=mRefLigneCommande.push().getKey();
        LigneCommande ligneCommande=new LigneCommande(idLigneCommande,df.format(dateobj),productId,numberButton.getNumber(),Prevalent.currentOnlineUser.getPhone());

//        hashMap.put("idLignCommande",idLigneCommande);
//        hashMap.put("date",df.format(dateobj));
//        hashMap.put("productId",productId);
//        hashMap.put("quantity",numberButton.getNumber());
//        hashMap.put("telNumber", Prevalent.currentOnlineUser.getPhone());

        mRefLigneCommande.child(idLigneCommande).setValue(ligneCommande).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ProductDetailActivity.this, "added to cart list successfully ...", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ProductDetailActivity.this,HomeActivity.class);
                startActivity(intent);
                }
            }
        });

        //hashMap.put("",);
        //hashMap.put("",);





        //System.out.println(df.format(dateobj));

    }

    private void getProductId(final String productId) {

        DatabaseReference mRef;
        mRef= FirebaseDatabase.getInstance().getReference().child("Products");

        mRef.child(productId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Product product=dataSnapshot.getValue(Product.class);
                    System.out.println("product detail: "+product.getPid()+" description is : "+product.getDescription());
                    description_product.setText(product.getDescription());
                    price_product.setText(product.getPrice());
                    name_product.setText(product.getPname());
                    product_image.setImageResource(R.drawable.tshirtt);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
