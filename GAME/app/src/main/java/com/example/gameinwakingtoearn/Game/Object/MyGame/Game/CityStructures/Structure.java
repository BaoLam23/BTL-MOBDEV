package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.widget.Toast;


import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemInBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.FireBaseMangament;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.Game;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.GameObject;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.OptionsBar.MoveOption;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.OptionsBar.MoveToInventoryOption;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.OptionsBar.OptionsBar;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.OptionsBar.SellOption;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.MyStore;

import java.util.ArrayList;

public abstract class Structure extends GameObject {


    private final Rect area;
    private float save_top;
    private float save_bottom;
    private float save_left;
    private float save_right;
    private boolean could_built=false;
    private final int size_area=1;
    protected OptionsBar optionsBar;
    private boolean is_moved=false;

    private final Paint paint;

    private ArrayList<Structure> mycity;
    private ArrayList<Structure> myDirt;

    protected MyBag bag;
    protected MyStore store;


    protected double cost;
    private String name;

    private boolean status=false;



    public Structure(float x, float y, Context context,int id,int quatities_frame,int height,int width,
                      String name, double cost,ArrayList<Structure> mycity, ArrayList<Structure> myDirt,
                     MyStore store, MyBag bag){
        super(x,y,context,id,quatities_frame,height,width);

        save_bottom=this.y+this.getImage().getHeight();
        save_top=this.y;
        save_left=this.x;
        save_right=this.x+this.getImage().getWidth();
        this.area=new Rect((int) save_left-size_area, (int) save_top-size_area, (int) save_right+size_area, (int) save_bottom+size_area);
        paint=new Paint();
        paint.setColor(Color.RED);
        paint.setAlpha(30);
        optionsBar=new OptionsBar(x + (this.image.getPos().right - this.image.getPos().left)/2 -
                (MoveOption.width + SellOption.width +
                        MoveToInventoryOption.width)/2
                ,y+height+10,context);
        this.mycity = mycity;
        this.myDirt = myDirt;
        this.store = store;
        this.bag = bag;

        this.name = name;
        this.cost = cost;
    }

    @Override
    public void setContext(Context context){
        optionsBar.setContext(context);
        super.setContext(context);
    }

    public void setBag(MyBag b){
        this.bag = b;
    }

    public void setMyStore(MyStore store){
        this.store = store;
    }

    public void setMycity(ArrayList<Structure> mycity){
        this.mycity = mycity;
    }

    public void setMyDirt(ArrayList<Structure> myDirt){
        this.myDirt = myDirt;
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

    public void setStatus(boolean s){
        this.status = s;
    }

    public boolean getStatus(){
        return this.status;
    }

    public void resetDataInOptionsBar(){
        this.optionsBar.getMoveOption().set_is_clicked(false);
        this.optionsBar.getMoveToInventoryOption().set_is_clicked(false);
        this.optionsBar.getSellOption().set_is_clicked(false);

        this.optionsBar.getNoButtonOfMoveOption().set_is_clicked(false);
        this.optionsBar.getYesButtonOfMoveOption().set_is_clicked(false);
        this.optionsBar.getNoButtonOfMoveOption().setIs_showed(false);
        this.optionsBar.getYesButtonOfMoveOption().setIs_showed(false);

        this.optionsBar.getMoveOption().setIs_showed(false);
        this.optionsBar.getMoveToInventoryOption().setIs_showed(false);
        this.optionsBar.getSellOption().setIs_showed(false);

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

            Log.e("check dirt pos befor adjusting" ,this.image.getPos().left + " " + this.image.getPos().top );
            this.checkCould_built();

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
            this.optionsBar.getMoveToInventoryOption().setIs_showed(true);
            this.optionsBar.getSellOption().setIs_showed(true);
        }
        else{
            //set_is_clicked(false);
        }

        if(!(x<=this.image.getPos().right && x>= this.image.getPos().left && y<=this.image.getPos().bottom
                && y>=this.image.getPos().top) && this.is_moved){

            this.x=save_left;
            this.y=save_top;
            resetDataInOptionsBar();
            return;
        }

        optionsBar.check_is_clicked(x,y);
        if(optionsBar.getMoveOption().get_is_clicked()){
            this.is_moved=true;
            optionsBar.getMoveToInventoryOption().setIs_showed(false);
            optionsBar.getSellOption().setIs_showed(false);
        }

        if(optionsBar.getMoveToInventoryOption().get_is_clicked()){
            Log.e("remove structure", " active");

           removeStruct();
            resetDataInOptionsBar();
        }

//        if(optionsBar.getSellOption().get_is_clicked()){
//            removeStruct();
//
//            resetDataInOptionsBar();
//        }




    }

    public  abstract void  removeStruct();



    public static boolean checkCould_builtFunction(Structure placed, Structure needPlace){
        Log.e("check pos of " + placed.getName(), placed.getSaveLeft() + " " + placed.getSaveRight() + " " +
                placed.getSaveTop() + " " + placed.getSaveBottom());
           if(needPlace.getImage().getPos().left >= placed.getSaveRight() ||
                   needPlace.getImage().getPos().right <= placed.getSaveLeft() ||
                   needPlace.getImage().getPos().top >= placed.getSaveBottom() ||
                   needPlace.getImage().getPos().bottom <= placed.getSaveTop() ||
                   needPlace == placed
           ){
                   return  true;
           }
           return  false;
    }

    // hàm kiểm tra xem đất xây nhà có trống không
    public abstract void checkCould_built();

    public boolean isCould_built(){
        return this.could_built;
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

        if(this.is_moved ) {


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
        optionsBar.update(this.image.getPos().left+ (this.image.getPos().right - this.image.getPos().left)/2 -
                (MoveOption.width + SellOption.width +
                        MoveToInventoryOption.width)/2,this.image.getPos().top+this.image.getHeight());


    }

    public void setIs_moved(boolean b){
        this.is_moved = b;
    }
    public boolean isIs_moved(){
        return this.is_moved;
    }

    public void save_new_pos(float top,float bottom,float right,float left){
        if(this.could_built){
            save_top=top;
            save_bottom=bottom;
            save_left=left;
            save_right=right;
        }
    }

    public void setCould_built(boolean b){
        this.could_built = b;
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
    public  ArrayList<Structure> getMycity(){ return this.mycity; }
    public  ArrayList<Structure> getMyDirt(){ return this.myDirt; }
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

    public abstract ItemInBag changeToItemInBag();


}
