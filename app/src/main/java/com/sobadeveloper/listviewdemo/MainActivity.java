package com.sobadeveloper.listviewdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/**
 * X402.41 - Advanced Application for Android (Fall 2014)
 * Assignment 1
 * MainActivity.java
 * Purpose: Main activity displaying listview of items
 * @author Levi Hsiao
 *    
 * Library used: opencsv-3.0
 *
 */
public class MainActivity extends ActionBarActivity {

	private ArrayList<Country> countryList;
	private CountryArrayAdapter arrayAdapter;
	private ListView listView;
	private ProgressDialog progressDialog;
    private Toolbar toolbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        // Set up Toolbar
	    toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Bind ListView to ArrayAdapter
		countryList = new ArrayList<>();
		listView = (ListView)findViewById(R.id.listView);
        arrayAdapter = new CountryArrayAdapter(this, R.layout.item_row, countryList);
		listView.setAdapter(arrayAdapter);
		 
		// Start ItemActivity when row is clicked
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				 
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position,
						long id) {
					Country country = countryList.get(position);
					Intent i = new Intent(MainActivity.this, ItemActivity.class);
					i.putExtra("country", country);
					startActivity(i);
				}
			});
		 
		 progressDialog = new ProgressDialog(this);
		 new MyTask().execute();
	}
	
	// Use AsyncTask to load data from local assets folder
	public class MyTask extends AsyncTask <Void, Void, List<Country>>{		
		
		//Show progress dialog
		@Override
		protected void onPreExecute() {
			 progressDialog.setTitle("Loading...");
			 progressDialog.setMessage("Please wait.");
			 progressDialog.setCancelable(false);
			 progressDialog.show();
		}

		//Retrieve csv file and parse the contents with Opencsv (http://opencsv.sourceforge.net/)
		@Override
		protected List<Country> doInBackground(Void... params) {
			
			ArrayList<Country>data = new ArrayList<>();
			AssetManager assetManager = getAssets();
			try{	
				InputStream stream = assetManager.open("data/europe.csv");
				
				CSVReader csv = new CSVReader(new InputStreamReader(stream));
				String[] line;
				
				while((line = csv.readNext()) !=null){
					data.add(new Country(line[0], line[1], line[2], line[3], line[4]));
				}
				csv.close();	
				 
				return data;
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
			return null;
		}

		// Dismiss the progress dialog on completion
		@Override
		protected void onPostExecute(List<Country> result) {
			countryList.clear();
			countryList.addAll(result);
            progressDialog.dismiss();
		}	
	}
}
