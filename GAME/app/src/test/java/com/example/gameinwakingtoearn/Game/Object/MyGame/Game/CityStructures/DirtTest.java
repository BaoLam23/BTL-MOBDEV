package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures;

import static org.junit.Assert.*;

import android.graphics.Rect;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemInBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.GameObject;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import static org.mockito.Mockito.*;


public class DirtTest {

    @PrepareForTest(Rect.class)

    @Test
    public void testFixOfDirtPos1(){
        //trọng tâm với vị trí (50,50)
        Rect r = new Rect(0,0,100,100);
        PowerMockito.mockStatic(Rect.class);
        try {
            PowerMockito.whenNew(Rect.class).withAnyArguments().thenReturn(r);
            PowerMockito.doNothing().when(r).set(50, 50, 150, 150); // Mock phương thức set
        } catch (Exception e) {
            e.printStackTrace();
        }

        Dirt.fixPosOfDirt(r);

        PowerMockito.verifyStatic(Rect.class);
        r.set(50, 50, 150, 150);


    }

    @Test
    public void testFixOfDirtPos2(){

        //trọng tâm (0,0)

        // Tạo mock object của Rect
        Rect mockedRect = mock(Rect.class);

        // Thiết lập giá trị ban đầu cho mockedRect
        when(mockedRect.left).thenReturn(-50);
        when(mockedRect.right).thenReturn(50);
        when(mockedRect.top).thenReturn(-50);
        when(mockedRect.bottom).thenReturn(50);

        Dirt.fixPosOfDirt(mockedRect);

        Rect expected = new Rect(0, 0, 100, 100);

        // Kiểm tra xem mockedRect có được thay đổi đúng như kỳ vọng hay không
//        assertEquals(expected.left, mockedRect.left);
//        assertEquals(expected.right, mockedRect.right);
//        assertEquals(expected.top, mockedRect.top);
//        assertEquals(expected.bottom, mockedRect.bottom);
        assertEquals(mockedRect,expected);
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