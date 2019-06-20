package com.example.softnik.retrofit_tmdbapi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.softnik.retrofit_tmdbapi.R;
import com.example.softnik.retrofit_tmdbapi.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie>movies;
    private Context context;

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_movies,viewGroup,false);
        return new MovieViewHolder(view) ;

    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int i) {
        holder.movieTitle.setText(movies.get(i).getTitle());
        holder.data.setText(movies.get(i).getReleaseDate());
        holder.movieDescription.setText(movies.get(i).getOverview());
        holder.rating.setText(movies.get(i).getVoteAverage().toString());

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle=itemView.findViewById(R.id.title);
            data=itemView.findViewById(R.id.subtitle);
            movieDescription=itemView.findViewById(R.id.description);
            rating=itemView.findViewById(R.id.rating);
        }
    }

    public MovieAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }
}
