package com.example.videogameapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import java.util.ArrayList;

import static com.example.videogameapp.DataProvider.generateData;

public class MainActivity extends AppCompatActivity implements TopPicksAdapter.ItemClickListener {
    ///////////////////////////////// TEMPORARY DATA /////////////////////////////////
    public static ArrayList<Videogame> data = generateData();
    ///////////////////////////////// -------------- /////////////////////////////////
    private RecyclerView mRecyclerView;
    TopPicksAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //New and Old button
        Button new_button = (Button) findViewById(R.id.new_button);
        Button old_button = (Button) findViewById(R.id.old_button);

        new_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intentMain = new Intent(MainActivity.this ,
                        ListActivity.class);
                intentMain.putExtra("FILTER_MODE", 2);// Used to indicate filter
                MainActivity.this.startActivity(intentMain);
                overridePendingTransition(R.anim.slide_in_right,
                       R.anim.slide_out_left);

            }
        });
        old_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intentMain = new Intent(MainActivity.this ,
                        ListActivity.class);
                intentMain.putExtra("FILTER_MODE", 3);// Used to indicate filter
                MainActivity.this.startActivity(intentMain);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);

            }
        });


        // Locate the filter icon
        ImageView filter = (ImageView)findViewById(R.id.filter_icon);

        filter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // set the color to relative layout
                Log.d("FltrClk", "Clicked!");

                Intent intentMain = new Intent(MainActivity.this ,
                        FilterActivity.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                MainActivity.this.startActivity(intentMain, options.toBundle());
            }
        });



        // Locate the search view
        SearchView searchView = (SearchView) findViewById(R.id.search_text);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // Executes when the user submit the text/return
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("searched", query);
                Intent intentMain = new Intent(MainActivity.this ,
                        SearchActivity.class);
                //Parse a string to next activity!
                //https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android-application
                intentMain.putExtra("SEARCH_KEY", query);
                MainActivity.this.startActivity(intentMain);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
                return false;
            }

            // Updates every time a character is changed
            @Override
            public boolean onQueryTextChange(String newText) {
                //Log.i("well", newText);
                return false;
            }
        });

        // Locate the linear layers
        LinearLayout genre1 = (LinearLayout)findViewById(R.id.genres1);
        LinearLayout genre2 = (LinearLayout)findViewById(R.id.genres2);
        LinearLayout genre3 = (LinearLayout)findViewById(R.id.genres3);

        genre1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // set the color to relative layout
                Log.d("ImgClk", "Clicked!");
                Intent intentMain = new Intent(MainActivity.this ,
                        ListActivity.class);

                FilterActivity.category = 1;
                MainActivity.this.startActivity(intentMain);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        genre2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // set the color to relative layout
                Log.d("ImgClk", "Clicked!");
                Intent intentMain = new Intent(MainActivity.this ,
                        ListActivity.class);

                FilterActivity.category = 2;
                MainActivity.this.startActivity(intentMain);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);

            }
        });

        genre3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // set the color to relative layout
                Log.d("ImgClk", "Clicked!");
                Intent intentMain = new Intent(MainActivity.this ,
                        ListActivity.class);

                FilterActivity.category = 3;
                MainActivity.this.startActivity(intentMain);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });
    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        //testing Top Items Recyclerview
        ArrayList<Videogame> topPicks = new ArrayList<Videogame>();
        ArrayList<Integer> top_three = findTopThree(MainActivity.data);
        topPicks.add(data.get(top_three.get(0)));
        topPicks.add(data.get(top_three.get(1)));
        topPicks.add(data.get(top_three.get(2)));

        mRecyclerView = findViewById(R.id.top_picks_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mAdapter = new TopPicksAdapter(topPicks);
        mAdapter.setClickListener(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


    public void onItemClick(View view, int position) {
        Intent intentMain = new Intent(MainActivity.this ,
                DetailsActivity.class);

        //Parse a string to next activity!
        //https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android-application
        intentMain.putExtra("GAMENAME", mAdapter.getItem(position));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                view.findViewById(R.id.top_pick_image), "details_cover");

        MainActivity.this.startActivity(intentMain, options.toBundle());
    }

    public ArrayList<Integer> findTopThree(ArrayList<Videogame> list) {
        int i, first, second, third;
        third = first = second = Integer.MIN_VALUE;
        // https://www.geeksforgeeks.org/find-the-largest-three-elements-in-an-array/
        for (i = 0; i < list.size(); i++) {
            if (list.get(i).getViewCount() > first) {
                third = second;
                second = first;
                first = i;
            }
            else if (list.get(i).getViewCount() > second) {
                third = second;
                second = i;
            }
            else if (list.get(i).getViewCount() > third)
                third = i;
        }
        ArrayList<Integer> top_three = new ArrayList<Integer>();
        top_three.add(first);
        top_three.add(second);
        top_three.add(third);
        return top_three;
    }


}