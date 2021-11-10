package com.allam.ets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.allam.ets.api.ApiActivity;
import com.allam.ets.api.json.JsonActivity;
import com.allam.ets.contact.ContactActivity;
import com.allam.ets.gps.GpsActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnKontak, btnKeluar, btnGps, btnApi, btnJsonApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("ETS");

        setupItemView();
        setupView();

    }

    private void setupItemView(){
        //Button
        btnKontak = findViewById(R.id.btnKontak);
        btnGps = findViewById(R.id.btnGps);
        btnApi = findViewById(R.id.btnApi);
        btnJsonApi = findViewById(R.id.btnJsonApi);
        btnKeluar = findViewById(R.id.btnKeluar);

        btnKontak.setOnClickListener(RedirectToKontakPage);
        btnGps.setOnClickListener(RedirectToGpsPage);
        btnApi.setOnClickListener(RedirectToApiPage);
        btnJsonApi.setOnClickListener(RedirectToApiJsonPage);
        btnKeluar.setOnClickListener(keluar);

    }

    private void setupView(){
    }

    private final View.OnClickListener RedirectToKontakPage = v -> {
        startActivity(new Intent(MainActivity.this, ContactActivity.class));
    };

    private final View.OnClickListener RedirectToGpsPage = v -> {
        startActivity(new Intent(MainActivity.this, GpsActivity.class));
    };

    private final View.OnClickListener RedirectToApiPage = v -> {
        startActivity(new Intent(MainActivity.this, ApiActivity.class));
    };

    private final View.OnClickListener RedirectToApiJsonPage = v -> {
        startActivity(new Intent(MainActivity.this, JsonActivity.class));
    };

    private final View.OnClickListener keluar = v -> {
        moveTaskToBack(true);
    };
}