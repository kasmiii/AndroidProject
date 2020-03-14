package com.example.mye_commerceapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mye_commerceapplication.Model.Product;
import java.util.ArrayList;

 class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

     public Context context;
     public ArrayList<Product> list;
     public OnProductListener mOnProductListener;

     public ProductsAdapter(Context context, ArrayList<Product> list, OnProductListener mOnProductListener) {
         this.context = context;
         this.list = list;
         this.mOnProductListener = mOnProductListener;
     }

     @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View product_view= LayoutInflater.from(context).inflate(R.layout.product_items_layout,parent,false);
        return new ViewHolder(product_view,mOnProductListener);
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Product product=list.get(position);

        holder.product_name.setText(product.getPname());
        holder.product_description.setText(product.getDescription());
        holder.product_price.setText(product.getPrice());
        holder.product_image.setImageResource(R.drawable.mobiles);

    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnProductListener onProductListener;
        private TextView product_name;
        private TextView product_description;
        private TextView product_price;
        private ImageView product_image;

        public ViewHolder(@NonNull View itemView,OnProductListener onProductListener) {

            super(itemView);
            this.onProductListener=onProductListener;

            product_name=itemView.findViewById(R.id.product_name);
            product_price=itemView.findViewById(R.id.product_price);
            product_description=itemView.findViewById(R.id.product_description);
            product_image=itemView.findViewById(R.id.product_image);

        }

        @Override
        public void onClick(View v) {
            onProductListener.onProductClick(getAdapterPosition());
        }
    }

    public interface OnProductListener{

        void onProductClick(int position);

    }
}
