package com.sobadeveloper.listviewdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * X402.41 - Advanced Application for Android (Fall 2014)
 * Assignment 1
 * CountryArrayAdapter.java
 * Purpose: Custom ArrayAdapter for displaying data in each list row
 *
 * @author Levi Hsiao
 */
public class CountryArrayAdapter extends ArrayAdapter<Country> {

    private Context context;
    private int layoutID;
    private List<Country> countryList = null;

    public CountryArrayAdapter(Context context, int layoutID, List<Country> countryList) {
        super(context, layoutID, countryList);
        this.context = context;
        this.layoutID = layoutID;
        this.countryList = countryList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CountryHolder holder;
        View view = convertView;

        // When convertView is not null, reuse it directly
        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(layoutID, parent, false);

            // Create ViewHolder to store references to the children views to avoid
            // unnecessary calls to findViewById() on each row
            holder = new CountryHolder();
            holder.image = (ImageView) view.findViewById(R.id.iv_icon);
            holder.textView = (TextView) view.findViewById(R.id.tv_name);
            holder.button = (Button) view.findViewById(R.id.btn_capital);
            view.setTag(holder);
        } else {
            holder = (CountryHolder) view.getTag();
        }

        final Country country = countryList.get(position);
        holder.textView.setText(country.getName());

        //Display image by retrieving resource ID of file from drawable folder
        String countryName = country.getName();
        countryName = countryName.replaceAll(" ", "_").toLowerCase();
        int id = getContext().getResources().getIdentifier
                (countryName, "drawable", getContext().getPackageName());
        holder.image.setImageResource(id);

        //Display toast on button click
        holder.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getContext(),
                        country.getCapital(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return view;
    }

    static class CountryHolder {
        ImageView image;
        TextView textView;
        Button button;
    }
}
