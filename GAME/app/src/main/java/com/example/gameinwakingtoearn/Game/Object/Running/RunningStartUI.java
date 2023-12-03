package com.example.gameinwakingtoearn.Game.Object.Running;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.example.gameinwakingtoearn.Game.Object.MainUI.Authentication;

import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class RunningStartUI extends AppCompatActivity {

    private ImageButton backButton;
    private ImageButton runButton;


    public static final int request_Code = 100;

    public static final String MUSIC_NAME_KEY = "music name ";
    public static final String MUSIC_URI_KEY = "music uri ";

    public static final String MUSIC_SIZE_KEY = "music size";

    public static final int MY_REQUEST_WRITE_PERMISSION_CODE = 2;

    private Button chooseMusicButton;
    private ToggleButton deleteButton;


    private ArrayList<String> musicList = new ArrayList<>();
    private ArrayList<String> musicNameList = new ArrayList<>();
    private ListView musicListView;

    private boolean isDeleteMusic = false;
    private ArrayAdapter<String> adapter;

    private   SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_ui);



        //yêu cầu cấp quyền truy cập viết
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Yêu cầu quyền truy cập bộ nhớ khi chưa được cấp
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_REQUEST_WRITE_PERMISSION_CODE);
        }


        // lấy dữ liệu từ bộ nhớ truyền vô app
        sharedPreferences = getSharedPreferences("music sources in irf app", Context.MODE_PRIVATE);
        for(int i=0;i<sharedPreferences.getInt(MUSIC_SIZE_KEY,0);i++){
            musicList.add(sharedPreferences.getString(MUSIC_URI_KEY+i,"empty"));
            musicNameList.add(sharedPreferences.getString(MUSIC_NAME_KEY+i,"empty"));
        }


         backButton = (ImageButton) findViewById(R.id.back_button);
         runButton = (ImageButton)findViewById(R.id.go_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RunningStartUI.this, Authentication.class);
                startActivity(intent);
                finish();
            }
        });

        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RunningStartUI.this, RunningResumeUI.class);
                Log.e("check music list before pull into next activity", musicList.size()+" ");

                intent.putExtra("music list", musicList);
                startActivity(intent);
                finish();
            }
        });



        chooseMusicButton = (Button)findViewById(R.id.chooseMusicFromFile);
        chooseMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                // chỉ chọn tệp nhạc
                intent.setType("audio/*");
                startActivityForResult(intent,request_Code);
            }
        });

        deleteButton = (ToggleButton) findViewById(R.id.deleteMusicButton);
        deleteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("change is delete music :",isDeleteMusic+"");
                if(isChecked){
                    isDeleteMusic = true;
                } else{
                    isDeleteMusic  = false;
                }
            }
        });

        musicListView = (ListView)findViewById(R.id.musicList);
        adapter = new ArrayAdapter<>(this,R.layout.item_in_list_view_style,R.id.text_view_item,musicNameList);
        musicListView.setAdapter(adapter);

        musicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(isDeleteMusic){
                    musicList.remove(i);
                    musicNameList.remove(i);
                    adapter.notifyDataSetChanged();
                }
            }
        });


    }

    private String getNameOfMusic(String musicPath){
           String[] save = musicPath.split("/");
           return save[save.length-1];
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == request_Code && resultCode == RESULT_OK && data != null) {
            Uri selectedAudioUri = data.getData();
            if(selectedAudioUri != null) {

                musicList.add(selectedAudioUri.toString());
                musicNameList.add(getNameOfMusic(selectedAudioUri.getPath()));

                //thông báo có sự thay đổi của dữ liệu
                adapter.notifyDataSetChanged();
            }

        }
    }

    public void onDestroy(){
        super.onDestroy();
        Log.e("destroy app","ok");
        //lưu trữ bộ nhớ trong shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for(int i=0;i<musicList.size();i++) {

            editor.putString(MUSIC_NAME_KEY+ i,  musicNameList.get(i));
            editor.putString(MUSIC_URI_KEY+ i, musicList.get(i) );
        }
        editor.putInt(MUSIC_SIZE_KEY,musicList.size());

        editor.apply();
    }
}