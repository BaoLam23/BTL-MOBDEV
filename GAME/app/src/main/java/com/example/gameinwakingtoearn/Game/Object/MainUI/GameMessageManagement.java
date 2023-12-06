package com.example.gameinwakingtoearn.Game.Object.MainUI;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class GameMessageManagement extends FirebaseMessagingService {

    //id của thiết bị đang cài đặt ứng dụng
    private String messageUSer = "";

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        if (message.getData().size() > 0) {

            this.messageUSer =message.getData().get(MessageLayout.keyMessage);
            Log.e("Message",messageUSer);

        }

    }

    public String getUserMessage(){
        return this.messageUSer;
    }
}
