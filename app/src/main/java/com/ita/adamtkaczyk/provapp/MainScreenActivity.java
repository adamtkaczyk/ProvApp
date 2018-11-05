package com.ita.adamtkaczyk.provapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

//import com.ita.adamtkaczyk.provapp.R;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }

    private void moveToSummary() {
        Intent myIntent = new Intent(MainScreenActivity.this, SummaryActivity.class);
        myIntent.putExtra("key", "dupa"); //Optional parameters
        MainScreenActivity.this.startActivity(myIntent);
    }

    public void onButtonClick(android.view.View button) {
        moveToSummary();
    }
}
