package com.sobadeveloper.listviewdemo;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * X402.41 - Advanced Application for Android (Fall 2014)
 * Assignment 1
 * ItemActivity.java
 * Purpose: Display in-depth data for the selected row item
 *
 * @author Levi Hsiao
 */
public class ItemActivity extends ActionBarActivity {

    private TextView tvName, tvCapital, tvPopulation, tvArea, tvGDP;
    private ImageView ivCountry;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        // Set up Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get UI components
        tvName = (TextView) findViewById(R.id.tv_countryname);
        tvCapital = (TextView) findViewById(R.id.tv_capital);
        tvPopulation = (TextView) findViewById(R.id.tv_population);
        tvArea = (TextView) findViewById(R.id.tv_area);
        tvGDP = (TextView) findViewById(R.id.tv_gdp);
        ivCountry = (ImageView) findViewById(R.id.iv_country);

        // Retrieve Country object
        Intent intent = getIntent();
        Country country = (Country) intent.getSerializableExtra("country");

        tvName.setText(country.getName());
        tvCapital.setText(country.getCapital());
        tvPopulation.setText(country.getPopulation());
        tvArea.setText(country.getArea());
        tvGDP.setText(country.getGdp());
        loadImageFromAsset(country.getName());
    }

    // Retrieve and display image of the selected country from assets
    private void loadImageFromAsset(String country) {
        AssetManager assets = getAssets();
        InputStream stream;
        try {
            // get an InputStream to the asset representing the image
            stream = assets.open("images/" + country + ".png");

            // load the asset as a drawable and display in imageview
            Drawable image = Drawable.createFromStream(stream, country);
            ivCountry.setImageDrawable(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}