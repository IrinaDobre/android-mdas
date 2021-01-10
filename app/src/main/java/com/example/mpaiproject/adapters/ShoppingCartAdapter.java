package com.example.mpaiproject.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mpaiproject.LoginActivity;
import com.example.mpaiproject.R;
import com.example.mpaiproject.models.interfaces.RetrofitClient;
import com.example.mpaiproject.models.ShoppingCartItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyViewHolder> {
    private Context mContext;
    private List<ShoppingCartItem> shoppingList;
    private RequestOptions options;
    private RetrofitClient retrofitClient;

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

        holder.tvIncreaseQty.setOnClickListener((v) -> {
            ShoppingCartItem s = shoppingList.get(i);
            s.setQuantity(s.getQuantity() + 1);
            s.setTotalPrice(s.getQuantity() * s.getInitialPrice());
            notifyDataSetChanged();
            holder.tvQuantity.setText(String.valueOf(s.getQuantity()));
        });

        holder.tvDecreaseQty.setOnClickListener((v) -> {
            ShoppingCartItem s = shoppingList.get(i);
            if (s.getQuantity() - 1 < 0) {
                Toast.makeText(mContext, "Quantity cannot be negative", Toast.LENGTH_SHORT).show();
            } else {
                s.setQuantity(s.getQuantity() - 1);
                s.setTotalPrice(s.getQuantity() * s.getInitialPrice());
                notifyDataSetChanged();
                holder.tvQuantity.setText(String.valueOf(s.getQuantity()));
            }
        });

        holder.imgDeleteShoppingItem.setOnClickListener((v) -> {
            String title = shoppingList.get(i).getTitle();
            shoppingList.remove(i);
            deleteShoppingItem(title);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        CardView cardViewHolder;
        ImageView ivImageBook, imgDeleteShoppingItem;
        TextView tvTitle, tvTotalPrice, tvAuthor, tvQuantity, tvIncreaseQty, tvDecreaseQty;
        LinearLayout container ;
        public MyViewHolder(View itemView) {
            super(itemView);

            ivImageBook = itemView.findViewById(R.id.imageBookCart);
            tvTitle = itemView.findViewById(R.id.titleCart);
            tvAuthor = itemView.findViewById(R.id.authorCart);
            tvTotalPrice = itemView.findViewById(R.id.totalPrice);
            tvQuantity = itemView.findViewById(R.id.quantityCart);
            tvIncreaseQty = itemView.findViewById(R.id.IncreaseQty);
            tvDecreaseQty = itemView.findViewById(R.id.DecreaseQty);
            imgDeleteShoppingItem = itemView.findViewById(R.id.imgDeleteShoppingItem);
            container = itemView.findViewById(R.id.containerCart);
            cardViewHolder = itemView.findViewById(R.id.cardViewHolderShoppingCart);
        }


    }

    public void deleteShoppingItem(String title) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitClient = retrofit.create(RetrofitClient.class);
        Call<String> call = retrofitClient.deleteShoppingItem(LoginActivity.user.getUsername(),title);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.code() == 200) {
                    Log.i("Delete shopping list item", "Success");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
