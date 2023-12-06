package com.example.gameinwakingtoearn;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList.AItemInList;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList.MyListManagement;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.ItemHouse1InStore;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.MyStore;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class MyListManagementTest {

    @Test
    public void checkSizeOfPage(){
        //check add bất kỳ
        int maxPage = 5;
        int maxItemInPage = 1;
        int maxColumn = 1;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyListManagement myListManagement = new MyListManagement(appContext,0,0,maxPage,maxItemInPage,maxColumn,20,R.drawable.app_bg,0,0,100,100);
        for(int i=0;i<3;i++) {
            myListManagement.addNewItem(new ItemHouse1InStore(0, 0, appContext, null, null, null, null), 0);
        }
        int expected = 3;
        assertEquals(expected,myListManagement.getLastPage().getNumber_of_page());
    }

    @Test
    public void checkSizeOfPage2(){
        //check vượt quá
        int maxPage = 5;
        int maxItemInPage = 3;
        int maxColumn = 1;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyListManagement myListManagement = new MyListManagement(appContext,0,0,maxPage,maxItemInPage,maxColumn,20,R.drawable.app_bg,0,0,100,100);
        for(int i=0;i<20;i++) {
            myListManagement.addNewItem(new ItemHouse1InStore(0, 0, appContext, null, null, null, null), 0);
        }
        int expected = 5;
        assertEquals(expected,myListManagement.getLastPage().getNumber_of_page());
    }

    @Test
    public void checkSizeOfPage3(){
        //check nhỏ hơn
        int maxPage = 10;
        int maxItemInPage = 3;
        int maxColumn = 1;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyListManagement myListManagement = new MyListManagement(appContext,0,0,maxPage,maxItemInPage,maxColumn,20,R.drawable.app_bg,0,0,100,100);
        for(int i=0;i<8;i++) {
            myListManagement.addNewItem(new ItemHouse1InStore(0, 0, appContext, null, null, null, null), 0);
        }
        int expected = 3;
        assertEquals(expected,myListManagement.getLastPage().getNumber_of_page());
    }

    @Test
    public void checkSizeOfPage4(){
        //check vừa đủ
        int maxPage = 5;
        int maxItemInPage = 3;
        int maxColumn = 1;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyListManagement myListManagement = new MyListManagement(appContext,0,0,maxPage,maxItemInPage,maxColumn,20,R.drawable.app_bg,0,0,100,100);
        for(int i=0;i<3;i++) {
            myListManagement.addNewItem(new ItemHouse1InStore(0, 0, appContext, null, null, null, null), 0);
        }
        int expected = 1;
        assertEquals(expected,myListManagement.getLastPage().getNumber_of_page());
    }

    @Test
    public void checkPreviousButtonClicked1(){
        // không thể lùi nữa
        int maxPage = 10;
        int maxItemInPage = 2;
        int maxColumn = 1;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyListManagement myListManagement = new MyListManagement(appContext,0,0,maxPage,maxItemInPage,maxColumn,20,R.drawable.app_bg,0,0,100,100);
        for(int i=0;i<10;i++) {
            myListManagement.addNewItem(new ItemHouse1InStore(0, 0, appContext, null, null, null, null), 0);
        }

        myListManagement.checkIsClicked(myListManagement.getPreButton().getPosX(),myListManagement.getPreButton().getPosY());
        int expected = 1;
        assertEquals(expected,myListManagement.getCurrentPage().getNumber_of_page());
    }

    @Test
    public void checkPreviousButtonClicked2(){
        // có thể thể lùi nữa
        int maxPage = 10;
        int maxItemInPage = 2;
        int maxColumn = 1;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyListManagement myListManagement = new MyListManagement(appContext,0,0,maxPage,maxItemInPage,maxColumn,20,R.drawable.app_bg,0,0,100,100);
        for(int i=0;i<10;i++) {
            myListManagement.addNewItem(new ItemHouse1InStore(0, 0, appContext, null, null, null, null), 0);
        }

        for(int i=0;i<3;i++){
            myListManagement.checkIsClicked(myListManagement.getNextButtonButton().getPosX(),myListManagement.getNextButtonButton().getPosY());
        }
        myListManagement.checkIsClicked(myListManagement.getPreButton().getPosX(),myListManagement.getPreButton().getPosY());
        int expected = 3;
        assertEquals(expected,myListManagement.getCurrentPage().getNumber_of_page());
    }

    @Test
    public void checkNextButtonClicked1(){
        // không thể tăng nữa
        int maxPage = 10;
        int maxItemInPage = 2;
        int maxColumn = 1;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyListManagement myListManagement = new MyListManagement(appContext,0,0,maxPage,maxItemInPage,maxColumn,20,R.drawable.app_bg,0,0,100,100);
        for(int i=0;i<10;i++) {
            myListManagement.addNewItem(new ItemHouse1InStore(0, 0, appContext, null, null, null, null), 0);
        }

        for(int i=0;i<13;i++){
            myListManagement.checkIsClicked(myListManagement.getNextButtonButton().getPosX(),myListManagement.getNextButtonButton().getPosY());
        }


        int expected = 5;
        assertEquals(expected,myListManagement.getCurrentPage().getNumber_of_page());
    }

    @Test
    public void checkNextButtonClicked2(){
        // có thể tăng nữa
        int maxPage = 10;
        int maxItemInPage = 2;
        int maxColumn = 1;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyListManagement myListManagement = new MyListManagement(appContext,0,0,maxPage,maxItemInPage,maxColumn,20,R.drawable.app_bg,0,0,100,100);
        for(int i=0;i<10;i++) {
            myListManagement.addNewItem(new ItemHouse1InStore(0, 0, appContext, null, null, null, null), 0);
        }

        for(int i=0;i<3;i++){
            myListManagement.checkIsClicked(myListManagement.getNextButtonButton().getPosX(),myListManagement.getNextButtonButton().getPosY());
        }


        int expected = 4;
        assertEquals(expected,myListManagement.getCurrentPage().getNumber_of_page());
    }

    @Test
    public void checkQuitButtonClicked(){
        // có thể tăng nữa
        int maxPage = 10;
        int maxItemInPage = 2;
        int maxColumn = 1;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyListManagement myListManagement = new MyListManagement(appContext,0,0,maxPage,maxItemInPage,maxColumn,20,R.drawable.app_bg,0,0,100,100);
        for(int i=0;i<10;i++) {
            myListManagement.addNewItem(new ItemHouse1InStore(0, 0, appContext, null, null, null, null), 0);
        }

        myListManagement.checkIsClicked(myListManagement.getQuitButton().getPosX(),myListManagement.getQuitButton().getPosY());

        boolean expected = true;
        assertEquals(expected,myListManagement.getIs_quit());
    }

    @Test
    public void checkNextButtonClicked4(){
        // có thể vừa tăng vừa giảm
        int maxPage = 120;
        int maxItemInPage = 1;
        int maxColumn = 1;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MyListManagement myListManagement = new MyListManagement(appContext,0,0,maxPage,maxItemInPage,maxColumn,20,R.drawable.app_bg,0,0,100,100);
        for(int i=0;i<120;i++) {
            myListManagement.addNewItem(new ItemHouse1InStore(0, 0, appContext, null, null, null, null), 0);
        }

        for(int i=0;i<70;i++){
            myListManagement.checkIsClicked(myListManagement.getNextButtonButton().getPosX(),myListManagement.getNextButtonButton().getPosY());
        }

        for(int i=0;i<30;i++){
            myListManagement.checkIsClicked(myListManagement.getPreButton().getPosX(),myListManagement.getPreButton().getPosY());
        }


        int expected = 41;
        assertEquals(expected,myListManagement.getCurrentPage().getNumber_of_page());
    }






}
