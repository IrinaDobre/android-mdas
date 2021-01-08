package com.example.mdasproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mdasproject.BookDetailsActivity;
import com.example.mdasproject.FavoritesListActivity;
import com.example.mdasproject.LoginActivity;
import com.example.mdasproject.R;
import com.example.mdasproject.RetrofitClient;
import com.example.mdasproject.models.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable {
    private Context mContext;
    private List<Book> mData;
    private RequestOptions options;
    private int isDeleteButtonVisible;
    List<Book> mDataAll;
    private RetrofitClient retrofitClient;

    public RecyclerViewAdapter() {
        this.mData = new ArrayList<>();
    }

    public RecyclerViewAdapter(Context mContext, List<Book> mData, int isDeleteButtonVisible) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataAll = new ArrayList<>(mData);
        this.isDeleteButtonVisible = isDeleteButtonVisible;

        options = new RequestOptions().centerCrop().placeholder(R.drawable.load).error(R.drawable.load);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.book_item, parent, false);
        final MyViewHolder viewHolder =  new MyViewHolder(view);
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext , BookDetailsActivity.class);
                int pos = viewHolder.getAdapterPosition();
                i.putExtra("bookTitle" ,mData.get(pos).getTitle());
                i.putExtra("bookAuthor" ,mData.get(pos).getAuthors());
                i.putExtra("bookDesc" ,mData.get(pos).getDescription());
                i.putExtra("bookPublishDate" ,mData.get(pos).getPublishedDate());
                i.putExtra("bookThumbnail" ,mData.get(pos).getThumbnail());
                i.putExtra("bookPrice", mData.get(pos).getPrice());
                if(mContext instanceof FavoritesListActivity)  {
                    i.putExtra("favScreen", 1);
                }
                mContext.startActivity(i);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {

        if(i == 0 || i == 3 || i == 6 || i == 9 || i == 12 || i == 15){
            holder.cardViewHolder.setCardBackgroundColor(Color.parseColor("#9575CD"));
        }
        else if(i==1 || i == 4 || i == 7 || i == 10 || i == 13){
            holder.cardViewHolder.setCardBackgroundColor(Color.parseColor("#FFAB91"));
        }
        else {
            holder.cardViewHolder.setCardBackgroundColor(Color.parseColor("#90CAF9"));
        }

        Book book = mData.get(i);
        holder.tvTitle.setText(book.getTitle());
        holder.tvAuthor.setText(book.getAuthors());
        holder.tvPrice.setText(book.getPrice());

        if(book.getThumbnail() != null){
            Glide.with(mContext).load(book.getThumbnail()).apply(options).into(holder.ivThumbnail);
        }
        if (isDeleteButtonVisible == 1) {
            holder.ivDelete.setVisibility(View.GONE);
        } else {
            holder.ivDelete.setVisibility(View.VISIBLE);
            holder.ivDelete.setOnClickListener(view -> {
                mData.remove(i);
                notifyDataSetChanged();
                deleteFavoriteItem(book.getTitle());
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public Filter getFilter() {

        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Book> filteredList = new ArrayList<>();
            if(charSequence.toString().isEmpty()){
                filteredList.addAll(mDataAll);
            }
            else {
                for(Book b: mDataAll){
                    if(b.getTitle().toLowerCase().contains(charSequence.toString().toLowerCase()) ||
                            b.getAuthors().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredList.add(b);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mData.clear();
            mData.addAll((Collection<? extends Book>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardViewHolder;
        ImageView ivThumbnail, ivDelete ;
        TextView tvTitle, tvPrice, tvAuthor;
        LinearLayout container;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivThumbnail = itemView.findViewById(R.id.thumbnail);
            ivDelete = itemView.findViewById(R.id.imgDeleteFavoriteItem);
            tvTitle = itemView.findViewById(R.id.title);
            tvAuthor = itemView.findViewById(R.id.author);
            tvPrice = itemView.findViewById(R.id.price);
            container = itemView.findViewById(R.id.container);
            cardViewHolder = itemView.findViewById(R.id.cardViewHolder);
        }
    }

    public void deleteFavoriteItem(String title) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitClient = retrofit.create(RetrofitClient.class);
        Call<String> call = retrofitClient.deleteFavoriteItem(LoginActivity.user.getUsername(), title);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.code() == 200) {
                    Log.i("Delete favorite list item", "Success");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
