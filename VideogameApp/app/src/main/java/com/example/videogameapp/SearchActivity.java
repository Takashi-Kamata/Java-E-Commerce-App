package com.example.videogameapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.example.videogameapp.DataProvider.generateData;

public class SearchActivity extends AppCompatActivity implements ListAdapter.ItemClickListener {
    ListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        String search_key = getIntent().getStringExtra("SEARCH_KEY");
        Log.d("search_key", search_key);
        TextView search = (TextView)findViewById(R.id.search_key);
        search.setText("Search result for " + search_key);

        //Set up filtered game list
        ArrayList<Videogame> Filtered = MainActivity.data
                .stream()
                .filter(c -> (c.getName().toLowerCase().replaceAll("[\\-':,]","").contains(search_key.toLowerCase().replaceAll("[\\-':,]",""))))
                .collect(Collectors.toCollection(ArrayList::new));

        if (Filtered.size() == 0){
            search.setText("No search results for \"" + search_key + "\"");
        } else {
            search.setText("Search results for \"" + search_key + "\"");
        }

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.search_result);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListAdapter(this, Filtered);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d("item_click", "Clicked " + adapter.getItem(position) + " on row number " + position);
        Intent intentMain = new Intent(SearchActivity.this ,
                DetailsActivity.class);

        //Parse a string to next activity!
        //https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android-application
        intentMain.putExtra("GAMENAME", adapter.getItem(position));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                view.findViewById(R.id.gameIcon_row), "details_cover");

        SearchActivity.this.startActivity(intentMain, options.toBundle());
    }
    // when the user pressed back button this function
    // get invoked automatically.
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}