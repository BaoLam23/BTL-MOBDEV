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



    private Game game;
    private MediaPlayer backgroundMusicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.game = new Game(this,-1);
        setContentView(game);

        backgroundMusicPlayer = MediaPlayer.create(this, R.raw.music);
        backgroundMusicPlayer.setLooping(true);
        backgroundMusicPlayer.setVolume(3, 3);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish(); // Kết thúc Activity B và quay lại Activity A
    }


}