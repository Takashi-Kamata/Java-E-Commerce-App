package com.example.videogameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.Rating;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

public class FilterActivity extends AppCompatActivity {

    // 0 : ALL, 1 : NEW, 2 : OLD,
    public static Integer condition = 0;

    // 0 : ALL, 1 : GENRE1, 2 : GENRE2, 3 : GENRE3
    public static Integer category = 0;

    // Price Max
    public static float price_max = 200;

    // Price Min
    public static float price_min = 0;

    // Rating Max
    public static float rating_max = 5;

    // Rating Min
    public static float rating_min = 0;

    // Sort Mode
    public static Integer sort_mode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        category = 0;
        Spinner spinner = (Spinner) findViewById(R.id.sort_spinner);
        //Array Adapter with the given string array with a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sort_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sort_mode = position;
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button condition_all = (Button) findViewById(R.id.condition_all);
        Button condition_new = (Button) findViewById(R.id.condition_new);
        Button condition_old = (Button) findViewById(R.id.condition_pre_owned);

        if (condition == 0) {
            condition_all.setBackgroundColor(Color.parseColor("#E30425"));
            condition_all.setTextColor(Color.WHITE);
            condition_new.setBackgroundColor(Color.WHITE);
            condition_new.setTextColor(Color.BLACK);
            condition_old.setBackgroundColor(Color.WHITE);
            condition_old.setTextColor(Color.BLACK);
        } else if (condition == 1) {
            condition_all.setBackgroundColor(Color.WHITE);
            condition_all.setTextColor(Color.BLACK);
            condition_new.setBackgroundColor(Color.parseColor("#E30425"));
            condition_new.setTextColor(Color.WHITE);
            condition_old.setBackgroundColor(Color.WHITE);
            condition_old.setTextColor(Color.BLACK);
        } else if  (condition == 2) {
            condition_all.setBackgroundColor(Color.WHITE);
            condition_all.setTextColor(Color.BLACK);
            condition_new.setBackgroundColor(Color.WHITE);
            condition_new.setTextColor(Color.BLACK);
            condition_old.setBackgroundColor(Color.parseColor("#E30425"));
            condition_old.setTextColor(Color.WHITE);
        }

        condition_all.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // set the color to relative layout
                Log.d("condition", "ALL");
                condition_all.setBackgroundColor(Color.parseColor("#E30425"));
                condition_all.setTextColor(Color.WHITE);
                condition_new.setBackgroundColor(Color.WHITE);
                condition_new.setTextColor(Color.BLACK);
                condition_old.setBackgroundColor(Color.WHITE);
                condition_old.setTextColor(Color.BLACK);
                condition = 0;
            }
        });

        condition_new.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // set the color to relative layout
                Log.d("condition", "ALL");
                condition_all.setBackgroundColor(Color.WHITE);
                condition_all.setTextColor(Color.BLACK);
                condition_new.setBackgroundColor(Color.parseColor("#E30425"));
                condition_new.setTextColor(Color.WHITE);
                condition_old.setBackgroundColor(Color.WHITE);
                condition_old.setTextColor(Color.BLACK);
                condition = 1;
            }
        });

        condition_old.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // set the color to relative layout
                Log.d("condition", "ALL");
                condition_all.setBackgroundColor(Color.WHITE);
                condition_all.setTextColor(Color.BLACK);
                condition_new.setBackgroundColor(Color.WHITE);
                condition_new.setTextColor(Color.BLACK);
                condition_old.setBackgroundColor(Color.parseColor("#E30425"));
                condition_old.setTextColor(Color.WHITE);
                condition = 2;
            }
        });

        Button category_all = (Button) findViewById(R.id.genre_all);
        Button category_genre1 = (Button) findViewById(R.id.genre_1);
        Button category_genre2 = (Button) findViewById(R.id.genre_2);
        Button category_genre3 = (Button) findViewById(R.id.genre_3);

        if (category == 0) {
            category_all.setBackgroundColor(Color.parseColor("#E30425"));
            category_all.setTextColor(Color.WHITE);
            category_genre1.setBackgroundColor(Color.WHITE);
            category_genre1.setTextColor(Color.BLACK);
            category_genre2.setBackgroundColor(Color.WHITE);
            category_genre2.setTextColor(Color.BLACK);
            category_genre3.setBackgroundColor(Color.WHITE);
            category_genre3.setTextColor(Color.BLACK);
        } else if (category == 1) {
            category_all.setBackgroundColor(Color.WHITE);
            category_all.setTextColor(Color.BLACK);
            category_genre1.setBackgroundColor(Color.parseColor("#E30425"));
            category_genre1.setTextColor(Color.WHITE);
            category_genre2.setBackgroundColor(Color.WHITE);
            category_genre2.setTextColor(Color.BLACK);
            category_genre3.setBackgroundColor(Color.WHITE);
            category_genre3.setTextColor(Color.BLACK);
        } else if (category == 2) {
            category_all.setBackgroundColor(Color.WHITE);
            category_all.setTextColor(Color.BLACK);
            category_genre1.setBackgroundColor(Color.WHITE);
            category_genre1.setTextColor(Color.BLACK);
            category_genre2.setBackgroundColor(Color.parseColor("#E30425"));
            category_genre2.setTextColor(Color.WHITE);
            category_genre3.setBackgroundColor(Color.WHITE);
            category_genre3.setTextColor(Color.BLACK);
        } else if (category == 3) {
            category_all.setBackgroundColor(Color.WHITE);
            category_all.setTextColor(Color.BLACK);
            category_genre1.setBackgroundColor(Color.WHITE);
            category_genre1.setTextColor(Color.BLACK);
            category_genre2.setBackgroundColor(Color.WHITE);
            category_genre2.setTextColor(Color.BLACK);
            category_genre3.setBackgroundColor(Color.parseColor("#E30425"));
            category_genre3.setTextColor(Color.WHITE);
        }

        category_all.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                category_all.setBackgroundColor(Color.parseColor("#E30425"));
                category_all.setTextColor(Color.WHITE);
                category_genre1.setBackgroundColor(Color.WHITE);
                category_genre1.setTextColor(Color.BLACK);
                category_genre2.setBackgroundColor(Color.WHITE);
                category_genre2.setTextColor(Color.BLACK);
                category_genre3.setBackgroundColor(Color.WHITE);
                category_genre3.setTextColor(Color.BLACK);
                category = 0;
            }
        });

        category_genre1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                category_all.setBackgroundColor(Color.WHITE);
                category_all.setTextColor(Color.BLACK);
                category_genre1.setBackgroundColor(Color.parseColor("#E30425"));
                category_genre1.setTextColor(Color.WHITE);
                category_genre2.setBackgroundColor(Color.WHITE);
                category_genre2.setTextColor(Color.BLACK);
                category_genre3.setBackgroundColor(Color.WHITE);
                category_genre3.setTextColor(Color.BLACK);
                category = 1;
            }
        });

        category_genre2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                category_all.setBackgroundColor(Color.WHITE);
                category_all.setTextColor(Color.BLACK);
                category_genre1.setBackgroundColor(Color.WHITE);
                category_genre1.setTextColor(Color.BLACK);
                category_genre2.setBackgroundColor(Color.parseColor("#E30425"));
                category_genre2.setTextColor(Color.WHITE);
                category_genre3.setBackgroundColor(Color.WHITE);
                category_genre3.setTextColor(Color.BLACK);
                category = 2;
            }
        });

        category_genre3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                category_all.setBackgroundColor(Color.WHITE);
                category_all.setTextColor(Color.BLACK);
                category_genre1.setBackgroundColor(Color.WHITE);
                category_genre1.setTextColor(Color.BLACK);
                category_genre2.setBackgroundColor(Color.WHITE);
                category_genre2.setTextColor(Color.BLACK);
                category_genre3.setBackgroundColor(Color.parseColor("#E30425"));
                category_genre3.setTextColor(Color.WHITE);
                category = 3;
            }
        });

        RatingBar rating_max_num = (RatingBar) findViewById(R.id.rating_max);
        RatingBar rating_min_num = (RatingBar) findViewById(R.id.rating_min);
        rating_max_num.setRating(rating_max);
        rating_min_num.setRating(rating_min);

        rating_max_num.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                rating_max = rating_max_num.getRating();
            }
        });

        rating_min_num.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                rating_min = rating_min_num.getRating();
            }
        });

        EditText price_max_num = (EditText) findViewById(R.id.pricenum_max);
        EditText price_min_num = (EditText) findViewById(R.id.pricenum_min);

        price_max_num.setText(String.valueOf(price_max));
        price_min_num.setText(String.valueOf(price_min));

        price_max_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //price_max = Float.valueOf(price_max_num.getText().toString());
                if (!price_max_num.getText().toString().isEmpty()) {
                    price_max = Float.valueOf(price_max_num.getText().toString());
                } else if (price_max_num.getText().toString().isEmpty()) {
                    price_max = Float.valueOf(0);
                }
            }
        });
        price_min_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!price_min_num.getText().toString().isEmpty()) {
                    price_min = Float.valueOf(price_min_num.getText().toString());
                } else if (price_min_num.getText().toString().isEmpty()) {
                    price_min = Float.valueOf(0);
                }

            }
        });


        Button execute = (Button) findViewById(R.id.filter_execute);
        Button cancel = (Button) findViewById(R.id.filter_cancel);

        execute.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Save filter settings
                Intent intentMain = new Intent(FilterActivity.this ,
                        ListActivity.class);
                intentMain.putExtra("FILTER_MODE", 1);// Used to indicate filter
                FilterActivity.this.startActivity(intentMain);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });




        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Cancel
                onBackPressed();
            }
        });
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