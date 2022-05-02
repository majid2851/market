package com.app.markeet.fcm;

import com.app.markeet.data.SharedPref;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

public class FcmInstanceIDService extends FirebaseMessagingService {

    private SharedPref sharedPref;

    public void onTokenRefresh()
    {
        sharedPref = new SharedPref(this);
         FirebaseMessaging.getInstance().getToken().addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                sendRegistrationToServer(s);
            }
        }) ;

    }

    private void sendRegistrationToServer(String token) {
        sharedPref.setFcmRegId(token);
        sharedPref.setOpenAppCounter(SharedPref.MAX_OPEN_COUNTER);
    }
}
