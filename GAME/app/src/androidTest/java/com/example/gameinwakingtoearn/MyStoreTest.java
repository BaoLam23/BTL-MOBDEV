package com.example.gameinwakingtoearn;


import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemDirt1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouse1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouse2InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouse3InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemTree1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemTree2InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemTree3InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemTree4InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.CityStructure;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Dirt;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Dirt1;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.House1;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.House2;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.House3;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Tree1;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Tree2;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Tree3;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Tree4;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.FireBaseMangament;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList.AItemInList;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList.MyListManagement;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.ItemDirt1InStore;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.ItemHouse1InStore;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.ItemHouse2InStore;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.ItemHouse3InStore;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.ItemTree1InStore;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.ItemTree2InStore;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.ItemTree3InStore;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.ItemTree4InStore;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.MyStore;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.StoreItemList;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class MyStoreTest {
    private MyBag myBag;
    private MyStore myStore;
    private ArrayList<Structure> cityStructuresInBag = new ArrayList<>();
    private ArrayList<Structure> dirtsInBag = new ArrayList<>();

    private ArrayList<Structure> cityStructures = new ArrayList<>();
    private ArrayList<Structure> dirts = new ArrayList<>();


    @Test
    public void checkBuyOfHouse1(){
        boolean check = false;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        long money = 10000;

        myBag = new MyBag(0,0,appContext,cityStructures,dirts,myStore);
        myStore = new MyStore(0,0,appContext,myBag,cityStructuresInBag,dirtsInBag,money);
        FireBaseMangament.setPhakeLevel(ItemHouse1InStore.levelRequired);

        myStore.check_is_clicked(myStore.getPosX(),myStore.getPosY());



        myStore.check_is_clicked(myStore.getItemList().getMenuItem()[0].getItemList()[StoreItemList.house1].getPosX(),
                myStore.getItemList().getMenuItem()[0].getItemList()[StoreItemList.house1].getPosY());

        long expected = (long)(money - House1.cost);
        boolean expectedCheck = true;
         if (myBag.getBagList().getCurrentPage().getItemList()[0] instanceof ItemHouse1InBag){
             check = true;
         }

         assertEquals(expected,myStore.getMoney());
         assertEquals(expectedCheck,check);

    }

    @Test
    public void checkBuyOfHouse2(){
        boolean check = false;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        long money = 10000;

        myBag = new MyBag(0,0,appContext,cityStructures,dirts,myStore);
        myStore = new MyStore(0,0,appContext,myBag,cityStructuresInBag,dirtsInBag,money);
        FireBaseMangament.setPhakeLevel(ItemHouse2InStore.levelRequired);


        myStore.check_is_clicked(myStore.getPosX(),myStore.getPosY());



        myStore.check_is_clicked(myStore.getItemList().getMenuItem()[0].getItemList()[StoreItemList.house2].getPosX(),
                myStore.getItemList().getMenuItem()[0].getItemList()[StoreItemList.house2].getPosY());

        long expected = (long)(money - House2.cost);
        boolean expectedCheck = true;
        if (myBag.getBagList().getCurrentPage().getItemList()[0] instanceof ItemHouse2InBag){
            check = true;
        }

        assertEquals(expected,myStore.getMoney());
        assertEquals(expectedCheck,check);

    }

    @Test
    public void checkBuyOfHouse3(){
        boolean check = false;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        long money = 10000;

        myBag = new MyBag(0,0,appContext,cityStructures,dirts,myStore);
        myStore = new MyStore(0,0,appContext,myBag,cityStructuresInBag,dirtsInBag,money);
        FireBaseMangament.setPhakeLevel(ItemHouse3InStore.levelRequired);


        myStore.check_is_clicked(myStore.getPosX(),myStore.getPosY());



        myStore.check_is_clicked(myStore.getItemList().getMenuItem()[0].getItemList()[StoreItemList.house3].getPosX(),
                myStore.getItemList().getMenuItem()[0].getItemList()[StoreItemList.house3].getPosY());

        long expected = (long)(money - House3.cost);
        boolean expectedCheck = true;
        if (myBag.getBagList().getCurrentPage().getItemList()[0] instanceof ItemHouse3InBag){
            check = true;
        }

        assertEquals(expected,myStore.getMoney());
        assertEquals(expectedCheck,check);

    }

    @Test
    public void checkBuyOfTree1(){

        boolean check = false;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        long money = 10000;

        myBag = new MyBag(0,0,appContext,cityStructures,dirts,myStore);
        myStore = new MyStore(0,0,appContext,myBag,cityStructuresInBag,dirtsInBag,money);
        FireBaseMangament.setPhakeLevel(ItemTree1InStore.levelRequired);


        myStore.check_is_clicked(myStore.getPosX(),myStore.getPosY());
        myStore.check_is_clicked(myStore.getItemList().getNextButtonButton().getPosX(),
                myStore.getItemList().getNextButtonButton().getPosY());

        myStore.check_is_clicked(myStore.getItemList().getMenuItem()[1].getItemList()[StoreItemList.tree1].getPosX(),
                myStore.getItemList().getMenuItem()[1].getItemList()[StoreItemList.tree1].getPosY());

        long expected = (long)(money - Tree1.cost);
        boolean expectedCheck = true;
        if (myBag.getBagList().getCurrentPage().getItemList()[0] instanceof ItemTree1InBag){
            check = true;
        }

        assertEquals(expected,myStore.getMoney());
        assertEquals(expectedCheck,check);

    }

    @Test
    public void checkBuyOfTree2(){

        boolean check = false;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        long money = 10000;

        myBag = new MyBag(0,0,appContext,cityStructures,dirts,myStore);
        myStore = new MyStore(0,0,appContext,myBag,cityStructuresInBag,dirtsInBag,money);

        FireBaseMangament.setPhakeLevel(ItemTree2InStore.levelRequired);

        myStore.check_is_clicked(myStore.getPosX(),myStore.getPosY());
        myStore.check_is_clicked(myStore.getItemList().getNextButtonButton().getPosX(),
                myStore.getItemList().getNextButtonButton().getPosY());

        myStore.check_is_clicked(myStore.getItemList().getMenuItem()[1].getItemList()[StoreItemList.tree2].getPosX(),
                myStore.getItemList().getMenuItem()[1].getItemList()[StoreItemList.tree2].getPosY());

        long expected = (long)(money - Tree2.cost);
        boolean expectedCheck = true;
        if (myBag.getBagList().getCurrentPage().getItemList()[0] instanceof ItemTree2InBag){
            check = true;
        }

        assertEquals(expected,myStore.getMoney());
        assertEquals(expectedCheck,check);

    }

    @Test
    public void checkBuyOfTree3(){

        boolean check = false;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        long money = 10000;

        myBag = new MyBag(0,0,appContext,cityStructures,dirts,myStore);
        myStore = new MyStore(0,0,appContext,myBag,cityStructuresInBag,dirtsInBag,money);
        FireBaseMangament.setPhakeLevel(ItemTree3InStore.levelRequired);


        myStore.check_is_clicked(myStore.getPosX(),myStore.getPosY());
        myStore.check_is_clicked(myStore.getItemList().getNextButtonButton().getPosX(),
                myStore.getItemList().getNextButtonButton().getPosY());

        myStore.check_is_clicked(myStore.getItemList().getMenuItem()[1].getItemList()[StoreItemList.tree3].getPosX(),
                myStore.getItemList().getMenuItem()[1].getItemList()[StoreItemList.tree3].getPosY());

        long expected = (long)(money - Tree3.cost);
        boolean expectedCheck = true;
        if (myBag.getBagList().getCurrentPage().getItemList()[0] instanceof ItemTree3InBag){
            check = true;
        }

        assertEquals(expected,myStore.getMoney());
        assertEquals(expectedCheck,check);

    }

    @Test
    public void checkBuyOfTree4(){

        boolean check = false;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        long money = 10000;

        myBag = new MyBag(0,0,appContext,cityStructures,dirts,myStore);
        myStore = new MyStore(0,0,appContext,myBag,cityStructuresInBag,dirtsInBag,money);
        FireBaseMangament.setPhakeLevel(ItemTree4InStore.levelRequired);


        myStore.check_is_clicked(myStore.getPosX(),myStore.getPosY());
        myStore.check_is_clicked(myStore.getItemList().getNextButtonButton().getPosX(),
                myStore.getItemList().getNextButtonButton().getPosY());
        myStore.check_is_clicked(myStore.getItemList().getNextButtonButton().getPosX(),
                myStore.getItemList().getNextButtonButton().getPosY());

        myStore.check_is_clicked(myStore.getItemList().getMenuItem()[2].getItemList()[StoreItemList.tree4].getPosX(),
                myStore.getItemList().getMenuItem()[2].getItemList()[StoreItemList.tree4].getPosY());

        long expected = (long)(money - Tree4.cost);
        boolean expectedCheck = true;
        if (myBag.getBagList().getCurrentPage().getItemList()[0] instanceof ItemTree4InBag){
            check = true;
        }

        assertEquals(expected,myStore.getMoney());
        assertEquals(expectedCheck,check);

    }


    @Test
    public void checkBuyOfDirt(){

        boolean check = false;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        long money = 10000;

        myBag = new MyBag(0,0,appContext,cityStructures,dirts,myStore);
        myStore = new MyStore(0,0,appContext,myBag,cityStructuresInBag,dirtsInBag,money);
        FireBaseMangament.setPhakeLevel(ItemDirt1InStore.levelRequired);


        myStore.check_is_clicked(myStore.getPosX(),myStore.getPosY());
        myStore.check_is_clicked(myStore.getItemList().getNextButtonButton().getPosX(),
                myStore.getItemList().getNextButtonButton().getPosY());
        myStore.check_is_clicked(myStore.getItemList().getNextButtonButton().getPosX(),
                myStore.getItemList().getNextButtonButton().getPosY());

        myStore.check_is_clicked(myStore.getItemList().getMenuItem()[2].getItemList()[StoreItemList.dirt].getPosX(),
                myStore.getItemList().getMenuItem()[2].getItemList()[StoreItemList.dirt].getPosY());

        long expected = (long)(money - Dirt1.cost);
        boolean expectedCheck = true;
        if (myBag.getBagList().getCurrentPage().getItemList()[0] instanceof ItemDirt1InBag){
            check = true;
        }

        assertEquals(expected,myStore.getMoney());
        assertEquals(expectedCheck,check);

    }

    @Test
    public void checkItemClicked(){
        // kiểm tra item có đợc chọn khi ngoài trang đó không
        boolean check = false;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();


        myBag = new MyBag(0,0,appContext,cityStructures,dirts,myStore);
        myStore = new MyStore(0,0,appContext,myBag,cityStructuresInBag,dirtsInBag,10000);



        myStore.check_is_clicked(myStore.getPosX(),myStore.getPosY());

        myStore.check_is_clicked(myStore.getItemList().getMenuItem()[2].getItemList()[StoreItemList.dirt].getPosX(),
                myStore.getItemList().getMenuItem()[2].getItemList()[StoreItemList.dirt].getPosY());

        boolean expectedCheck = false;
        if (myBag.getBagList().getCurrentPage().getItemList()[0] instanceof ItemDirt1InBag){
            check = true;
        }


        assertEquals(expectedCheck,check);
    }


    @Test
    public void checkItemClicked1(){

        //kiểm tra xem khi chưa mở cửa hàng có được mua không
        boolean check = false;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        long money = 10000;

        myBag = new MyBag(0,0,appContext,cityStructures,dirts,myStore);
        myStore = new MyStore(0,0,appContext,myBag,cityStructuresInBag,dirtsInBag,money);




        myStore.check_is_clicked(myStore.getItemList().getNextButtonButton().getPosX(),
                myStore.getItemList().getNextButtonButton().getPosY());
        myStore.check_is_clicked(myStore.getItemList().getNextButtonButton().getPosX(),
                myStore.getItemList().getNextButtonButton().getPosY());

        myStore.check_is_clicked(myStore.getItemList().getMenuItem()[2].getItemList()[StoreItemList.dirt].getPosX(),
                myStore.getItemList().getMenuItem()[2].getItemList()[StoreItemList.dirt].getPosY());

        long expected = (long)(money );
        boolean expectedCheck = false;
        if (myBag.getBagList().getCurrentPage().getItemList()[0] instanceof ItemDirt1InBag){
            check = true;
        }

        assertEquals(expected,myStore.getMoney());
        assertEquals(expectedCheck,check);

    }

    @Test
    public void checkItemClicked2(){

        //kiểm tra xem khi không đủ tiền có được mua không
        boolean check = false;
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        long money = 10;

        myBag = new MyBag(0,0,appContext,cityStructures,dirts,myStore);
        myStore = new MyStore(0,0,appContext,myBag,cityStructuresInBag,dirtsInBag,money);


        myStore.check_is_clicked(myStore.getItemList().getNextButtonButton().getPosX(),
                myStore.getItemList().getNextButtonButton().getPosY());
        myStore.check_is_clicked(myStore.getItemList().getNextButtonButton().getPosX(),
                myStore.getItemList().getNextButtonButton().getPosY());
        FireBaseMangament.setPhakeLevel(ItemDirt1InStore.levelRequired);

        myStore.check_is_clicked(myStore.getItemList().getMenuItem()[2].getItemList()[StoreItemList.dirt].getPosX(),
                myStore.getItemList().getMenuItem()[2].getItemList()[StoreItemList.dirt].getPosY());

        long expected = (long)(money );
        boolean expectedCheck = false;
        if (myBag.getBagList().getCurrentPage().getItemList()[0] instanceof ItemDirt1InBag){
            check = true;
        }

        assertEquals(expected,myStore.getMoney());
        assertEquals(expectedCheck,check);

    }
}
