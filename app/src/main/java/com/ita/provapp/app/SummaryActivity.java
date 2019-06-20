package com.ita.provapp.app;

import com.ita.provapp.app.R;
import com.ita.provapp.common.OrderAPIClient;
import com.ita.provapp.common.exceptions.ServerException;
import com.ita.provapp.common.json.Order;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class SummaryActivity extends AppCompatActivity {

    private Integer orderID = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        final Button send = (Button) this.findViewById(R.id.button3);
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                try {
                    ProvApplication app = (ProvApplication) getApplicationContext();
                    Order order = new Order();
                    CreateOrderTask task = new CreateOrderTask(app.getLoginUser().getAuthtoken(), new Order(new Date()), "Full Access");
                    task.execute().get();
                } catch (ExecutionException | InterruptedException e) {
                    //TODO: handle exception
                }
            }
        });
    }

    public class CreateOrderTask extends AsyncTask<Void, Void, Integer> {

        private final Order order;
        private final String token;
        private final String accountType;

        CreateOrderTask(String token, Order order, String type) {
            this.order = order;
            this.token = token;
            this.accountType = type;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            Integer orderid = null;
            final Intent res = new Intent();
            try {
                OrderAPIClient client = new OrderAPIClient();
                orderid = client.createOrder(token, order);
            } catch (IOException | ServerException e) {
                //TODO: handle exception
            }

            return orderid;
        }

        @Override
        protected void onPostExecute(Integer orderid) {
            if(orderid != null) {
                Toast.makeText(getBaseContext(), "Send order successfully", Toast.LENGTH_SHORT).show();
            }
            orderID = orderid;
        }
    }
}
