package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.Game;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.GameObject;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList.AItemInList;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList.MyListManagement;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.MyStore;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class BagList extends MyListManagement {
    private final String label="túi đồ";
    private Paint paint;
    private boolean Was_Structure_Thrown = false;
    private int indexOfItemNeedThrown = -1;

    public static final int id = R.drawable.backdrop;
    public BagList(Context context, float x, float y) {
        super(context, x, y, 2, 40, 4, 3,
                id,50,200, Game.getScreenWidth()-50,Game.getScreenHeight()-200);
        paint=new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
    }
    @Override
    public void draw(Canvas canvas){
         super.draw(canvas);
         canvas.drawText(this.label,(this.background.getPos().left+this.background.getPos().right)/2-50,
                 this.background.getPos().top+paint.getTextSize(),paint);
    }

    @Override
    public void checkIsClicked(float x,float y){
          super.checkIsClicked(x,y);

              AItemInList[] currentPage = this.getCurrentPage().getItemList();
              for (int i = 0; i < this.getCurrentPage().getQua_of_item(); i++) {

                  currentPage[i].check_is_clicked(x, y);


                  if (currentPage[i].get_is_clicked()) {

                    this.Was_Structure_Thrown=true;
                    this.indexOfItemNeedThrown = i;

                      this.setIs_quit(true);
                      break;
                  }

              }

    }



    public void deleteItemInBagFunction(){

        AItemInList[] currentPage = this.getCurrentPage().getItemList();
        //delete item from bag by replace the last item with the item need to be deleted

        //save pos of box and item in box before replace item in currentPage with item in lastPage
        Rect save_Pos_of_box=new Rect(currentPage[indexOfItemNeedThrown].getPos().left,currentPage[indexOfItemNeedThrown].getPos().top,
                currentPage[indexOfItemNeedThrown].getPos().right,currentPage[indexOfItemNeedThrown].getPos().bottom);

        Rect save_Pos_of_item_in_box=new Rect(currentPage[indexOfItemNeedThrown].getItemstored().getPos().left,
                currentPage[indexOfItemNeedThrown].getItemstored().getPos().top,
                currentPage[indexOfItemNeedThrown].getItemstored().getPos().right
                ,currentPage[indexOfItemNeedThrown].getItemstored().getPos().bottom);


        //thay đổi vị trí trỏ của currentPage[indexOfItemNeedThrown] lên item cuối cùng của danh sách
        currentPage[indexOfItemNeedThrown].getImage().setPos(this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1].getPos().left,
                this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1].getPos().top,
                this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1].getPos().right,
                this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1].getPos().bottom);

        currentPage[indexOfItemNeedThrown].getItemstored().getImage().setPos(this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1].getItemstored().getPos().left,
                this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1].getItemstored().getPos().top,
                this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1].getItemstored().getPos().right,
                this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1].getItemstored().getPos().bottom);



       //thay đổi vị trí con trỏ cuối trỏ lên item cần xóa để sau đó đưa nó về null và giảm độ lớn của mảng
        this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1].
                getImage().setPos(save_Pos_of_box.left,
                        save_Pos_of_box.top,
                        save_Pos_of_box.right,
                        save_Pos_of_box.bottom);

        this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1]
                .getItemstored().getImage().setPos(save_Pos_of_item_in_box.left,
                        save_Pos_of_item_in_box.top,
                        save_Pos_of_item_in_box.right,
                        save_Pos_of_item_in_box.bottom);

        currentPage[indexOfItemNeedThrown] = this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1];

        this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1] = null;

        if( this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1] == null){
            Log.e("delete item","ok");
        }

        this.getLastPage().setQua_of_item(this.getLastPage().getQua_of_item() - 1);


    }

    public boolean getWasStrutureThrown(){
        return this.Was_Structure_Thrown;
    }
    public void setWas_Structure_Thrown(boolean b){
        this.Was_Structure_Thrown = b;
    }

    public GameObject throwStrutures(){

        if(indexOfItemNeedThrown < 0){
            return null;
        }
        ItemInBag item = (ItemInBag) this.getCurrentPage().getItemList()[indexOfItemNeedThrown];
         return item.throwStruture();
    }

    public Structure createStructure(float x, float y, ArrayList<Structure> city, ArrayList<Structure> dirt,
                                     MyStore store, MyBag bag){
        if(indexOfItemNeedThrown < 0){
            return null;
        }
        ItemInBag item = (ItemInBag) this.getCurrentPage().getItemList()[indexOfItemNeedThrown];
        return item.createStructure(x,y,city,dirt,store,bag);
    }
}
