package com.example.mdasproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mdasproject.R;
import com.example.mdasproject.classes.ShoppingCartItem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyViewHolder> {
    private Context mContext;
    private List<ShoppingCartItem> shoppingList;
    private RequestOptions options;

    public ShoppingCartAdapter() {
        this.shoppingList = new ArrayList<>();
    }

    public ShoppingCartAdapter(Context mContext, List<ShoppingCartItem> shoppingList) {
        this.mContext = mContext;
        this.shoppingList = shoppingList;

        options = new RequestOptions().centerCrop().placeholder(R.drawable.load).error(R.drawable.load);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.cart_item, parent, false);
        final MyViewHolder viewHolder =  new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        ShoppingCartItem shoppingCartItem = shoppingList.get(i);
        holder.tvTitle.setText(shoppingCartItem.getTitle());
        holder.tvAuthor.setText(shoppingCartItem.getAuthors());
        holder.tvTotalPrice.setText(Double.toString(shoppingCartItem.getTotalPrice()));
        holder.tvQuantity.setText(Integer.toString(shoppingCartItem.getQuantity()));

        if (shoppingCartItem.getImageBook() != null) {
            Glide.with(mContext).load(shoppingCartItem.getImageBook()).apply(options).into(holder.ivImageBook);
        }
    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        CardView cardViewHolder;
        ImageView ivImageBook ;
        TextView tvTitle, tvTotalPrice, tvAuthor, tvQuantity;
        LinearLayout container ;
        public MyViewHolder(View itemView) {
            super(itemView);

            ivImageBook = itemView.findViewById(R.id.imageBookCart);
            tvTitle = itemView.findViewById(R.id.titleCart);
            tvAuthor = itemView.findViewById(R.id.authorCart);
            tvTotalPrice = itemView.findViewById(R.id.totalPrice);
            tvQuantity = itemView.findViewById(R.id.quantityCart);
            container = itemView.findViewById(R.id.containerCart);
            cardViewHolder = itemView.findViewById(R.id.cardViewHolderShoppingCart);
        }
    }
}
