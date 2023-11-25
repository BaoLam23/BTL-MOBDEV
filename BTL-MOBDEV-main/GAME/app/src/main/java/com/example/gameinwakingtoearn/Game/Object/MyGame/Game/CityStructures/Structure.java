package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;


import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.Game;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.GameObject;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.OptionsBar.OptionsBar;

import java.util.ArrayList;

public class Structure extends GameObject {


    private final Rect area;
    private float save_top;
    private float save_bottom;
    private float save_left;
    private float save_right;
    private boolean could_built=false;
    private final int size_area=1;
    private OptionsBar optionsBar;
    private boolean is_moved=false;

    private final Paint paint;

    private double cost;
    private String name;

    private int status;
    public static  final int inBagstatus = 0;
    public static  final int inMapstatus = 1;

    private ArrayList<ArrayList<Integer>> save_area;
    public Structure(float x, float y, Context context,int id,int quatities_frame,int height,int width,ArrayList<ArrayList<Integer>> save_area,
                      String name, double cost){
        super(x,y,context,id,quatities_frame,height,width);
        this.save_area=save_area;
        save_bottom=this.y+this.getImage().getHeight();
        save_top=this.y;
        save_left=this.x;
        save_right=this.x+this.getImage().getWidth();
        this.area=new Rect((int) save_left-size_area, (int) save_top-size_area, (int) save_right+size_area, (int) save_bottom+size_area);
        paint=new Paint();
        paint.setColor(Color.RED);
        paint.setAlpha(30);
        optionsBar=new OptionsBar(x,y+height+10,context);


        this.name = name;
        this.cost = cost;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setStatus(int s){
        this.status = s;
    }

    public int getStatus(){
        return this.status;
    }

    private void resetDataInOptionsBar(){
        this.optionsBar.getMoveOption().set_is_clicked(false);
        this.optionsBar.getNoButtonOfMoveOption().set_is_clicked(false);
        this.optionsBar.getYesButtonOfMoveOption().set_is_clicked(false);
        this.optionsBar.getNoButtonOfMoveOption().setIs_showed(false);
        this.optionsBar.getYesButtonOfMoveOption().setIs_showed(false);
        this.optionsBar.getMoveOption().setIs_showed(false);
        this.is_clicked=false;
        this.is_moved=false;


    }
    public void draw(Canvas canvas){

        if(is_clicked || is_moved){
            DrawArea(canvas);
            optionsBar.draw(canvas);
        }
        canvas.drawBitmap(image.getBitmap(),null,image.getPos(),null);

    }

    @Override
    public void check_is_clicked(float x,float y){

        optionsBar.getNoButtonOfMoveOption().check_is_clicked(x,y);
        optionsBar.getYesButtonOfMoveOption().check_is_clicked(x,y);



        if(optionsBar.getYesButtonOfMoveOption().get_is_clicked()){
            this.checkCould_built();
            Log.e("check could build",this.could_built+"");
            if(could_built){

                this.x=this.image.getPos().left;
                this.y=this.image.getPos().top;
                save_new_pos(this.y,this.y+ image.getHeight(),this.x+ image.getWidth(),this.x);
                resetDataInOptionsBar();
                this.could_built=false;

            }
            else{
                this.optionsBar.getYesButtonOfMoveOption().set_is_clicked(false);
            }
            return;

        }

        if(optionsBar.getNoButtonOfMoveOption().get_is_clicked()){
            this.x=save_left;
            this.y=save_top;
            resetDataInOptionsBar();
            return;
        }


        if(x<=save_right && x>= save_left && y<=save_bottom && y>=save_top){
            set_is_clicked(true);
            this.optionsBar.getMoveOption().setIs_showed(true);
        }
        else{
            set_is_clicked(false);
        }

        if(!(x<=this.image.getPos().right && x>= this.image.getPos().left && y<=this.image.getPos().bottom
                && y>=this.image.getPos().top) && this.is_moved){

            this.x=save_left;
            this.y=save_top;
            resetDataInOptionsBar();
            return;
        }
        optionsBar.getMoveOption().check_is_clicked(x, y);
        if(optionsBar.getMoveOption().get_is_clicked()){
            this.is_moved=true;
        }




    }



    // hàm kiểm tra xem đất xây nhà có trống không
    public void checkCould_built(){


        //nếu diện tích đã bị chiếm
       for(int i=(int)this.image.getPos().top;i<=(int)this.image.getPos().bottom;i++){
           for(int j=(int)this.image.getPos().left;j<=(int)this.image.getPos().right;j++){
               if(this.save_area.get(i).get(j)==1){
                   Log.e("area :", save_area.get(i).get(j)+"");
                   this.could_built=false;
                   return;
               }
           }
       }


       //nếu diện tích không bị chiếm => set vị trí trên save area
        for(int i=(int)this.image.getPos().top;i<=(int)this.image.getPos().bottom;i++){
            for(int j=(int)this.image.getPos().left;j<=(int)this.image.getPos().right;j++){
                this.save_area.get(i).set(j,1);
            }
        }
        this.could_built=true;



        //reset lại vị trí đặt ban đầu của kiến trúc sau khi đã có vị trí moiws
        for(int i=(int)save_top;i<=(int)save_bottom;i++){
            for(int j=(int)save_left;j<=(int)save_right;j++){
                this.save_area.get(i).set(j,0);
            }
        }


    }
    public static float fixPosX(float pos,int width){
        if(pos <= Game.AreaLeft){
            return Game.AreaLeft;
        }

        if(pos + width>= Game.AreaRight){
            return Game.AreaRight - width;
        }

        return pos;
    }

    public static float fixPosY(float pos,int height){


        if(pos <= Game.AreaTop){
            return  Game.AreaTop;
        }

        if(pos + height>= Game.AreaBottom){
            return  Game.AreaBottom - height;
        }
        return pos;
    }
    @Override
    public void update() {

        if(this.is_moved) {


            x= fixPosX(x, getImage().getWidth());
            y= fixPosY(y, getImage().getHeight());

            this.image.getPos().bottom = (int) y + this.image.getHeight();
            this.image.getPos().left = (int) x;
            this.image.getPos().top = (int) y;
            this.image.getPos().right = (int) x + this.image.getWidth();


        }
        else {
            this.image.getPos().bottom = (int) save_bottom;
            this.image.getPos().top = (int) save_top;
            this.image.getPos().left = (int) save_left;
            this.image.getPos().right = (int) save_right;
        }
        optionsBar.update(this.image.getPos().left,this.image.getPos().top+this.image.getHeight());


    }

    public void save_new_pos(float top,float bottom,float right,float left){
        if(this.could_built){
            save_top=top;
            save_bottom=bottom;
            save_left=left;
            save_right=right;
        }
    }
    public float getSaveBottom(){
        return save_bottom;
    }
    public float getSaveTop(){
        return save_top;
    }
    public float getSaveRight(){
        return save_right;
    }
    public float getSaveLeft(){
        return save_left;
    }
    public void DrawArea(Canvas canvas){
        area.right=this.image.getPos().right + size_area;
        area.left=this.image.getPos().left - size_area;
        area.bottom=this.image.getPos().bottom + size_area;
        area.top=this.image.getPos().top - size_area;
        canvas.drawRect(area,paint);
    }


    @Override
    public void setPos(float x,float y){
        if(is_moved && !this.optionsBar.getYesButtonOfMoveOption().get_is_clicked() &&
                !this.optionsBar.getNoButtonOfMoveOption().get_is_clicked()) {
            this.x=x;
            this.y=y;
        }

    }

}
