package com.example.gameinwakingtoearn;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouse1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList.AItemInList;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList.ItemsList;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ItemListTestCase {



    @Test
    public void testPushContextIntoObject(){
        boolean check = true;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        AItemInList aItemInList = new AItemInList(7,8,context,R.drawable.icon_item_in_myteam,80,79);
        if(aItemInList.getContext() == null){
            check = false;
        }

        boolean expected = true;
        assertEquals(expected,check);

    }

    @Test
    public void checkImageItem(){

        boolean check = true;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        AItemInList aItemInList = new AItemInList(7,8,context,R.drawable.icon_item_in_myteam,80,79);
        if(aItemInList.getImage() == null){
            check = false;
        }

        boolean expected = true;
        assertEquals(expected,check);

    }

    @Test
    public void checkAddItem(){
        boolean check = true;
        boolean checkSize = true;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ItemsList itemsList = new ItemsList(10,4,0);
        ItemHouse1InBag aItemInList = new ItemHouse1InBag(4,5,context,null,null);
        if(aItemInList.getImage() == null || aItemInList.getContext() == null){
            check = false;
        } else{
            itemsList.addItem(aItemInList,3,4,0);
        }

        if(itemsList.getQua_of_item() == 0){
            checkSize = false;
        }

        boolean expected = true;
        assertEquals(expected,check);
        assertEquals(expected,checkSize);
    }

    @Test
    public void testNumberPage(){

        int maxItemInPage = 10;
        ItemsList itemsList = new ItemsList(maxItemInPage,10,0);
        for(int i=0 ;i<49;i++){
            Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
            ItemHouse1InBag item = new ItemHouse1InBag(4,5,context,null,null);
            itemsList.addItem(item,0,0,0);

        }
        int expected = 10;
        assertEquals(expected,itemsList.getQua_of_item());


    }

    @Test
    public void testNumberPage1(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        int maxItemInPage = 10;
        ItemsList itemsList = new ItemsList(maxItemInPage,10,0);
        for(int i=0 ;i<3;i++){
            ItemHouse1InBag item = new ItemHouse1InBag(4,5,context,null,null);
            itemsList.addItem(item,0,0,0);
        }
        int expected = 3;
        assertEquals(expected,itemsList.getQua_of_item());


    }


    @Test
    public void testNumberPage2(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        int maxItemInPage = 0;
        ItemsList itemsList = new ItemsList(maxItemInPage,0,0);
        for(int i=0 ;i<3;i++){
            itemsList.addItem(new ItemHouse1InBag(4,5,context,null,null),0,0,0);
        }
        int expected = 0;
        assertEquals(expected,itemsList.getQua_of_item());


    }

    @Test
    public void testDistanceBetweenItemInARow(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        int distant_between_item = 30;
        ItemsList itemsList = new ItemsList(10,5,distant_between_item);
        for(int i=0 ;i<5;i++){
            itemsList.addItem(new ItemHouse1InBag(4,5,context,null,null),0,0,0);
        }
        int expected = distant_between_item;
        int check1 = itemsList.getItemList()[1].getImage().getPos().left - itemsList.getItemList()[0].getImage().getPos().right;
        int check2= itemsList.getItemList()[2].getImage().getPos().left - itemsList.getItemList()[1].getImage().getPos().right;
        assertEquals(expected,check1);
        assertEquals(expected,check2);

    }

    @Test
    public void testDistanceBetweenItemInARow2(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        int distant_between_item = 73;
        ItemsList itemsList = new ItemsList(10,5,distant_between_item);
        for(int i=0 ;i<5;i++){
            itemsList.addItem(new ItemHouse1InBag(4,5,context,null,null),0,0,0);
        }
        int expected = distant_between_item;
        int check1 = itemsList.getItemList()[1].getImage().getPos().left - itemsList.getItemList()[0].getImage().getPos().right;
        int check2= itemsList.getItemList()[2].getImage().getPos().left - itemsList.getItemList()[1].getImage().getPos().right;
        assertEquals(expected,check1);
        assertEquals(expected,check2);

    }

    @Test
    public void testDistanceBetweenItemInAColumn(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        int distant_between_item = 30;
        ItemsList itemsList = new ItemsList(10,3,distant_between_item);
        for(int i=0 ;i<5;i++){
            itemsList.addItem(new ItemHouse1InBag(4,5,context,null,null),0,0,0);
        }
        int expected = 0;
        int expected2 = 3;

        int check1 = itemsList.getItemList()[1].getImage().getPos().top - itemsList.getItemList()[0].getImage().getPos().top;
        int check2= itemsList.getItemList()[3].getImage().getPos().top - itemsList.getItemList()[1].getImage().getPos().bottom;
        int check3= itemsList.getItemList()[4].getImage().getPos().top - itemsList.getItemList()[1].getImage().getPos().bottom;
        int check4 = itemsList.getItemList()[2].getImage().getPos().top - itemsList.getItemList()[0].getImage().getPos().top;

        assertEquals(expected,check1);
        assertEquals(expected2,check2);
        assertEquals(expected2,check3);
        assertEquals(expected,check4);

    }

    @Test
    public void testPosItemStored(){

        int x = 3;
        int y = 5;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        ItemsList itemsList = new ItemsList(10,3,20);

        itemsList.addItem(new ItemHouse1InBag(x,y,context,null,null),0,0,0);

        int expectedLeft = itemsList.getItemList()[0].getImage().getPos().left + AItemInList.distanceFromBorder;
        int expectedTop = itemsList.getItemList()[0].getImage().getPos().top + AItemInList.distanceFromBorder;
        int  expectedRight = expectedLeft + itemsList.getItemList()[0].getItemstored().getImage().getWidth();
        int  expectedBottom = expectedTop + itemsList.getItemList()[0].getItemstored().getImage().getHeight();


        assertEquals(expectedLeft,itemsList.getItemList()[0].getItemstored().getImage().getPos().left);
        assertEquals(expectedTop,itemsList.getItemList()[0].getItemstored().getImage().getPos().top);
        assertEquals(expectedRight,itemsList.getItemList()[0].getItemstored().getImage().getPos().right);
        assertEquals(expectedBottom,itemsList.getItemList()[0].getItemstored().getImage().getPos().bottom);


    }

    @Test
    public void testPosItemStored1(){

        int x = 53;
        int y = 95;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        ItemsList itemsList = new ItemsList(10,3,20);

        itemsList.addItem(new ItemHouse1InBag(x,y,context,null,null),0,0,0);

        int expectedLeft = itemsList.getItemList()[0].getImage().getPos().left + AItemInList.distanceFromBorder;
        int expectedTop = itemsList.getItemList()[0].getImage().getPos().top + AItemInList.distanceFromBorder;
        int  expectedRight = expectedLeft + itemsList.getItemList()[0].getItemstored().getImage().getWidth();
        int  expectedBottom = expectedTop + itemsList.getItemList()[0].getItemstored().getImage().getHeight();


        assertEquals(expectedLeft,itemsList.getItemList()[0].getItemstored().getImage().getPos().left);
        assertEquals(expectedTop,itemsList.getItemList()[0].getItemstored().getImage().getPos().top);
        assertEquals(expectedRight,itemsList.getItemList()[0].getItemstored().getImage().getPos().right);
        assertEquals(expectedBottom,itemsList.getItemList()[0].getItemstored().getImage().getPos().bottom);


    }


}
