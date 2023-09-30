package com.example.testmap.MyGame;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.testmap.MyGame.Object.House;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameloop;

    private final Context context;
    private House house[];
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
        house=new House[2];
        house[0]=new House(500,500,context);
        house[1]=new House(300,700,context);

        // đây chỉ là bộ nhớ test sau này cần lưu trong bộ nhớ máy
        this.area_of_land=new int[3000][3000];
        setFocusable(true);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        // hàm dùng để xử lý cách sự kiện ấn nút các thứ
        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:
                house[0].check_is_clicked(event.getX(),event.getY());

                return true;
            case MotionEvent.ACTION_MOVE:
                // xử lý vị trí của đối tượng mỗi khi con chuột di chuyển

                    house[0].setPos(event.getX(),event.getY());


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

        for(int i=0;i<2;i++){
            house[i].draw(canvas);
        }


    }


    public void update() {
        for(int i=0;i<2;i++){
            house[i].update();
        }

    }

    //cập nhập các ví trí của kiến trúc vô mảng lưu và sau đó thay đổi vị trí của các kiến trúc
    public void save_invade_area_and_update_pos_of_construction(House house){

            // nếu diện tích đã bị chiếm
            for (int i = house.getPos().top; i <= house.getPos().bottom; i++) {
                for (int j = house.getPos().left; j <= house.getPos().right; j++) {
                    if (area_of_land[i][j] == 1) {
                        house.setCould_built(false);
                        return;
                    }
                }
            }


            //nếu diện tích có thể xây dựng đc
            for (int i = house.getPos().top; i <= house.getPos().bottom; i++) {
                for (int j = house.getPos().left; j <= house.getPos().right; j++) {
                    area_of_land[i][j] = 1;
                }
            }
            house.setCould_built(true);


            // reset lại diện tích lúc ban đầu
            for (int i = (int) house.getSaveTop(); i <= house.getSaveBottom(); i++) {
                for (int j = (int) house.getSaveLeft(); j <= house.getSaveRight(); j++) {
                    area_of_land[i][j] = 0;
                }
            }

            // cập nhập vị trí hiện tại cho biến save
            house.save_new_pos(house.getPos().top, house.getPos().bottom, house.getPos().right, house.getPos().left);



    }
}
