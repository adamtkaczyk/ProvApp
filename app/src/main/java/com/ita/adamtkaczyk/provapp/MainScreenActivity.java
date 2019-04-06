package com.ita.adamtkaczyk.provapp;

import android.accounts.AccountManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

//import com.ita.adamtkaczyk.provapp.R;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        AccountManager am = AccountManager.get(this);
        Bundle options = new Bundle();
        am.getAuthToken(am.getAccountsByType(AccountGeneral.ACCOUNT_TYPE),
                AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS
                ,
                options,
                this,                           // Your activity
                new OnTokenAcquired(),          // Callback called when a token is successfully acquired
                new Handler(new OnError()));
    }

    private void moveToSummary() {
        Intent myIntent = new Intent(MainScreenActivity.this, SummaryActivity.class);
        MainScreenActivity.this.startActivity(myIntent);
    }

    public void onButtonClick(android.view.View button) {
        moveToSummary();
    }
}
