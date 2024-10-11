package com.finalproject.travelya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SuggestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);

    }

    public void backdetectionpage(View view) {
        startActivity(new Intent(this,CameraGalleryActivity.class));
    }

    public void suggest1(View view) {
        startActivity(new Intent(this,Suggest1Activity.class));
    }

    public void suggest2(View view) {
        startActivity(new Intent(this,Suggest2Activity.class));
    }

    public void suggest3(View view) {
        startActivity(new Intent(this,Suggest3Activity.class));
    }

    public void suggest4(View view) {
        startActivity(new Intent(this,Suggest4Activity.class));
    }

    public void suggest5(View view) {
        startActivity(new Intent(this,Suggest5Activity.class));
    }
}