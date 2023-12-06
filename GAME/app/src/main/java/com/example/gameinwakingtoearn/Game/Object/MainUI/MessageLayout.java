package com.example.gameinwakingtoearn.Game.Object.MainUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.FireBaseMangament;
import com.example.gameinwakingtoearn.Game.Object.User.CurrentUser;
import com.example.gameinwakingtoearn.Game.Object.User.User;
import com.example.gameinwakingtoearn.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.MessagingKt;
import com.google.firebase.messaging.RemoteMessage;


public class MessageLayout extends AppCompatActivity {

    public static final String projectNumber = "461261865463";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageButton backButton;
    private ImageButton sendButton;
    private EditText textInput;
    public static final String keyMessage = "FCM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_layout);

         tabLayout = findViewById(R.id.tabLayout);
         viewPager = findViewById(R.id.viewPaper);
         backButton = findViewById(R.id.backButtonInMessage);
         sendButton = findViewById(R.id.sendMessageButton);
         textInput = findViewById(R.id.messageInput);

        MyPaperAdapter adapter = new MyPaperAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        FirebaseMessaging.getInstance().subscribeToTopic("global")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (!task.isSuccessful()) {
                            Log.d("subscribe into global topic","successful");
                        } else{
                            Exception exception = task.getException();
                            if(exception!= null){
                                Log.e("a exception found in Subcribe into topi global", exception.getMessage() + " caused by : ", exception.getCause());
                            }
                            Log.d("subscribe into global topic","fail" );
                        }

                    }
                });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageLayout.this,Authentication.class);
                startActivity(intent);
                finish();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textInput.getText().toString().isEmpty()){
                    Log.e("check user phone token : ", FireBaseMangament.getPhoneToken()  + "");
                    if(FireBaseMangament.getPhoneToken() != null){
                        User user = CurrentUser.getInstance().getUser();
                        RemoteMessage message = new RemoteMessage.Builder(projectNumber + "@fcm.googleapis.com")
                                .addData(keyMessage,textInput.getText().toString()).build();
                        Log.e("check message : ",message.getData().toString());


                        FirebaseMessaging.getInstance().send(message);
                        Log.e("send message successfull","ok");
                    }else{
                        FireBaseMangament.setPhoneToken();
                    }
                }
            }
        });


    }
}