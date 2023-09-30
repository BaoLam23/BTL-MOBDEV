package com.example.testmap.MyGame.Object;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.testmap.MyGame.OptionBars.OptionsBar;

public class Structure extends GameObject {


    private final Rect area;
    private float save_top;
    private float save_bottom;
    private float save_left;
    private float save_right;
    private boolean could_built=true;
    private final int size_area=5;
    private OptionsBar optionsBar;
    private boolean is_moved=false;

    private final Paint paint;
    public Structure(float x, float y, Context context,int id,int quatities_frame,int zoom){
        super(x,y,context,id,quatities_frame,zoom);
        save_bottom=this.y+this.height;
        save_top=this.y;
        save_left=this.x;
        save_right=this.x+this.width;
        this.area=new Rect((int) save_left-size_area, (int) save_top-size_area, (int) save_right+size_area, (int) save_bottom+size_area);
        paint=new Paint();
        paint.setColor(Color.GREEN);
        paint.setAlpha(60);
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
        if(could_built){
            this.paint.setColor(Color.GREEN);
        }
        else{
            this.paint.setColor(Color.RED);
        }
        if(is_clicked || is_moved){
            DrawArea(canvas);
            optionsBar.draw(canvas);
        }
        canvas.drawBitmap(image.getBitmap(),null,image.getPos(),null);

    }

    @Override
    public void check_is_clicked(float x,float y){
        Log.e("is yes button clicked",this.optionsBar.getYesButtonOfMoveOption().get_is_clicked()+"");
        Log.e("is yes button showed",this.optionsBar.getYesButtonOfMoveOption().getIs_showed()+"");
        Log.e("is no button clicked",this.optionsBar.getNoButtonOfMoveOption().get_is_clicked()+"");
        Log.e("is no button showed",this.optionsBar.getNoButtonOfMoveOption().getIs_showed()+"");
        optionsBar.getNoButtonOfMoveOption().check_is_clicked(x,y);
        optionsBar.getYesButtonOfMoveOption().check_is_clicked(x,y);


        if(optionsBar.getYesButtonOfMoveOption().get_is_clicked()){
            if(could_built){
                Log.e("could bulit","ok");
                save_top=y;
                save_left=x;
                save_right=x+width;
                save_bottom=y+height;
               resetDataInOptionsBar();
            }
        }

        if(optionsBar.getNoButtonOfMoveOption().get_is_clicked()){
            resetDataInOptionsBar();
        }


        if(x<=save_right && x>= save_left && y<=save_bottom && y>=save_top){
            set_is_clicked(true);
            this.optionsBar.getMoveOption().setIs_showed(true);
        }
        else{
            set_is_clicked(false);
        }

        if(!(x<=save_right && x>= save_left && y<=save_bottom && y>=save_top) && is_moved){

            this.x=save_left;
            this.y=save_top;
            resetDataInOptionsBar();
        }
        optionsBar.getMoveOption().check_is_clicked(x, y);
        if(optionsBar.getMoveOption().get_is_clicked()){
            this.is_moved=true;
        }




    }


    public void setCould_built(boolean b){
        this.could_built=b;
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
    public void setColorForAreaOfStructure(int c){
        this.paint.setColor(c);
    }
    public OptionsBar getOptionsBar(){
        return optionsBar;
    }
    public void setIs_moved(boolean b){
        this.is_moved=b;
    }
    public boolean getIs_moved(){return this.is_moved;}
    @Override
    public void setPos(float x,float y){
        if(is_moved) {
            this.x=x;
            this.y=y;
        }

    }

}
