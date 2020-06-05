package com.example.mye_commerceapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mye_commerceapplication.Model.Product;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> implements Filterable {

     public Context context;
     public ArrayList<Product> list;
     public ArrayList<Product> mFilteredList;
     public MyFilter mFilter;


    public ProductsAdapter(Context context, ArrayList<Product> list){//, OnProductListener mOnProductListener) {
         this.context = context;
         this.list = list;
         this.mFilteredList=new ArrayList<Product>();
     }

     @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View product_view= LayoutInflater.from(context).inflate(R.layout.product_items_layout,parent,false);
        return new ViewHolder(product_view);
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {

        final Product product=list.get(position);

        holder.product_name.setText(product.getPname());
        holder.product_description.setText(product.getDescription());
        holder.product_price.setText(product.getPrice()+" MAD");
        //holder.product_image.setImageResource(R.drawable.tshirtt);
//set an image with Picasso
        Picasso.get().load(product.getImage()).into(holder.product_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        System.out.println("click product on :"+position+"  le pid est : "+product.getPid());//marche tres bien
        //Toast.makeText(H, "vous avez clique l'item num : "+position, Toast.LENGTH_SHORT).show();
        //text treatment here ...
        Intent intent = new Intent(context,ProductDetailActivity.class);
        intent.putExtra("pid",product.getPid());
        context.startActivity(intent);
            }
        });

    }

    @Override
    public Filter getFilter() {
        if (mFilter == null){
            mFilteredList.clear();
            mFilteredList.addAll(this.list);
            mFilter = new ProductsAdapter.MyFilter(this,mFilteredList);
        }
        return mFilter;
    }

    class ViewHolder extends RecyclerView.ViewHolder {// implements View.OnClickListener

        //OnProductListener onProductListener;
        TextView product_name;
        TextView product_description;
        TextView product_price;
        ImageView product_image;

        public ViewHolder(@NonNull View itemView) {//,OnProductListener onProductListener) {

            super(itemView);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);
            product_description = itemView.findViewById(R.id.product_description);
            product_image = itemView.findViewById(R.id.product_image);

        }
    }

     private static class MyFilter extends Filter {

         private final ProductsAdapter myAdapter;
         private final ArrayList<Product> originalList;
         private final ArrayList<Product> filteredList;

         private MyFilter(ProductsAdapter myAdapter, ArrayList<Product> originalList) {
             this.myAdapter = myAdapter;
             this.originalList = originalList;
             this.filteredList = new ArrayList<Product>();
         }

         @Override
         protected FilterResults performFiltering(CharSequence charSequence) {

             filteredList.clear();
             final FilterResults results = new FilterResults();
             if (charSequence.length() == 0){
                 filteredList.addAll(originalList);
             }else {
                 final String filterPattern = charSequence.toString().toLowerCase().trim();
                 for ( Product prod : originalList){
                     if (prod.getDescription().toLowerCase().contains(filterPattern)){
                         filteredList.add(prod);
                     }
                 }
             }

             results.values = filteredList;
             results.count = filteredList.size();
             return results;

         }

         @Override
         protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

             myAdapter.list.clear();
             myAdapter.list.addAll((ArrayList<Product>)filterResults.values);
             myAdapter.notifyDataSetChanged();

         }
     }
}
