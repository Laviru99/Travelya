package com.finalproject.travelya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MountainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mountain);
    }

    public void backhome(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void moun1(View view) {
        startActivity(new Intent(this, Moun1Activity.class));
    }
    public void moun2(View view) {
        startActivity(new Intent(this, Moun2Activity.class));
    }
    public void moun3(View view) {
        startActivity(new Intent(this, Moun3Activity.class));
    }
    public void moun4(View view) {
        startActivity(new Intent(this, Moun4Activity.class));
    }
    public void moun5(View view) {
        startActivity(new Intent(this, Moun5Activity.class));
    }
}