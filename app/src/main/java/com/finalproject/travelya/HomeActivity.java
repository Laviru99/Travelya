package com.finalproject.travelya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void exit(View view) {

        startActivity(new Intent(this,LoginActivity.class));
    }

    public void goDetection(View view) {
        startActivity(new Intent(this,CameraGalleryActivity.class));
    }

    public void gowaterfall(View view) {
        startActivity(new Intent(this, WaterfallActivity.class));
    }

    public void gomountain(View view) {
        startActivity(new Intent(this, MountainActivity.class));
    }

    public void gobeach(View view) {
        startActivity(new Intent(this, BeachActivity.class));
    }

    public void goabout(View view) {
        startActivity(new Intent(this, AboutteamActivity.class));
    }

    public void gosearch(View view) {
        startActivity(new Intent(this, SearchActivity.class));
    }

    public void gotraveltips(View view) {
        startActivity(new Intent(this, TravelTipsActivity.class));
    }

    public void gotravelmap(View view) {
        startActivity(new Intent(this, TravelMapActivity.class));
    }
}