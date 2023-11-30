package com.example.gameinwakingtoearn.Game.Object.MyGame.Game;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemInBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Dirt;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Dirt1;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList.ItemsList;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.MyStore;

import java.util.ArrayList;


public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameloop;


    private final Context context;

    private ArrayList<Structure> mycity=new ArrayList<Structure>();
    private ArrayList<Structure> myDirt=new ArrayList<Structure>();
    private MyStore myStore;
    private MyBag mybag;


    public static final int AreaTop = 50;
    public static final int AreaLeft = 60;

    public static final int AreaBottom =  Game.getScreenHeight() -256 - AreaTop;
    public static final int AreaRight =  Game.getScreenWidth() - AreaLeft;
//    private ArrayList<ArrayList<Integer>> area_of_land = new ArrayList<>();
//    private ArrayList<ArrayList<Integer>> area_of_dirt = new ArrayList<>();

   // private Map<String, Object> user =new HashMap<>();
    private Paint paint = new Paint();

    private GameObject gameObjectIsClicked = null;


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



        Log.e("check length of rect : ",AreaRight-AreaLeft + "");
        Log.e("check height of rect : ",AreaBottom-AreaTop + "");
        Log.e("check length of phone : ", Game.getScreenWidth()+"");
        Log.e("check height of phone : ", Game.getScreenHeight()+"");

        this.mybag=new MyBag(10,Game.getScreenHeight()-150- MyBag.height,context,mycity,myDirt,myStore);


        this.myStore=new MyStore(this.getScreenWidth()-MyStore.width - 10,Game.getScreenHeight()-150- MyStore.height,context,this.mybag,mycity,myDirt,moneyOfUser);



        myStore.setMoney(FireBaseMangament.getUserMoney());
        Log.e("structure size :", FireBaseMangament.getStructuresList().size() + "");

        Log.e("check bug first", "ok");
        for(Structure s : FireBaseMangament.getStructuresList()){
            s.setMycity(this.mycity);
            s.setContext(this.context);
            s.setMyDirt(myDirt);
            s.setBag(this.mybag);
            s.setMyStore(this.myStore);

            if(s instanceof Dirt) {
                myDirt.add(s);
                Log.e("add dirt","active");
            } else{
                mycity.add(s);
            }

        }



        Log.e("check myDirt size ",myDirt.size()+ "");
        Log.e("check myCity size ",mycity.size()+ "");


        for(ItemInBag s : FireBaseMangament.getBagList()){
            Log.e("check city source",this.mycity + "");
            Log.e("check context source",this.context + "");


            s.setContext(this.context);
            Log.e("check bug after", "ok");
            s.setCity(this.mycity);
            s.setDirt(myDirt);



            mybag.getBagList().addNewItem(s,MyBag.posStartOfItem);
        }






         paint.setColor(Color.rgb(185,122,87));
        setFocusable(true);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event){
        // hàm dùng để xử lý cách sự kiện ấn nút các thứ
        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:

                if(gameObjectIsClicked == null) {

                        myStore.check_is_clicked(event.getX(), event.getY());
                        if(myStore.is_clicked){
                            gameObjectIsClicked = myStore;
                            return true;
                        }


                        mybag.check_is_clicked(event.getX(), event.getY());
                    if(mybag.is_clicked){
                        gameObjectIsClicked = mybag;
                        return true;
                    } else{
                        if(mybag.getStructureSymbol() != null) {
                            gameObjectIsClicked = mybag.getStructureSymbol();
                            return true;
                        }

                    }


                    for (Structure s : mycity) {


                            s.check_is_clicked(event.getX(), event.getY());
                            if(s.is_clicked){
                                gameObjectIsClicked = s;
                                return true;
                            }

                    }

                    for (Structure s : myDirt) {


                        s.check_is_clicked(event.getX(), event.getY());
                        if(s.is_clicked){
                            gameObjectIsClicked = s;
                            return true;
                        }

                    }
                } else{
                    gameObjectIsClicked.check_is_clicked(event.getX(), event.getY());
                    if(!gameObjectIsClicked.is_clicked){
                        gameObjectIsClicked = null;
                    }
                }

                return true;
            case MotionEvent.ACTION_MOVE:
                // xử lý vị trí của đối tượng mỗi khi con chuột di chuyển

                for(Structure s:myDirt){
                    s.setPos(event.getX(),event.getY());
                }

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


        for(Structure s:myDirt){
            s.draw(canvas);
        }

        for(Structure s:mycity){
            s.draw(canvas);
        }
        if(!myStore.get_is_clicked()){
            mybag.draw(canvas);
        }
        if(!mybag.get_is_clicked()) {
            myStore.draw(canvas);
        }

        if(gameObjectIsClicked != null){
            gameObjectIsClicked.draw(canvas);
        }



    }


    public void update() {
        for(Structure s:mycity){
            s.update();
        }

        for(Structure s:myDirt){
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

        ArrayList<Structure> saveStructList = new ArrayList<>();
        for(Structure s : mycity){
            Log.e("check pos strucutre in game before saving ", s.getName() + " " + s.getSaveLeft() +" " + s.getSaveTop() );
            s.setStatus(true);
            saveStructList.add(s);
        }


        for(Structure s : myDirt){
            s.setStatus(true);
            saveStructList.add(s);
        }



        for(int i=0;i<=mybag.getBagList().getQuatities_of_Page();i++){
            for(int j=0;j<mybag.getBagList().getMenuItem()[i].getQua_of_item();j++){
               ItemInBag itemInBag = (ItemInBag) mybag.getBagList().getMenuItem()[i].getItemList()[j];

               saveStructList.add(itemInBag.createStructure(itemInBag.getPosX(),itemInBag.getPosY(),itemInBag.getCity(),
                       itemInBag.getDirt(),this.myStore,this.mybag));
            }
        }

        Log.d("check saveStructList In Game", saveStructList.size() + " ");

        FireBaseMangament.saveUserMoney(this.myStore.getMoney());
        FireBaseMangament.saveUserBuildings(saveStructList);
        FireBaseMangament.updateUserData();

    }


}