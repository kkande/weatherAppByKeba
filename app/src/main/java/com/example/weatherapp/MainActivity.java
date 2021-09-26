package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.ui.fragment.CityFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    Spinner spinner;
    private Activity activity;
    CityFragment cityFragment;
    Toast toast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;

        spinner = findViewById(R.id.spinner);
        final List<String> list = new ArrayList<String>();
        list.add(getString(R.string.selectionner));
        list.add(getString(R.string.abidjan));
        list.add(getString(R.string.lyon));
        list.add(getString(R.string.londres));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {

                String item = (String) spinner.getSelectedItem();

                if (i != 0 && item.equals(getString(R.string.lyon))) {
                    setCityFragment();
                    dataAdapter.notifyDataSetChanged();
                    cityFragment.getCurrentDataByName("Lyon");
                }
                else if (i != 0 && item.equals(getString(R.string.londres))){
                    setCityFragment();
                    dataAdapter.notifyDataSetChanged();
                    cityFragment.getCurrentDataByName("Londres");
                }
                else if (i != 0 && item.equals(getString(R.string.abidjan))){
                    setCityFragment();
                    dataAdapter.notifyDataSetChanged();
                    cityFragment.getCurrentDataByName("Abidjan");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setCityFragment(){
        FragmentManager fm = getSupportFragmentManager();
        cityFragment = new CityFragment();
        fm.beginTransaction().replace(R.id.container,cityFragment).addToBackStack(null).commit();
    }
}