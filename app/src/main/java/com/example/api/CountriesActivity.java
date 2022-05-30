package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;

public class CountriesActivity extends AppCompatActivity {
    TextView countryname,isocode,fipscode,capital,area,population,currency,languages,neighbours,postalcodeformat;
    ImageView flag,countrymap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        Anhxa();
        Geonames intent = (Geonames) getIntent().getSerializableExtra("chitiet");
        Log.d("XXX", intent.countryName);
        countryname.setText(intent.countryName);
        isocode.setText(String.valueOf(intent.countryCode+", "+intent.isoNumeric+" and "+ intent.isoAlpha3));
        capital.setText(intent.capital);
        fipscode.setText(intent.fipsCode);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,#");
        DecimalFormat decimalFormat1 = new DecimalFormat("###,###,###");
        area.setText(decimalFormat.format(Double.parseDouble(intent.areaInSqKm))+" km²");
        population.setText(decimalFormat1.format(Double.parseDouble(intent.population)));
        currency.setText("Dong ("+intent.currencyCode+")");
        languages.setText(intent.languages);
        neighbours.setText(intent.continentName);
        postalcodeformat.setText(intent.postalCodeFormat);
        Glide.with(this).load("http://img.geonames.org/flags/x/"+intent.countryCode.toLowerCase()+".gif").into(flag);
        Glide.with(this).load("http://img.geonames.org/img/country/250/"+intent.countryCode+".png").into(countrymap);
    }

    private void Anhxa() {
        countryname = findViewById(R.id.countryname);
        isocode = findViewById(R.id.isocode);
        fipscode = findViewById(R.id.fipscode);
        capital = findViewById(R.id.capital);
        area = findViewById(R.id.area);
        population = findViewById(R.id.population);
        currency = findViewById(R.id.currency);
        languages = findViewById(R.id.languages);
        neighbours = findViewById(R.id.neighbours);
        postalcodeformat = findViewById(R.id.postalcodeformat);
        flag = findViewById(R.id.flag);
        countrymap = findViewById(R.id.countrymap);
    }
}