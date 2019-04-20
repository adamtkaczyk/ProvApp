package com.ita.provapp.authenticator;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ProvAppAuthenticatorService extends Service {
    @Override
    public IBinder onBind(Intent intent) {

        ProvAppAuthenticator authenticator = new ProvAppAuthenticator(this);
        return authenticator.getIBinder();
    }
}
