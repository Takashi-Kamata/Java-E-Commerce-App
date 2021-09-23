package com.example.videogameapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DetailsActivity extends AppCompatActivity implements ListAdapter.ItemClickListener, CarouselAdapter.ItemClickListener {
    CarouselAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String game_name = getIntent().getStringExtra("GAMENAME");

        // Locate the index of the selected game using game name
        int index = IntStream.range(0, MainActivity.data.size())
                .filter(i -> MainActivity.data.get(i).getName().equals(game_name))
                .findFirst().orElse(-1);

        // Increment View Count
        MainActivity.data.get(index).addViewCount();



        Resources res = getResources();
        String mDrawableName1 = MainActivity.data.get(index).getImage1Address();
        String mDrawableName2 = MainActivity.data.get(index).getImage2Address();
        String mDrawableName3 = MainActivity.data.get(index).getImage3Address();
        int resID1 = res.getIdentifier(mDrawableName1 , "drawable", getPackageName());
        int resID2 = res.getIdentifier(mDrawableName2 , "drawable", getPackageName());
        int resID3 = res.getIdentifier(mDrawableName3 , "drawable", getPackageName());


        ArrayList<Integer> gameNames = new ArrayList<>();
        gameNames.add(resID1);
        gameNames.add(resID2);
        gameNames.add(resID3);

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.carousel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CarouselAdapter(this, gameNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.scrollToPosition(gameNames.size()-1);


        TextView title = (TextView)findViewById(R.id.details_name);
        title.setText(MainActivity.data.get(index).getName());

        ImageView cover = (ImageView)findViewById(R.id.details_cover);
        String coverAddress = MainActivity.data.get(index).getCoverAddress();
        int coverID = getResources().getIdentifier(coverAddress, "drawable", getPackageName());
        cover.setImageResource(coverID);

        TextView price = (TextView)findViewById(R.id.details_price);
        price.setText("$" + String.format("%.2f", MainActivity.data.get(index).getPrice()));

        TextView genre = (TextView)findViewById(R.id.details_genre);
        genre.setText(String.valueOf(MainActivity.data.get(index).getGenre()));

        TextView condition = (TextView)findViewById(R.id.details_condition);
        String condition_txt = "Brand New";
        if (MainActivity.data.get(index).getPreOwned()) {
            condition_txt = "Second Hand";
        }
        condition.setText(String.valueOf(condition_txt));

        String description_text =MainActivity.data.get(index).getName().replaceAll("\\s+", "").replaceAll(":", "").replaceAll("'", "").replaceAll("-", "");
        int descriptionID = res.getIdentifier(description_text, "string", getPackageName());
        TextView description = (TextView)findViewById(R.id.details_blurb);
        description.setText(String.valueOf(getString(descriptionID)));

        RatingBar rating = (RatingBar)findViewById(R.id.details_rating);
        rating.setRating(MainActivity.data.get(index).getRating());
    }


    @Override
    public void onItemClick(View view, int position) {

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