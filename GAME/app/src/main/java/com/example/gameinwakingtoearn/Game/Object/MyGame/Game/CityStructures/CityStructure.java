package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemInBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.FireBaseMangament;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.MyStore;

import java.util.ArrayList;

public abstract class CityStructure extends Structure{
    public CityStructure(float x, float y, Context context, int id, int quatities_frame, int height, int width, String name, double cost, ArrayList<Structure> mycity, ArrayList<Structure> myDirt
            , MyStore store, MyBag bag) {
        super(x, y, context, id, quatities_frame, height, width, name, cost, mycity, myDirt,store,bag);
    }

    @Override
    public void removeStruct() {

        this.getMycity().remove(this);
        this.bag.getBagList().addNewItem(this.changeToItemInBag(), MyBag.posStartOfItem);



    }

    @Override
    public ItemInBag changeToItemInBag() {
        return null;
    }

    public static boolean checkIsLandHere(Structure s){

         boolean checkTop = false;
        boolean checkLeft = false;
        boolean checkRight = false;
        boolean checkBottom = false;
        boolean checkHalfLength = false;
        boolean checkHalfHeight = false;

        float halfLength = s.getImage().getPos().left + (s.getImage().getPos().right- s.getImage().getPos().left)/2;
        float halfHeight = s.getImage().getPos().top + (s.getImage().getPos().bottom- s.getImage().getPos().top)/2;


        //cách xây dựng thuật toán kiểm tra xem có đất không :
        /*
        *  + mỗi cityStructure của ta thì có chiều dài rộng chưa bị giới hạn bới 300,300
        *  + mỗi một ô đất có kích thước 100 x 100
         => do đó mà chỉ cần xét 6 điểm sau : top,left,right,bottom,halfLength,halfHeight
         => cm : dùng phản chứng và hai dữ kiện đã đặt ra
        * */

        if(s.getMyDirt().size() == 0){
            Log.e("myDirt ", " is empty");
            return false;
        }

        for(Structure dirt : s.getMyDirt()){

            if(dirt.getSaveTop() <= s.getImage().getPos().top  &&  s.getImage().getPos().top <= dirt.getSaveBottom()){
                checkTop = true;
            }

            if(dirt.getSaveTop() <= s.getImage().getPos().bottom  &&  s.getImage().getPos().bottom  <= dirt.getSaveBottom()){
                checkBottom = true;
            }

            if(dirt.getSaveTop() <= halfHeight && halfHeight  <= dirt.getSaveBottom()){
                checkHalfHeight = true;
            }

            if(dirt.getSaveLeft() <= halfLength && halfLength <= dirt.getSaveRight()){
                checkHalfLength = true;
            }

            if(dirt.getSaveLeft() <= s.getImage().getPos().left && s.getImage().getPos().left  <= dirt.getSaveRight()){
                checkLeft = true;
            }

            if(dirt.getSaveLeft() <= s.getImage().getPos().right && s.getImage().getPos().right <= dirt.getSaveRight()){
                checkRight = true;
            }

        }

        Log.e("check sth : ", "checkLeft : " + checkLeft +
                " checkRight : " + checkRight +
                " checkTop : " + checkTop +
                " checkBottom : " + checkBottom +
                " checkHalfHeight : " + checkHalfHeight +
                " checkHalfLength : " + checkHalfLength );

        return checkLeft && checkBottom && checkHalfLength && checkHalfHeight && checkRight && checkTop;
    }

    public void checkCould_built(){



        this.setCould_built(true);

        for(Structure s : this.getMycity()){
            if(!checkCould_builtFunction(s,this) ){

                this.setCould_built(false);
                Toast.makeText(this.getContext(),"không thể đặt tại vị trí này vì nơi này đã được sử dụng ", Toast.LENGTH_SHORT).show();
            }

            if(!checkIsLandHere(s) ){
                this.setCould_built(false);
                Toast.makeText(this.getContext(),"không thể đặt tại vị trí này vì nơi này  không có đất", Toast.LENGTH_SHORT).show();
            }


        }







    }
}
