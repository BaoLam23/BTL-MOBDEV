package com.example.gameinwakingtoearn.Game.Object.MyGame.Game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import com.example.gameinwakingtoearn.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class GameUI extends AppCompatActivity {


    private FirebaseAuth auth;
    private FirebaseUser user;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Game game;
    private MediaPlayer backgroundMusicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if (user == null) {
            Log.e("cant find user :","error! ");
        } else{
            Log.e("load data :","success");
            this.GetDataOfUserFromDataBase();

        }
        this.game = new Game(this,-1);

        setContentView(game);

        backgroundMusicPlayer = MediaPlayer.create(this, R.raw.music);
        backgroundMusicPlayer.setLooping(true);
        backgroundMusicPlayer.setVolume(10, 10);
        backgroundMusicPlayer.start();


    }
    @Override
    protected void onPause() {
        super.onPause();
        if (backgroundMusicPlayer.isPlaying()) {
            backgroundMusicPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (backgroundMusicPlayer != null && !backgroundMusicPlayer.isPlaying()) {
            backgroundMusicPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
            backgroundMusicPlayer.release();
            backgroundMusicPlayer = null;
        }
    }
    private void GetDataOfUserFromDataBase(){

        String userId = user.getUid();
        Log.e("userid : ",userId);
        this.db.collection("users").document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {

                        // get money
                        game.setUserMoney(document.getLong("money"));


                    } else {
                        Log.d("GameUI", "No such document");
                    }
                } else {
                    Log.d("GameUI", "get failed with ", task.getException());
                }
            }
        });

    }
}