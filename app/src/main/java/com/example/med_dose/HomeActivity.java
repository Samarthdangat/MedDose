package com.example.med_dose;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

import com.abdsoft.med_dose.R;
import com.example.med_dose.ui.home.TimeItem;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    public static ArrayList<TimeItem> timeItems= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MaterialToolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_bar, menu);
        return true;
    }

}
