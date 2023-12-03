package com.example.gameinwakingtoearn.Game.Object.Running;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.util.ArrayList;

public class MusicService extends Service {

    // cần sửa được lỗi setDataSource failed.: status=0x80000000

    public static ArrayList<String> musicPathList = new ArrayList<>();

    private ArrayList<MediaPlayer> mediaLists = new ArrayList<>();


    private int index = 0;



   public static void setMusicPathList(ArrayList<String> musicPathList){

       if(MusicService.musicPathList == null){
           MusicService.musicPathList = new ArrayList<>();
       }

       try {
           MusicService.musicPathList.clear();

           for (String s : musicPathList) {
               MusicService.musicPathList.add(s);
           }
       }catch (Exception e){
           Log.e("pull music exception at MusicService : ",e.getMessage() + " caused by " + e.getCause());
       }

   }

   public void setIndex(int i){
       this.index = i;
   }
   public int getIndex(){
       return this.index;
   }



    private MediaPlayer createSong(String musicPath){
        MediaPlayer mediaPlayer = new MediaPlayer();

        try {

            Uri musicUri = Uri.parse(musicPath);
            Log.e("check music path : ", musicUri.getPath() + "");
            if(mediaPlayer != null){
                Log.e("check media player","not null");
            }
            mediaPlayer.setDataSource(getApplicationContext(), musicUri);


            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    setIndex(getIndex() + 1);
                    if (getIndex() > mediaLists.size()) {
                        setIndex(mediaLists.size() + 1);
                    }
                    playMusic(index);

                }
            });

        } catch (IOException e) {
            Log.e("error at create song at MusicService",e.getMessage() + "\n" + e.getCause());
            e.printStackTrace();
        } catch (Exception e){
            Log.e("error at create song at MusicService",e.getMessage() + "\n" + e.getCause());
        }

       return mediaPlayer;
    }

    private void playMusic(int index){
       Log.e("play music :","active");
        if(mediaLists.size() != 0 && index < mediaLists.size()){

            try {
                mediaLists.get(index).prepare();
                mediaLists.get(index).start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onCreate() {
        super.onCreate();



        if(mediaLists == null){
            mediaLists = new ArrayList<>();
        }
        for(int i = 0 ;i<musicPathList.size();i++ ){
            mediaLists.add(createSong(musicPathList.get(i)));

        }



    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("music service : ","active");

        playMusic(index);

        return START_STICKY;
    }

    public void releaseMusicMemory(){
        for(MediaPlayer mediaPlayer : mediaLists){
            mediaPlayer.stop();
            mediaPlayer.release();

        }
        mediaLists = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("stop MusicService ","active");
       releaseMusicMemory();

        musicPathList = null;


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
