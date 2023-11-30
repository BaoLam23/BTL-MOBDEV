package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.widget.Toast;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.FireBaseMangament;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.Game;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.MyStore;

import java.util.ArrayList;

public abstract class Dirt extends Structure{

    public static final int height = 100;
    public static final int width = 100;
    public Dirt(float x, float y, Context context, int id, String name, double cost, ArrayList<Structure> mycity,
                ArrayList<Structure> myDirt,MyStore store, MyBag bag) {
        super(x, y, context, id, 0, height, width, name, cost,mycity,myDirt,store,bag);
    }

    public boolean checkContainsStructure(){
        //kiểm tra xem có chứa căn nhà nào không
        boolean flag = false;
          for(Structure s : getMycity()){
              if(s.getSaveLeft() >= this.getSaveRight() ||
                      s.getSaveRight() <= this.getSaveLeft() ||
                      s.getSaveTop() >= this.getSaveBottom() ||
                      s.getSaveBottom() <= this.getSaveTop() ){

              } else{
                  flag = true;
              }
          }
          return flag;
    }

    public static void fixPosOfDirt(Rect rect){

        //lấy được trọng tâm của ô đất
        int centerPosX = rect.left + (rect.right - rect.left)/2;
        int centerPosY = rect.top + (rect.bottom - rect.top)/2;
        Log.e("check pos of dirt before revise :",centerPosX + " " + centerPosY);

        //kiểm tra và chỉnh sửa trọng tâm ô đất đó
        int leftFixed =(int) ((centerPosX - Game.AreaLeft)/(width)) * (width) + Game.AreaLeft;
        int topFixed = (int)((centerPosY- Game.AreaTop)/(height)) * (height) + Game.AreaTop;

        Log.e("check pos of dirt after revise :",leftFixed + " " + topFixed);
        rect.set(leftFixed,topFixed,leftFixed + width,topFixed + height);



    }



    @Override
    public void checkCould_built() {
        fixPosOfDirt(this.image.getPos());

        this.setCould_built(true);

        for(Structure s : getMyDirt()){
            if(!checkCould_builtFunction(s,this)){
                this.setCould_built(false);
            }
        }

        if(!this.isCould_built()){
            Toast.makeText(this.getContext(),"không thể đặt tại vị trí này vì nơi này đã được sử dụng", Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void removeStruct() {
        if(!this.checkContainsStructure()) {
            this.getMyDirt().remove(this);
            this.bag.getBagList().addNewItem(this.changeToItemInBag(), MyBag.posStartOfItem);
        }
    }

    @Override
    public void update() {

        if(this.isIs_moved() && !this.checkContainsStructure()) {


            x= fixPosX(x, getImage().getWidth());
            y= fixPosY(y, getImage().getHeight());

            this.image.getPos().bottom = (int) y + this.image.getHeight();
            this.image.getPos().left = (int) x;
            this.image.getPos().top = (int) y;
            this.image.getPos().right = (int) x + this.image.getWidth();


        }
        else {
            this.image.getPos().bottom = (int) getSaveBottom();
            this.image.getPos().top = (int) getSaveTop();
            this.image.getPos().left = (int) getSaveLeft();
            this.image.getPos().right = (int) getSaveRight();
        }
        optionsBar.update(this.image.getPos().left,this.image.getPos().top+this.image.getHeight());


    }
}
