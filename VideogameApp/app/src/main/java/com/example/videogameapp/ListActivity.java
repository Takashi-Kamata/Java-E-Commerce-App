package com.example.videogameapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.videogameapp.DataProvider.generateData;

// Referenced https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example
public class ListActivity extends AppCompatActivity implements ListAdapter.ItemClickListener {
    ListAdapter adapter;
    private static final String COVER_ID = "COVER_ID";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Integer filter_mode = getIntent().getIntExtra("FILTER_MODE" , 0);

        String category = "";
        switch (FilterActivity.category) {
            case 0:
                category = "ALL";
                break;
            case 1:
                category = "RPG";
                break;
            case 2:
                category = "Action";
                break;
            case 3:
                category = "FPS";
                break;
        }

        final String target_genre = category;
        final Integer target_condition = FilterActivity.condition;
        final float target_rating_max = FilterActivity.rating_max;
        final float target_rating_min = FilterActivity.rating_min;

        final float target_price_max = FilterActivity.price_max;
        final float target_price_min = FilterActivity.price_min;
        final Integer target_sort = FilterActivity.sort_mode;

        ArrayList<Videogame> Sorted = MainActivity.data;
        if (target_sort == 1) {// A-Z
            Collections.sort(Sorted, new Videogame.CustomComparatorName());
        } else if (target_sort == 2) {// A-Z reversed
            Collections.sort(Sorted, new Videogame.CustomComparatorName());
            Collections.reverse(Sorted);

        } else if (target_sort == 3) {// Rating
            Collections.sort(Sorted, new Videogame.CustomComparatorRating());
        } else if (target_sort == 4) {// Rating reversed
            Collections.sort(Sorted, new Videogame.CustomComparatorRating());
            Collections.reverse(Sorted);

        } else if (target_sort == 5) {// Price
            Collections.sort(Sorted, new Videogame.CustomComparatorPrice());
        } else if (target_sort == 6) {// Price reversed
            Collections.sort(Sorted, new Videogame.CustomComparatorPrice());
            Collections.reverse(Sorted);
        }


        // Nasty sorting condition.... :(
        ArrayList<Videogame> Filtered = Sorted
                .stream()
                .filter(c -> ((filter_mode == 1 &&
                                (target_genre.equals("ALL") || c.getGenre().equals(target_genre)) && // Filter with genre
                                (target_condition.equals(0) || (c.getPreOwned() ? 2 : 1) == target_condition) && // Filter with its condition
                                (c.getRating() <= target_rating_max && c.getRating() >= target_rating_min) &&
                                ((target_price_max == 0.0) || (c.getPrice() <= target_price_max && c.getPrice() >= target_price_min)))

                                || (filter_mode == 0 && // Only Filter genre (this is only when the user access the list activity from 3 genre card views from main)
                                (target_genre.equals("ALL") || c.getGenre().equals(target_genre))
                                || (filter_mode == 2 && // Only show New
                                ((c.getPreOwned() ? 2 : 1) == 1))
                                || (filter_mode == 3 && // Only show Old
                                ((c.getPreOwned() ? 2 : 1) == 2))

                )))
                .collect(Collectors.toCollection(ArrayList::new));
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListAdapter(this, Filtered);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }


    public void onItemClick(View view, int position) {
        //Log.d("item_click", "Clicked " + adapter.getItem(position) + " on row number " + position);
        Intent intentMain = new Intent(ListActivity.this ,
                DetailsActivity.class);

        //Parse a string to next activity!
        //https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android-application
        intentMain.putExtra("GAMENAME", adapter.getItem(position));
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                view.findViewById(R.id.gameIcon_row), "details_cover");

        ListActivity.this.startActivity(intentMain, options.toBundle());
        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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