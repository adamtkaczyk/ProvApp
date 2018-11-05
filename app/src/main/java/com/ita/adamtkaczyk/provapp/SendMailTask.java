package com.ita.adamtkaczyk.provapp;

import android.os.AsyncTask;

public class SendMailTask extends AsyncTask<String, Void, MailSender> {
    private Exception exception;

    @Override
    protected MailSender doInBackground(String... strings) {
        try {
            MailSender sender = new MailSender("test_mailapp@wp.pl", "test_mailapp");
            sender.sendMail("This is Subject",
                    "This is Body",
                    "test_mailapp@wp.pl",
                    "adamtkaczyk90@gmail.com");
            return sender;
        } catch (Exception e) {
            this.exception = e;

            return null;
        }
    }
}
