package com.example.gameinwakingtoearn.Object.MyDesignList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.gameinwakingtoearn.Object.GameObject;
import com.example.gameinwakingtoearn.R;

public class MyListManagement {

     private  int current_page =0;
     private boolean is_quit=false;
     private int quatities_of_page =0;
     private final int Max_page_of_ListManagement ;
     protected Rect background;
     private Paint paint=new Paint();
     private ItemsList[] menuItem;
     private GameObject preButton;
     private GameObject quitButton;
     private GameObject nextButton;
     private final int Distance_between_next_and_pre_button=20;
     public MyListManagement(Context context,float x,float y,int Max_page_of_ListManagement,int MAX_ITEM_IN_A_PAGE,int Max_column, int distant_between_items,
                             int left_bg,int top_bg,int right_bg,int bottom_bg){
          this.Max_page_of_ListManagement=Max_page_of_ListManagement;
          paint.setColor(Color.YELLOW);
          this.background=new Rect(left_bg,top_bg, right_bg,bottom_bg);


          this.menuItem=new ItemsList[this.Max_page_of_ListManagement];
          this.menuItem[quatities_of_page]=new ItemsList(MAX_ITEM_IN_A_PAGE,Max_column,distant_between_items);

          preButton =new GameObject(0,0,context, R.drawable.back_button,0,-20);
          preButton.getImage().setPos(this.background.left + (this.background.right-this.background.left)/2-this.preButton.getImage().getBitmap().getWidth()-Distance_between_next_and_pre_button,
                  background.bottom-preButton.getHeight()-50,
                  this.background.left + (this.background.right-this.background.left)/2-this.Distance_between_next_and_pre_button,
                  background.bottom-50);


          nextButton=new GameObject(0,0,context, R.drawable.next_button,0,0);
          nextButton.getImage().setPos(this.background.left + (this.background.right-this.background.left)/2+this.Distance_between_next_and_pre_button,
                  background.bottom-preButton.getHeight()-50,
                  this.background.left + (this.background.right-this.background.left)/2+nextButton.getImage().getBitmap().getWidth()+this.Distance_between_next_and_pre_button,
                  background.bottom-50);

          quitButton=new GameObject(0,0,context,R.drawable.nosign,0,0);
          quitButton.getImage().setPos(background.right-quitButton.getWidth(),
                  background.top, background.right,background.top+ quitButton.getHeight());

     }
     public void drawText(Canvas canvas,int number){
          Paint textpaint=new Paint();
          textpaint.setColor(Color.GRAY);
          textpaint.setTextSize(50);
          canvas.drawText(String.valueOf(number),
                  this.preButton.getImage().getPos().right+5,
                  this.preButton.getImage().getPos().top+50,textpaint);
     }

     public void draw(Canvas canvas){
          canvas.drawRect(background,paint);
          menuItem[current_page].draw(canvas);
          nextButton.draw(canvas);
          preButton.draw(canvas);
          quitButton.draw(canvas);
          drawText(canvas,menuItem[current_page].getNumber_of_page());
     }
     public void checkIsClicked(float x,float y){

          nextButton.check_is_clicked(x,y);
          if(nextButton.get_is_clicked() && this.current_page <this.quatities_of_page){
               this.current_page++;
          }
          preButton.check_is_clicked(x,y);
          if(preButton.get_is_clicked() && this.current_page >0){
               this.current_page--;
          }

          quitButton.check_is_clicked(x,y);
          if(quitButton.get_is_clicked()){
               this.is_quit=true;
          }

          menuItem[current_page].check_is_clicked(x,y);

     }
     public void addNewItem(AItemInList item, int distant_from_CenterBg){
          if(menuItem[quatities_of_page].getQua_of_item()>=menuItem[quatities_of_page].getMaxItemInAPage()){

               quatities_of_page++;

               menuItem[quatities_of_page] = new ItemsList(menuItem[0].getMaxItemInAPage(),
                       menuItem[0].Max_column,
                       menuItem[0].distant_between_items);
          }

          menuItem[quatities_of_page].addItem(item,this.background.left+10,
                  this.background.bottom-this.background.top,distant_from_CenterBg);
          menuItem[quatities_of_page].setNumber_of_page(quatities_of_page +1);
     }
     public boolean getIs_quit(){
          return this.is_quit;
     }
     public void setIs_quit(boolean b){
          this.is_quit=b;
     }

     public int getQuatities_of_Page(){
          return this.quatities_of_page;
     }
     public ItemsList getCurrentPage(){
          return this.menuItem[current_page];
     }
     public ItemsList getLastPage(){
          return this.menuItem[quatities_of_page];
     }




}
