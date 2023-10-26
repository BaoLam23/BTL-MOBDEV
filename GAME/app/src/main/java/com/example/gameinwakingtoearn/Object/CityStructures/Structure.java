package com.example.gameinwakingtoearn.Object.CityStructures;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.gameinwakingtoearn.Object.GameObject;
import com.example.gameinwakingtoearn.OptionsBar.OptionsBar;

public class Structure extends GameObject {


    private final Rect area;
    private float save_top;
    private float save_bottom;
    private float save_left;
    private float save_right;
    private boolean could_built=false;
    private final int size_area=5;
    private OptionsBar optionsBar;
    private boolean is_moved=false;

    private final Paint paint;
    private int save_area[][];
    public Structure(float x, float y, Context context,int id,int quatities_frame,int zoom,int a[][]){
        super(x,y,context,id,quatities_frame,zoom);
        this.save_area=a;
        save_bottom=this.y+this.height;
        save_top=this.y;
        save_left=this.x;
        save_right=this.x+this.width;
        this.area=new Rect((int) save_left-size_area, (int) save_top-size_area, (int) save_right+size_area, (int) save_bottom+size_area);
        paint=new Paint();
        paint.setColor(Color.GREEN);
        paint.setAlpha(30);
        optionsBar=new OptionsBar(x,y+height+10,context);
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
                save_new_pos(this.y,this.y+height,this.x+width,this.x);
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



    public void checkCould_built(){

        //nếu diện tích đã bị chiếm
       for(int i=(int)this.image.getPos().top;i<=(int)this.image.getPos().bottom;i++){
           for(int j=(int)this.image.getPos().left;j<=(int)this.image.getPos().right;j++){
               if(this.save_area[i][j]==1){
                   this.could_built=false;
                   return;
               }
           }
       }


       //nếu diện tích không bị chiếm => set vị trí trên save area
        for(int i=(int)this.image.getPos().top;i<=(int)this.image.getPos().bottom;i++){
            for(int j=(int)this.image.getPos().left;j<=(int)this.image.getPos().right;j++){
                this.save_area[i][j]=1;
            }
        }
        this.could_built=true;



        //reset lại vị trí đặt ban đầu của kiến trúc sau khi đã có vị trí moiws
        for(int i=(int)save_top;i<=(int)save_bottom;i++){
            for(int j=(int)save_left;j<=(int)save_right;j++){
                this.save_area[i][j]=0;
            }
        }
        return;

    }
    @Override
    public void update() {

        if(this.is_moved) {


            this.image.getPos().bottom = (int) y + this.height;
            this.image.getPos().left = (int) x;
            this.image.getPos().top = (int) y;
            this.image.getPos().right = (int) x + this.width;


        }
        else {
            this.image.getPos().bottom = (int) save_bottom;
            this.image.getPos().top = (int) save_top;
            this.image.getPos().left = (int) save_left;
            this.image.getPos().right = (int) save_right;
        }
        optionsBar.update(this.image.getPos().left,this.image.getPos().top+this.height);


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
        area.right=this.image.getPos().right;
        area.left=this.image.getPos().left;
        area.bottom=this.image.getPos().bottom;
        area.top=this.image.getPos().top;
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
