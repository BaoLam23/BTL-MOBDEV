package com.example.gameinwakingtoearn;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.gameinwakingtoearn.Object.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Object.CityStructures.House;
import com.example.gameinwakingtoearn.Object.CityStructures.Structure;
import com.example.gameinwakingtoearn.Object.MyDesignList.AItemInList;
import com.example.gameinwakingtoearn.Object.MyDesignList.ItemsList;
import com.example.gameinwakingtoearn.Object.MyDesignList.MyListManagement;
import com.example.gameinwakingtoearn.Object.StoreManagement.MyStore;

import java.util.ArrayList;


public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameloop;

    private final Context context;

    private ArrayList<Structure> mycity=new ArrayList<Structure>();
    private MyStore myStore;
    private MyBag mybag;
    private int area_of_land[][];


    //toàn bộ hàm khời tạo game để thiết lập j j đó :vv
    public Game(Context context) {
        super(context);
        this.context=context;
        // khởi tạo hàm surfaceholder và thêm hàm callback
        SurfaceHolder surfaceholder=getHolder();
        surfaceholder.addCallback(this);

        //khởi tạo class gameloop
        gameloop=new GameLoop(this,surfaceholder);

        // đây chỉ là bộ nhớ test sau này cần lưu trong bộ nhớ máy
        this.area_of_land=new int[5000][5000];

        this.mybag=new MyBag(50,this.getScreenHeight()-500,context,mycity,area_of_land);
        this.myStore=new MyStore(this.getScreenWidth()-300,this.getScreenHeight()-500,context,this.mybag,mycity,area_of_land);


        setFocusable(true);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        // hàm dùng để xử lý cách sự kiện ấn nút các thứ
        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:

                myStore.check_is_clicked(event.getX(), event.getY());
                mybag.check_is_clicked(event.getX(), event.getY());
               for(Structure s:mycity){
                   if(!myStore.get_is_clicked()  ) {
                       s.check_is_clicked(event.getX(), event.getY());
                   }
               }

                return true;
            case MotionEvent.ACTION_MOVE:
                // xử lý vị trí của đối tượng mỗi khi con chuột di chuyển
                for(Structure s:mycity){
                    s.setPos(event.getX(),event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:

                return true;

        }


        return super.onTouchEvent(event);
    }



    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameloop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
    //hàm vẽ chính của game
    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);

        for(Structure s:mycity){
            s.draw(canvas);
        }
        if(!myStore.get_is_clicked()){
            mybag.draw(canvas);
        }
        if(!mybag.get_is_clicked()) {
            myStore.draw(canvas);
        }



    }


    public void update() {
        for(Structure s:mycity){
            s.update();
        }

    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


}