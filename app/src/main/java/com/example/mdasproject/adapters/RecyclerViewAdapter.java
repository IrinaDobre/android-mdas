package com.example.mdasproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mdasproject.BookDetailsActivity;
import com.example.mdasproject.R;
import com.example.mdasproject.classes.Book;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<Book> mData;
    private RequestOptions options;

    public RecyclerViewAdapter() {
        this.mData = new ArrayList<>();
    }

    public RecyclerViewAdapter(Context mContext, List<Book> mData) {
        this.mContext = mContext;
        this.mData = mData;

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
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardViewHolder;
        ImageView ivThumbnail ;
        TextView tvTitle, tvPrice, tvAuthor;
        LinearLayout container;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivThumbnail = itemView.findViewById(R.id.thumbnail);
            tvTitle = itemView.findViewById(R.id.title);
            tvAuthor = itemView.findViewById(R.id.author);
            tvPrice = itemView.findViewById(R.id.price);
            container = itemView.findViewById(R.id.container);
            cardViewHolder = itemView.findViewById(R.id.cardViewHolder);
        }
    }
}
