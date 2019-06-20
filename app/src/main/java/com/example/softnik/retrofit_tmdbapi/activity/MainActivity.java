package com.example.softnik.retrofit_tmdbapi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.softnik.retrofit_tmdbapi.R;
import com.example.softnik.retrofit_tmdbapi.adapter.MovieAdapter;
import com.example.softnik.retrofit_tmdbapi.rest.ApiInterface;
import com.example.softnik.retrofit_tmdbapi.model.Movie;
import com.example.softnik.retrofit_tmdbapi.model.MovieResponse;
import com.example.softnik.retrofit_tmdbapi.rest.APIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG=MainActivity.class.getSimpleName();
    public static final String API_KEY="3940034e5a6e35634314b9ba2f974a6e";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if(API_KEY.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please obtain your API Key first from themoviedb.org",Toast.LENGTH_SHORT).show();
            return;
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService = APIClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();

                recyclerView.setAdapter(new MovieAdapter(movies,getApplicationContext()));

                Log.d(TAG, "Number of Movies Recieived : " + movies.size());

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG,t.toString());
            }
        });
    }
}
