package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList;

import android.graphics.Canvas;
import android.util.Log;

// xây dựng một danh sách trong dãy các danh sách

public class ItemsList {
    private final int MAX_ITEM_IN_A_PAGE;
    protected final int Max_column;
    protected final int distant_between_items;

    protected int qua_of_item=0;
    private AItemInList[] mylist;

    private int number_of_page=0;
    public ItemsList(int MAX_ITEM_IN_A_PAGE,int Max_column, int distant_between_items){

        this.MAX_ITEM_IN_A_PAGE=MAX_ITEM_IN_A_PAGE;
        this.Max_column=Max_column;
        this.distant_between_items=distant_between_items;
        mylist=new AItemInList[this.MAX_ITEM_IN_A_PAGE];

    }

    public void draw(Canvas canvas){

        for(int i=0;i<qua_of_item;i++){
            mylist[i].draw(canvas);
        }
    }
    public int getMaxItemInAPage(){
        return  MAX_ITEM_IN_A_PAGE;
    }
    public void setNumber_of_page(int n){
        this.number_of_page=n;
    }
    public int getNumber_of_page(){
        return this.number_of_page;
    }


    public int getQua_of_item(){
        return this.qua_of_item;
    }
    public void setQua_of_item(int q){
        this.qua_of_item=q;
    }
    public void addItem(AItemInList item,float x,float y, int distant_from_CenterBg){

        if(this.qua_of_item<MAX_ITEM_IN_A_PAGE) {
            if (item != null && item.getImage() != null && item.getContext() != null) {

            // thuật toán xây dựng cơ chế set vij trí cho từng item của list : thay vì dùng if,else => dùng quy tắc hash thông qua phép % hoặc / tùy cơ ứng biến

                int leftItem = (int) (x + 20 + (qua_of_item % Max_column) * (item.getImage().getWidth() + this.distant_between_items ));
                int topItem = (int) (y / 2) + (item.getImage().getBitmap().getHeight() + 3) * (qua_of_item / Max_column) + distant_from_CenterBg;
                int rightItem = leftItem + item.getImage().getWidth();
                int bottomItem = topItem + item.getImage().getHeight();

                item.getImage().setPos(leftItem, topItem, rightItem, bottomItem);

            //set vị trí cho thành phần bên trong của items
                int leftItemStored = (int) (leftItem + AItemInList.distanceFromBorder );
                int topItemStored = (int) (topItem +  AItemInList.distanceFromBorder ) ;
                int rightItemStored = leftItemStored + item.getItemstored().getImage().getWidth();
                int bottomItemStored = topItemStored + item.getItemstored().getImage().getHeight();

            item.getItemstored().getImage().setPos(leftItemStored, topItemStored, rightItemStored, bottomItemStored);


            this.mylist[this.qua_of_item] = item;
            this.qua_of_item++;
          }
        }

    }
    public AItemInList[] getItemList(){
        return this.mylist;
    }

    public void check_is_clicked(float x,float y){

        for(int i=0;i<qua_of_item;i++){
            mylist[i].check_is_clicked(x,y);
        }
    }


}
