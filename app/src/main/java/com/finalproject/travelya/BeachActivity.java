package com.finalproject.travelya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BeachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_beach);
    }

    public void backhome(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void beach1(View view) {
        startActivity(new Intent(this, Beach1Activity.class));
    }
    public void beach2(View view) {
        startActivity(new Intent(this, Beach2Activity.class));
    }
    public void beach3(View view) {
        startActivity(new Intent(this, Beach3Activity.class));
    }
    public void beach4(View view) {
        startActivity(new Intent(this, Beach4Activity.class));
    }
    public void beach5(View view) {
        startActivity(new Intent(this, Beach5Activity.class));
    }
}