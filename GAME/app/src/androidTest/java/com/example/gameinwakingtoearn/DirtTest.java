package com.example.gameinwakingtoearn;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.graphics.Rect;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemInBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Dirt;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DirtTest {


    @Test
    public void testFixOfDirtPos1(){
        //lấy gốc tọa độ là : (60,50)
        //trọng tâm với vị trí (64,56)
        Rect r = new Rect(14,6,114,106);
        Dirt.fixPosOfDirt(r);

        Rect expected = new Rect(60, 50, 160, 150);

        assertEquals(expected.left,r.left);
        assertEquals(expected.right,r.right);
        assertEquals(expected.top,r.top);
        assertEquals(expected.bottom,r.bottom);


    }

    @Test
    public void testFixOfDirtPos2(){

        //lấy gốc tọa độ là : (60,50)
        //trọng tâm với vị trí (152,164)
        Rect r = new Rect(102,114,202,214);
        Dirt.fixPosOfDirt(r);

        Rect expected = new Rect(60, 150, 160, 250);

        assertEquals(expected.left,r.left);
        assertEquals(expected.right,r.right);
        assertEquals(expected.top,r.top);
        assertEquals(expected.bottom,r.bottom);
    }

    @Test
    public void testFixOfDirtPos3(){
        //trọng tâm với vị trí (380,306)
        Rect r = new Rect(330,256,430,356);

        Dirt dirt = new Dirt(r) {
            @Override
            public ItemInBag changeToItemInBag() {
                return null;
            }
        };



        Rect expected = new Rect(300,300,400,400);
        assertEquals(dirt.getTestCase(),expected);

    }

    @Test
    public void testFixOfDirtPos4(){
        //trọng tâm với vị trí (200,200)
        Rect r = new Rect(100,50,300,250);
        Dirt.fixPosOfDirt(r);
        Rect expected = new Rect(200,200,400,400);
        assertEquals(r,expected);

    }

    @Test
    public void testFixOfDirtPos5(){
        //trọng tâm với vị trí (400,567)
        Rect r = new Rect(200,539,1000,595);
        Dirt.fixPosOfDirt(r);
        Rect expected = new Rect(400,500,120,556);
        assertEquals(r,expected);

    }

}
