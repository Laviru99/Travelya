package com.finalproject.travelya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WaterfallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterfall);
    }

    public void waterfall1(View view) {
        startActivity(new Intent(this, Water1Activity.class));
    }
    public void waterfall2(View view) {
        startActivity(new Intent(this, Water2Activity.class));
    }
    public void waterfall3(View view) {
        startActivity(new Intent(this, Water3Activity.class));
    }
    public void waterfall4(View view) {
        startActivity(new Intent(this, Water4Activity.class));
    }
    public void waterfall5(View view) {
        startActivity(new Intent(this, Water5Activity.class));
    }
    public void waterfall6(View view) {
        startActivity(new Intent(this, Water6Activity.class));
    }

    public void backhome(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }
}