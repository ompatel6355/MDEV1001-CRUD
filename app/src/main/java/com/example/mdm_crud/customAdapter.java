package com.example.mdm_crud;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class customAdapter extends RecyclerView.Adapter<customAdapter.MyViewHolder> {
    Context context;
    ArrayList movie_id, movie_name, movie_actor, movie_rating;

    customAdapter(Context context, ArrayList movie_id, ArrayList movie_name, ArrayList movie_actor, ArrayList movie_rating) {
        this.context = context;
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_actor = movie_actor;
        this.movie_rating = movie_rating;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.movie_id_txt.setText(String.valueOf(movie_id.get(position)));
        holder.movie_name_txt.setText(String.valueOf(movie_name.get(position)));
        holder.movie_actor_txt.setText(String.valueOf(movie_actor.get(position)));
        holder.movie_rating_txt.setText(String.valueOf(movie_rating.get(position)));

        holder.mainLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("id", String.valueOf(movie_id.get(position)));
            intent.putExtra("title", String.valueOf(movie_name.get(position)));
            intent.putExtra("actor", String.valueOf(movie_actor.get(position)));
            intent.putExtra("rating", String.valueOf(movie_rating.get(position)));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movie_id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView movie_id_txt, movie_name_txt, movie_actor_txt, movie_rating_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_id_txt = itemView.findViewById(R.id.movie_id_txt);
            movie_name_txt = itemView.findViewById(R.id.movie_title_txt);
            movie_actor_txt = itemView.findViewById(R.id.movie_actor_txt);
            movie_rating_txt = itemView.findViewById(R.id.movie_rating_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
