package com.example.gameinwakingtoearn;



import android.graphics.Canvas;
import android.view.SurfaceHolder;

// dùng để quản lý vòng lặp của game
public class GameLoop extends Thread {

    private boolean isRunning=false;
    private SurfaceHolder surfaceHolder;
    private Game game;

    public GameLoop(Game game, SurfaceHolder surfaceHolder){
        this.surfaceHolder=surfaceHolder;
        this.game=game;
    };


    public void startLoop() {
        isRunning=true;
        start();
    }
    @Override
    public void run(){
        super.run();
        //game loop
        Canvas canvas=null;

        while(isRunning){


            try {
                // Render các đối tượng
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    // cập nhập các trạng thái của game sau đó mới vẽ
                    game.update();
                    // yêu cầu lớp game hiện thị các đối tượng thông qua hàm vẽ
                    game.draw(canvas);

                }
            } catch(IllegalArgumentException e){
                e.printStackTrace();
            }
            finally {
                if(canvas!=null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);

                    }
                    catch(IllegalArgumentException e){
                        e.printStackTrace();
                    }
                }


            }

        }
    }


}

