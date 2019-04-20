package com.ita.provapp.app;

import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

public class SimpleMailSender {
    SimpleMailSender(AppCompatActivity activity) {
        this.activity = activity;
    }

    private AppCompatActivity activity;

    public void send() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        //intent.putExtra(Intent.EXTRA_EMAIL,new String[] {"adamtkaczyk90@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT,"Order Send");
        intent.putExtra(Intent.EXTRA_TEXT,"Order body");
        intent.setData(Uri.parse("mailto:adamtkaczyk90@gmail.com"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            activity.startActivity(Intent.createChooser(intent,"Send email ,,,"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(activity, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
