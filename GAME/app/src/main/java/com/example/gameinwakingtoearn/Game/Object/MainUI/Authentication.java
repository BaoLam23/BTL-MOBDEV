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

import com.example.gameinwakingtoearn.Game.Object.Running.GPS;

import com.example.gameinwakingtoearn.Game.Object.Running.MapsActivity;
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



    TextView textView, money, level;
    ProgressBar progressBar;
    ImageButton button;
    ImageButton showMapBut;
    ImageButton playgame;
    FirebaseAuth auth;
    FirebaseUser user;



    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        textView = findViewById(com.example.gameinwakingtoearn.R.id.user_details);
        user = auth.getCurrentUser();
        showMapBut = findViewById(R.id.showMapBut);

        money = findViewById(R.id.user_money);
        level = findViewById(R.id.level);
        progressBar = findViewById(R.id.progressBar);



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
                           //textView.setText(document.getString("username"));

                            textView.setText(user.getEmail() + " " + user.getUsername());
                            money.setText(user.getMoney() + "$");
                            level.setText("Level: " + user.getLevel());
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

        // if click logout btn
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                CurrentUser.getInstance().setUser(null);

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
        showMapBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
                
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
