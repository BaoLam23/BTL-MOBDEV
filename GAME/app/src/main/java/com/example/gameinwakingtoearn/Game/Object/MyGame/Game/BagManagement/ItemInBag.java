package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement;

import android.content.Context;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.GameObject;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList.AItemInList;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public abstract class ItemInBag extends AItemInList implements ItemInBagInterface {
    private ArrayList<Structure> city;
    protected int area[][];

    // đây là đối tượng structure cần ném ra để Baglist xử lý mỗi khi ta chọn vào từng item của một danh sách trong dãy danh sách
    protected GameObject structure = null;

    private int repeat=0;

    public ItemInBag(float x, float y, Context context,ArrayList<Structure> city,int area[][]) {
        super(x, y, context, R.drawable.icon_item_in_myteam, 10);
        this.city=city;
        this.area=area;

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



}
