package com.ita.provapp.app;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.view.KeyEvent;

import com.ita.provapp.authenticator.AccountGeneral;
import com.ita.provapp.app.R;

public class MainScreenActivity extends AppCompatActivity {

    private AccountManager accountManager;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        accountManager = AccountManager.get(this);

        ProvApplication app = (ProvApplication)getApplicationContext();

        navigationView = (NavigationView) findViewById(R.id.nv);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.nav_header_username);
        TextView navEmail = (TextView) headerView.findViewById(R.id.nav_header_email);
        navUsername.setText(app.getLoginUser().getUser().getUsername());
        navEmail.setText(app.getLoginUser().getUser().getEmail());

        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.account:
                        Toast.makeText(MainScreenActivity.this, "My Account",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.settings:
                        Toast.makeText(MainScreenActivity.this, "Settings",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:
                        logOut();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }

    private void moveToSummary() {
        Intent myIntent = new Intent(MainScreenActivity.this, SummaryActivity.class);
        MainScreenActivity.this.startActivity(myIntent);
    }

    public void onButtonClick(android.view.View button) {
        moveToSummary();
    }

    private void showMessage(final String msg) {
        if (TextUtils.isEmpty(msg))
            return;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void logOut() {
        final Account availableAccounts[] = accountManager.getAccountsByType(AccountGeneral.ACCOUNT_TYPE);
        final Account account = availableAccounts[0];

        final Handler handler = new Handler ();

        AccountManagerCallback<Boolean> callback = new AccountManagerCallback<Boolean>()
        {
            @Override
            public void run(AccountManagerFuture<Boolean> arg0)
            {
                Intent myIntent = new Intent(MainScreenActivity.this, StartAppActivity.class);
                MainScreenActivity.this.startActivity(myIntent);
            }
        };

        AccountManagerFuture<Boolean> bool = accountManager.removeAccount(account, callback, handler);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
