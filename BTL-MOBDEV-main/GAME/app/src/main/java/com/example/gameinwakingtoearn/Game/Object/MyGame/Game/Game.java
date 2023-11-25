package com.example.gameinwakingtoearn.Game.Object.MyGame.Game;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.MyStore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameloop;


    private final Context context;

    private ArrayList<Structure> mycity=new ArrayList<Structure>();
    private MyStore myStore;
    private MyBag mybag;


    public static final int AreaTop = 50;
    public static final int AreaLeft = 50;

    public static final int AreaBottom =  Game.getScreenHeight() -200 - AreaTop;
    public static final int AreaRight =  Game.getScreenWidth() - AreaLeft;
    private ArrayList<ArrayList<Integer>> area_of_land = new ArrayList<>();
   // private Map<String, Object> user =new HashMap<>();
    private Paint paint = new Paint();


    //toàn bộ hàm khời tạo game để thiết lập j j đó :vv
    public Game(Context context,long moneyOfUser) {
        super(context);
        Log.e("construct game", "ok");
        this.context=context;

        // khởi tạo hàm surfaceholder và thêm hàm callback
        SurfaceHolder surfaceholder=getHolder();
        surfaceholder.addCallback(this);

        //khởi tạo class gameloop
        gameloop=new GameLoop(this,surfaceholder);

        // đây chỉ là bộ nhớ test sau này cần lưu trong bộ nhớ máy
        for(int i=0;i<Game.getScreenHeight();i++){
            area_of_land.add(new ArrayList<Integer>());
            for(int j=0;j<getScreenWidth();j++){
                area_of_land.get(i).add(0);
            }
        }

        this.mybag=new MyBag(10,Game.getScreenHeight()-100- MyBag.height,context,mycity,area_of_land);


        this.myStore=new MyStore(this.getScreenWidth()-MyStore.width - 10,Game.getScreenHeight()-100- MyStore.height,context,this.mybag,mycity,area_of_land,moneyOfUser);

        //Log.e("current Money", FireBaseMangament.getUserMoney() + " >>>");
        myStore.setMoney(FireBaseMangament.getUserMoney());

         paint.setColor(Color.GREEN);
        setFocusable(true);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        // hàm dùng để xử lý cách sự kiện ấn nút các thứ
        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:

                if(!mybag.get_is_clicked()) {
                    myStore.check_is_clicked(event.getX(), event.getY());
                }

                if(!myStore.get_is_clicked()) {
                    mybag.check_is_clicked(event.getX(), event.getY());
                }




               for(Structure s:mycity){

                   if(!myStore.get_is_clicked() && !mybag.get_is_clicked() ) {
                       s.check_is_clicked(event.getX(), event.getY());
                   }

               }

                return true;
            case MotionEvent.ACTION_MOVE:
                // xử lý vị trí của đối tượng mỗi khi con chuột di chuyển
                for(Structure s:mycity){
                    s.setPos(event.getX(),event.getY());
                }
                mybag.setPosOfStucture(event.getX(),event.getY());
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
         Log.e("game end :","true");
         saveData();
    }
    //hàm vẽ chính của game
    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawRect(new Rect(AreaLeft,AreaTop,AreaRight,AreaBottom), paint);

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
        this.mybag.update();
        this.myStore.update();



    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


    public long getMoney(){
        return this.myStore.getMoney();
    }

    private void saveData(){
       FireBaseMangament.saveUserMoney(this.myStore.getMoney());
    }


}