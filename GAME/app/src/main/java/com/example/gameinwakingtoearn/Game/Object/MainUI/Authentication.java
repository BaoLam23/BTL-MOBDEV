package com.example.gameinwakingtoearn.Game.Object.MainUI;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.GameUI;
import com.example.gameinwakingtoearn.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Authentication extends AppCompatActivity {

    TextView textView;
    Button button;
    Button playgame;
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
                            textView.setText(document.getString("username"));
                            Log.e("money :",document.get("money") + "");
                            // ... set other attributes to the text views
                        } else {
                            Log.d("GameUI", "No such document");
                        }
                    } else {
                        Log.d("GameUI", "get failed with ", task.getException());
                    }
                }
            });
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        playgame =(Button) findViewById(R.id.playGameButton);
        playgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Authentication.this, GameUI.class);
                startActivity(i);
            }
        });
    }
}
