package com.example.testmap.MyGame;


import android.graphics.Canvas;
import android.view.SurfaceHolder;

// dùng để quản lý vòng lặp của game,UPS,FPS
public class GameLoop extends Thread {
    //MAX_Ups là cái quyết định fps và ups của game
    private static final double MAX_UPS=60.0;
    private static final double UPS_PERIOD=1E+3/MAX_UPS;
    private boolean isRunning=false;
    private SurfaceHolder surfaceHolder;
    private Game game;
    private double averageUPS;
    private double avergeFPS;
    public GameLoop(Game game, SurfaceHolder surfaceHolder){
        this.surfaceHolder=surfaceHolder;
        this.game=game;
    };
    public double getAverageUPS(){
        return averageUPS;
    };
    public double getAverageFPS(){
        return avergeFPS;
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
        //khởi tạo biến đếm thời gian và vòng lặp
        int updatecount=0;
        int framescount=0;
        long starttime=System.currentTimeMillis();
        long elapsedtime;
        long sleeptime;
        while(isRunning){


            try {
                // Render các đối tượng
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    // cập nhập các trạng thái của game sau đó mới vẽ
                    game.update();
                    updatecount++;
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
                        framescount++;
                    }
                    catch(IllegalArgumentException e){
                        e.printStackTrace();
                    }
                }


            }



            //dừng vòng lặp game để không vượt quá đối tượng UPS
            elapsedtime=System.currentTimeMillis()-starttime;
            sleeptime=(long)(updatecount*UPS_PERIOD-elapsedtime);
            if(sleeptime>0){
                try {
                    sleep((sleeptime));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            //bỏ qua các frames để đuổi kịp UPS
            while(sleeptime<0 && updatecount< MAX_UPS-1){
                game.update();
                updatecount++;
                elapsedtime=System.currentTimeMillis()-starttime;
                sleeptime=(long)(updatecount*UPS_PERIOD-elapsedtime);
            }

            //tính toán trung bình UPS và FPS
            elapsedtime=System.currentTimeMillis()-starttime;
            //nếu thời gian chạy vòng lặp >=1000 milis (= 1s) thì thiết lập lại mọi thứ và tính toán ÚP và FPS trung bình khi chạy con game
            if(elapsedtime>=1000){

                // công thức tính toán FPS hay UPS trung bình
                averageUPS=updatecount/(1E-3*elapsedtime);
                avergeFPS=framescount/(1E-3*elapsedtime);

                // set lại =0 để theo dõi mỗi lần chạy vòng lặp thí fps hay ups sẽ ra sao
                updatecount=0;
                framescount=0;
                starttime=System.currentTimeMillis();
            }
        }
    }


}
