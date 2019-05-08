package com.ita.provapp.app;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.ita.provapp.authenticator.AccountGeneral;
import com.ita.provapp.common.AccountAPIClient;
import com.ita.provapp.common.LoginUser;
import com.ita.provapp.common.User;

import static com.ita.provapp.authenticator.AccountGeneral.sServerAuthenticate;

public class StartAppActivity extends AppCompatActivity {

    private AccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_start_app);
        //final ProvApplication app = (ProvApplication)getApplicationContext();

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
                            final String username = bnd.getString(AccountManager.KEY_ACCOUNT_NAME);

                            GetUserTask task = new GetUserTask(username,token,"Full Access");
                            task.execute();

                            Intent myIntent = new Intent(StartAppActivity.this, MainScreenActivity.class);
                            StartAppActivity.this.startActivity(myIntent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, null);
    }

    public class GetUserTask extends AsyncTask<Void, Void, Intent> {

        private final String username;
        private final String token;
        private final String accountType;

        GetUserTask(String username, String token, String type) {
            this.username = username;
            this.token = token;
            this.accountType = type;
        }

        @Override
        protected Intent doInBackground(Void... params) {
            //Bundle data = new Bundle();

            AccountAPIClient accountAPIClient = new AccountAPIClient();
            User user = accountAPIClient.getUser(token,username);
            ProvApplication app = (ProvApplication)getApplicationContext();
            app.setLoginUser(new LoginUser(user,token));

            final Intent res = new Intent();
            //res.putExtras(data);
            return res;
        }

        @Override
        protected void onPostExecute(Intent intent) {

        }
    }
}
