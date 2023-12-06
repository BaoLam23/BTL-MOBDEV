package com.example.gameinwakingtoearn;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.GameObject;
import com.example.gameinwakingtoearn.R;

import org.junit.Test;

public class GameObjectTestCase {

    @Test
    public void testContext(){
        boolean result = true;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        if(context == null){
            result = false;
        }
        boolean expected = true;
        assertEquals(expected,result);
    }

    @Test
    public void testContextOfGameObject(){
        boolean result = true;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        GameObject gameObject = new GameObject(0,0,context,0,0,50,50);
        if(gameObject.getContext() == null){
            result = false;
        }
        boolean expected = true;
        assertEquals(expected,result);
    }

    @Test
    public void testGetImageGameObject(){
        boolean result = true;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        GameObject gameObject = new GameObject(0,0,context,R.drawable.sell_button,0,50,50);
        if(gameObject.getImage() == null){
            result = false;
        }
        boolean expected = true;
        assertEquals(expected,result);
    }
    @Test
    public void testHeightWidthImage(){
        int height = 100;
        int width = 200;
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        GameObject gameObject = new GameObject(0,0,context,R.drawable.sell_button,0,height,width);
        assertEquals(height,gameObject.getImage().getHeight());
        assertEquals(width,gameObject.getImage().getWidth());
    }
    @Test
    public void testSetPos1(){
        float x = 3;
        float y = 5;

        GameObject gameObject = new GameObject(x,y,null,0,0,0,0);
        assertEquals(x,gameObject.getPosX(),0) ;
        assertEquals(y,gameObject.getPosY(),0);

    }

    @Test
    public void testSetPos2(){
        float x = 10;
        float y = 56;

        GameObject gameObject = new GameObject(0,0,null,0,0,0,0);
        gameObject.setPos(x,y);

        float expectedX = 0;
        float expectedY = 0;
        assertEquals(expectedX,gameObject.getPosX(),0) ;
        assertEquals(expectedY,gameObject.getPosY(),0);

    }

    @Test
    public void testSetPos3(){
        float x = 10;
        float y = 56;

        GameObject gameObject = new GameObject(0,0,null,0,0,0,0);
        gameObject.set_is_clicked(true);
        gameObject.setPos(x,y);
        float expectedX = 0;
        float expectedY = 0;

        assertEquals(gameObject.getPosX(),expectedX,0) ;
        assertEquals(gameObject.getPosY(),expectedY,0);

    }

    @Test
    public void testId(){
        int id = R.drawable.back_button;

        GameObject gameObject = new GameObject(0,0,null,id,0,0,0);

        assertEquals(id,gameObject.getId());

    }

    @Test
    public void testHeightWidth(){
        int height = 199;
        int width = 45;

        GameObject gameObject = new GameObject(0,0,null,0,0,height,width);

        assertEquals(height,gameObject.getImage().getHeight());
        assertEquals(width,gameObject.getImage().getWidth());

    }

    @Test
    public void testCheckIsClicked(){

        //test trong phạm vi
        float x = 35f;
        float y = 78f;

        int height = 199;
        int width = 45;

        GameObject gameObject = new GameObject(0,0,null,0,0,height,width);
        gameObject.check_is_clicked(x,y);
        boolean expected = true;
        assertEquals(expected,gameObject.get_is_clicked());

    }

    @Test
    public void testCheckIsClicked2(){

        //test ngoài phạm vi
        float x = 250f;
        float y = 780f;

        int height = 700;
        int width = 300;

        GameObject gameObject = new GameObject(0,0,null,0,0,height,width);
        gameObject.check_is_clicked(x,y);
        boolean expected = false;
        assertEquals(expected,gameObject.get_is_clicked());

    }

    @Test
    public void testCheckIsClicked3(){

        //test ngoài phạm vi
        float x = 0f;
        float y = 0f;

        int height = 700;
        int width = 300;

        GameObject gameObject = new GameObject(100,200,null,0,0,height,width);
        gameObject.check_is_clicked(x,y);
        boolean expected = false;
        assertEquals(expected,gameObject.get_is_clicked());

    }

    @Test
    public void testCheckIsClicked4(){
        //test rìa phạm vi
        float x = 0f;
        float y = 0f;

        int height = 200;
        int width = 300;

        GameObject gameObject = new GameObject(0,0,null,0,0,height,width);
        gameObject.check_is_clicked(x,y);
        boolean expected = true;
        assertEquals(expected,gameObject.get_is_clicked());

    }

    @Test
    public void testCheckIsClicked5(){

        //test rìa phạm vi
        float x = 100f;
        float y = 0f;

        int height = 200;
        int width = 300;

        GameObject gameObject = new GameObject(0,0,null,0,0,height,width);
        gameObject.check_is_clicked(x,y);
        boolean expected = true;
        assertEquals(expected,gameObject.get_is_clicked());

    }

}