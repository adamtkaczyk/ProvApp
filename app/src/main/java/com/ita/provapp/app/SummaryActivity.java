package com.ita.provapp.app;

import com.ita.provapp.app.R;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        final Button send = (Button) this.findViewById(R.id.button3);
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                try {
                    new SendMailTask().execute();
                } catch (Exception e) {
                    Toast.makeText(SummaryActivity.this, "SendMail: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
