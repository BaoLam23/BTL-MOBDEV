package com.example.gameinwakingtoearn;


import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList.AItemInList;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.ItemDirt1InStore;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.ItemHouse1InStore;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AItemListTest {
    @Test
    public void checkSizeOfItemStored1(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ItemHouse1InStore itemHouse1InStore = new ItemHouse1InStore(3,4, appContext,null,null,null,null);
        int expectedHeight = ItemHouse1InStore.height - AItemInList.distanceFromBorder*2;
        int expectedWidth = ItemHouse1InStore.width - AItemInList.distanceFromBorder*2;

        assertEquals(expectedHeight,itemHouse1InStore.getItemstored().getImage().getHeight());
        assertEquals(expectedWidth,itemHouse1InStore.getItemstored().getImage().getWidth());
    }

    @Test
    public void checkSizeOfItemStored2(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        ItemDirt1InStore item = new ItemDirt1InStore (3,4, appContext,null,null,null,null);
        int expectedHeight = ItemHouse1InStore.height - AItemInList.distanceFromBorder*2;
        int expectedWidth = ItemHouse1InStore.width - AItemInList.distanceFromBorder*2;

        assertEquals(expectedHeight,item.getItemstored().getImage().getHeight());
        assertEquals(expectedWidth,item.getItemstored().getImage().getWidth());
    }

}
