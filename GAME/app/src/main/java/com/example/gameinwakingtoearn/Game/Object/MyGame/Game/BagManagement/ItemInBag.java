package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement;

import android.content.Context;
import android.util.Log;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.GameObject;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList.AItemInList;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public abstract class ItemInBag extends AItemInList implements ItemInBagInterface {
    private ArrayList<Structure> city;
    private ArrayList<Structure> dirt;
    public static final  int id =  R.drawable.item_box;

    // đây là đối tượng structure cần ném ra để Baglist xử lý mỗi khi ta chọn vào từng item của một danh sách trong dãy danh sách
    protected GameObject structure = null;

    private int repeat=0;

    public static  final int height = 130;
    public static  final int width = 130;
    public ItemInBag(float x, float y, Context context,ArrayList<Structure> city,ArrayList<Structure> dirt) {
        super(x, y, context, id, height,width);
        this.city=city;
        this.dirt = dirt;


    }

    public GameObject throwStruture(){
        return this.structure;
    }



    @Override
    public void check_is_clicked(float x,float y){
        super.check_is_clicked(x,y);
        if(this.is_clicked && repeat == 0 ){
            addSymbolStruture();
            repeat++;

        }
    }

    public void setCity(ArrayList<Structure>  city){

        this.city =city;

    }

    public void setDirt( ArrayList<Structure> dirt){

        this.dirt = dirt;

    }

    public ArrayList<Structure> getCity(){
        return  this.city;
    }

    public ArrayList<Structure> getDirt(){
        return  this.dirt;
    }



}
