package com.finalproject.travelya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    private EditText searchBar;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchBar = findViewById(R.id.searchbar);
        linearLayout = findViewById(R.id.linearLayout);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterLocations(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterLocations(String query) {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View locationView = linearLayout.getChildAt(i);
            TextView locationText = getLocationTextView(locationView);
            if (locationText != null) {
                String locationName = locationText.getText().toString().toLowerCase();
                if (locationName.contains(query.toLowerCase())) {
                    locationView.setVisibility(View.VISIBLE);
                } else {
                    locationView.setVisibility(View.GONE);
                }
            }
        }
    }

    private TextView getLocationTextView(View locationView) {
        int[] textIds = {
                R.id.mount1, R.id.mount2, R.id.mount3, R.id.mount4, R.id.mount5,
                R.id.water1, R.id.water2, R.id.water3, R.id.water4, R.id.water5, R.id.water6,
                R.id.beach1, R.id.beach2, R.id.beach3, R.id.beach4, R.id.beach5
        };
        for (int id : textIds) {
            TextView textView = locationView.findViewById(id);
            if (textView != null) {
                return textView;
            }
        }
        return null;
    }

    public void goHome(View view) {
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
