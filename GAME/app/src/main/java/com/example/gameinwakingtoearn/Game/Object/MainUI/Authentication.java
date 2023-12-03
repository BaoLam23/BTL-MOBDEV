package com.example.gameinwakingtoearn.Game.Object.MainUI;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.FireBaseMangament;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.GameUI;

import com.example.gameinwakingtoearn.Game.Object.Running.RunningResumeUI;
import com.example.gameinwakingtoearn.Game.Object.Running.RunningStartUI;
import com.example.gameinwakingtoearn.Game.Object.User.CurrentUser;
import com.example.gameinwakingtoearn.Game.Object.User.User;

import com.example.gameinwakingtoearn.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Authentication extends AppCompatActivity {

    // ở đây cần thêm một activity với thanh progressBar để tải dữ liệu, nếu xong thì mới vào activity chính



    TextView textView, level;
    ProgressBar progressBar;

    ImageButton showMapBut;

    private ImageButton settingsButton;

    ImageButton playgame;
    FirebaseAuth auth;
    FirebaseUser user;
    ImageButton friendBtn;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);


        auth = FirebaseAuth.getInstance();

        textView = findViewById(com.example.gameinwakingtoearn.R.id.user_details);
        user = auth.getCurrentUser();
        showMapBut = (ImageButton) findViewById(com.example.gameinwakingtoearn.R.id.showMapBut);

        settingsButton = (ImageButton) findViewById(com.example.gameinwakingtoearn.R.id.settingButtonAuthentication);
        level = (TextView) findViewById(com.example.gameinwakingtoearn.R.id.level);
        progressBar = (ProgressBar) findViewById(R.id.xpProgressBar);
        friendBtn = (ImageButton)findViewById(R.id.findFriendButton);




        Log.e("push data from fire base", "active");



        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {


            String userId = user.getUid();
            Log.e("userid : ",userId);
            db.collection("users").document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null && document.exists()) {
                            // Set the user info to your text views
//                            textView.setText(document.getId());

                            User user = CurrentUser.getInstance().getUser();
                            Log.e("check user : ", user + " ");
                           //textView.setText(document.getString("username"));

                            textView.setText(user.getUsername());



                            level.setText(String.valueOf(user.getLevel()));

                            Log.e("insert data","successful");

                            Log.e("check user exp before insert",user.getCurrentExp()+"");
                            progressBar.setProgress(user.getCurrentExp());


                            FireBaseMangament.saveUserMoney((long)user.getMoney());




                        } else {
                            Log.d("GameUI", "No such document");
                        }
                    } else {
                        Log.d("GameUI", "get failed with ", task.getException());
                    }
                }
            });

            FireBaseMangament.setCurrentUser(user);

            FireBaseMangament.getUserDataFromFireBase();


        }

        friendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Authentication.this, Friends.class);
                startActivity(i);
                finish();
            }
        });



        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Authentication.this, Settings.class);
                startActivity(intent);
                finish();
            }
        });


        showMapBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), RunningStartUI.class);
                startActivity(intent);
                finish();
                
            }
        });

        playgame =(ImageButton)  findViewById(R.id.playGameButton);
        playgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Authentication.this, GameUI.class);
                User user = CurrentUser.getInstance().getUser();

                FireBaseMangament.saveUserMoney((long)user.getMoney());
                startActivity(i);
            }
        });


    }


}
