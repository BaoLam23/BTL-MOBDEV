package com.example.gameinwakingtoearn;


import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.gameinwakingtoearn.Game.Object.Models.Item;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemDirt1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouse1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemInBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Dirt1;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.House1;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList.AItemInList;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.ItemHouse1InStore;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class BagTest {
    private MyBag myBag;

    @Test
    public void checkStructureSymbolDirt(){
        boolean checkSymbol = false;
        boolean checkStructure = false;
        ArrayList<Structure> cityStructure = new ArrayList<>();
        ArrayList<Structure> dirt = new ArrayList<>();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        myBag = new MyBag(0,0,appContext,cityStructure,dirt,null);
        myBag.check_is_clicked(myBag.getPosX(),myBag.getPosY());
        myBag.getBagList().addNewItem(new ItemDirt1InBag(0,0,appContext,cityStructure,dirt),200);
        myBag.check_is_clicked(myBag.getBagList().getMenuItem()[0].getItemList()[0].getPosX(),
                myBag.getBagList().getMenuItem()[0].getItemList()[0].getPosY());

        if(myBag.getBagList().throwStrutures().getId() == Dirt1.id){
            checkSymbol = true;
        }

        if(myBag.getBagList().createStructure(0,0,cityStructure,dirt,null,null) instanceof  Dirt1){
            checkStructure = true;
        }

        boolean expected = true;
        assertEquals(expected,checkSymbol);
        assertEquals(expected,checkStructure);

        }

    @Test
    public void checkStructureSymbolHouse1(){
        boolean checkSymbol = false;
        boolean checkStructure = false;
        ArrayList<Structure> cityStructure = new ArrayList<>();
        ArrayList<Structure> dirt = new ArrayList<>();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        myBag = new MyBag(0,0,appContext,cityStructure,dirt,null);
        myBag.check_is_clicked(myBag.getPosX(),myBag.getPosY());
        myBag.getBagList().addNewItem(new ItemHouse1InBag(0,0,appContext,cityStructure,dirt),200);
        myBag.check_is_clicked(myBag.getBagList().getMenuItem()[0].getItemList()[0].getPosX(),
                myBag.getBagList().getMenuItem()[0].getItemList()[0].getPosY());

        if(myBag.getBagList().throwStrutures().getId() == House1.id){
            checkSymbol = true;
        }

        if(myBag.getBagList().createStructure(0,0,cityStructure,dirt,null,null) instanceof  House1){
            checkStructure = true;
        }

        boolean expected = true;
        assertEquals(expected,checkSymbol);
        assertEquals(expected,checkStructure);

    }

    @Test
    public void checkDeleteFunctionItem(){


        boolean checkItem = false;

        ArrayList<Structure> cityStructure = new ArrayList<>();
        ArrayList<Structure> dirt = new ArrayList<>();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        myBag = new MyBag(0,0,appContext,cityStructure,dirt,null);

        for(int i=0;i<4;i++){
            myBag.getBagList().addNewItem(new ItemHouse1InBag(0,0,appContext,cityStructure,dirt),200);
        }

        myBag.check_is_clicked(myBag.getPosX(),myBag.getPosY());

        ItemInBag itemInBag = (ItemInBag) myBag.getBagList().getMenuItem()[0].getItemList()[3];

        myBag.check_is_clicked(myBag.getBagList().getMenuItem()[0].getItemList()[2].getPosX(),
                myBag.getBagList().getMenuItem()[0].getItemList()[2].getPosY());
        myBag.getBagList().deleteItemInBagFunction();



        if( myBag.getBagList().getMenuItem()[0].getItemList()[2] == itemInBag){
            checkItem = true;
        }


        boolean expected = true;
        int expectedSize = 3;
        int expectedItemThrowed = 2;
        assertEquals(expectedItemThrowed,myBag.getBagList().getIndexOfItemNeedThrown());
        assertEquals(expectedSize,myBag.getBagList().getMenuItem()[0].getQua_of_item());
        assertEquals(expected,checkItem);


    }



}
