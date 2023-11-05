package com.example.gameinwakingtoearn.Object.BagManagement;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.widget.Toast;

import com.example.gameinwakingtoearn.Game;
import com.example.gameinwakingtoearn.Object.MyDesignList.AItemInList;
import com.example.gameinwakingtoearn.Object.MyDesignList.ItemsList;
import com.example.gameinwakingtoearn.Object.MyDesignList.MyListManagement;

public class BagList extends MyListManagement {
    private final String label="túi đồ";
    private Paint paint;
    public BagList(Context context, float x, float y) {
        super(context, x, y, 2, 40, 5, 3,
                50,100, Game.getScreenWidth()-50,Game.getScreenHeight()-100);
        paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
    }
    @Override
    public void draw(Canvas canvas){
         super.draw(canvas);
         canvas.drawText(this.label,(this.background.left+this.background.right)/2-50,
                 this.background.top+paint.getTextSize(),paint);
    }

    @Override
    public void checkIsClicked(float x,float y){
          super.checkIsClicked(x,y);

              AItemInList[] currentPage = this.getCurrentPage().getItemList();
              for (int i = 0; i < this.getCurrentPage().getQua_of_item(); i++) {

                  currentPage[i].check_is_clicked(x, y);


                  if (currentPage[i].get_is_clicked()) {

                      //delete item from bag by replace the last item with the item need to be deleted

                      //save pos of box and item in box before replace item in currentPage with item in lastPage
                      Rect save_Pos_of_box=new Rect(currentPage[i].getPos().left,currentPage[i].getPos().top,
                              currentPage[i].getPos().right,currentPage[i].getPos().bottom);

                      Rect save_Pos_of_item_in_box=new Rect(currentPage[i].getItemstored().getPos().left,
                              currentPage[i].getItemstored().getPos().top,
                              currentPage[i].getItemstored().getPos().right
                              ,currentPage[i].getItemstored().getPos().bottom);


                      currentPage[i].getImage().setPos(this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1].getPos().left,
                              this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1].getPos().top,
                              this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1].getPos().right,
                              this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1].getPos().bottom);

                      currentPage[i].getItemstored().getImage().setPos(this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1].getItemstored().getPos().left,
                              this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1].getItemstored().getPos().top,
                              this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1].getItemstored().getPos().right,
                              this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1].getItemstored().getPos().bottom);




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

                      currentPage[i] = this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1];

                      this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1] = null;

                      if( this.getLastPage().getItemList()[this.getLastPage().getQua_of_item()-1] == null){
                          Log.e("delete item","ok");
                      }

                      this.getLastPage().setQua_of_item(this.getLastPage().getQua_of_item() - 1);

                      this.setIs_quit(true);
                  }

              }

    }
}
