package com.ita.provapp.app;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ita.provapp.app.R;
import com.ita.provapp.authenticator.AccountGeneral;

public class StartAppActivity extends AppCompatActivity {

    private AccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);

        accountManager = AccountManager.get(this);
        Bundle options = new Bundle();


        final AccountManagerFuture<Bundle> future = accountManager.getAuthTokenByFeatures(AccountGeneral.ACCOUNT_TYPE, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS, null, this, null, null,
                new AccountManagerCallback<Bundle>() {
                    @Override
                    public void run(AccountManagerFuture<Bundle> future) {
                        Bundle bnd = null;
                        try {
                            bnd = future.getResult();
                            final String token = bnd.getString(AccountManager.KEY_AUTHTOKEN);
                            Intent myIntent = new Intent(StartAppActivity.this, MainScreenActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("token", token);
                            myIntent.putExtras(bundle);
                            StartAppActivity.this.startActivity(myIntent);
                        } catch (Exception e) {

                        }
                    }
                }, null);
    }
}
