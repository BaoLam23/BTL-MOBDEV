package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.widget.Toast;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.CityStructure;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Dirt;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Dirt1;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.Game;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.GameObject;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.OptionsBar.MoveOption;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.MyStore;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class MyBag extends GameObject {
    private BagList itemlist;

    private ArrayList<Structure> mycity;
    private ArrayList<Structure> myDirt;

    private GameObject structureSymbol = null;
    private Structure structure;
    private boolean could_build=true;
    private MoveOption option;

    private MyStore myStore;

    public static final  int height = 150;
    public static final  int width = 150;
    public static final int id = R.drawable.inventory_button;
    public  static  final  int posStartOfItem = -200;




    public MyBag(float x, float y, Context context, ArrayList<Structure> mycity, ArrayList<Structure> dirt,
                 MyStore store) {
        super(x, y, context, id, 0, height,width);
        itemlist = new BagList(context, x, y);
        this.option = new MoveOption(x, y, context);
        this.myDirt = dirt;
        this.mycity = mycity;
        this.myStore = store;

    }

public GameObject getStructureSymbol(){
        return this.structureSymbol;
}


    public void draw(Canvas canvas) {
        if (this.is_clicked) {
            itemlist.draw(canvas);
        } else {
            canvas.drawBitmap(this.image.getBitmap(), null, this.image.getPos(), null);

            if (structureSymbol != null) {
                structureSymbol.draw(canvas);
                option.draw(canvas);
            }
        }

    }




    @Override
    public void check_is_clicked(float x, float y) {




        if (x <= this.image.getPos().right && x >= this.image.getPos().left &&
                y <= this.image.getPos().bottom && y >= this.image.getPos().top) {
            this.set_is_clicked(true);
        }


        // xây dựng các thao tác dựa trên các nút của option
        if (structureSymbol != null) {

            int height = structureSymbol.getImage().getPos().bottom - structureSymbol.getImage().getPos().top;
            int width = structureSymbol.getImage().getPos().right - structureSymbol.getImage().getPos().left;
            int top = (int)Structure.fixPosX(structureSymbol.getImage().getPos().top,height);
            int bottom = (int)Structure.fixPosX(structureSymbol.getImage().getPos().bottom,height);
            int left = (int)Structure.fixPosX(structureSymbol.getImage().getPos().left,width);
            int right = (int)Structure.fixPosX(structureSymbol.getImage().getPos().right,width);

            structureSymbol.setPos(left,top);

            this.structureSymbol.check_is_clicked(x, y);
            this.option.getNobutton().check_is_clicked(x,y);
            this.option.getYesbutton().check_is_clicked(x,y);

            if(option.getNobutton().get_is_clicked()){
                structureSymbol = null;

                this.option.getNobutton().set_is_clicked(false);
                return;
            }

            if(option.getYesbutton().get_is_clicked()){

                this.option.getYesbutton().set_is_clicked(false);

               could_build = true;
                this.structure = itemlist.createStructure(structureSymbol.getImage().getPos().left
                        ,structureSymbol.getImage().getPos().top,mycity,myDirt,myStore,this);
                structure.set_is_clicked(false);
                structure.resetDataInOptionsBar();

               if(this.structure instanceof Dirt){
                   Log.e("instanceof Dirt : ","success");
                   for(Structure s : myDirt){
                       if(!Structure.checkCould_builtFunction(s,this.structure)){
                           could_build = false;

                       }
                   }

               } else{
                   for(Structure s : mycity){
                       if(!Structure.checkCould_builtFunction(s,this.structure)){
                           could_build = false;

                       }
                   }
               }
                if(!this.could_build){
                    Toast.makeText(this.getContext(),"không thể đặt tại vị trí này vì nơi này đã được sử dụng", Toast.LENGTH_SHORT).show();
                } else{

                    if(this.structure instanceof Dirt){
                        Dirt.fixPosOfDirt(structure.getPos());

                        this.myDirt.add(structure);
                        this.itemlist.deleteItemInBagFunction();
                        structureSymbol = null;
                        return;

                    }else{
                        if(CityStructure.checkIsLandHere(structure)) {

                            this.mycity.add(structure);
                            this.itemlist.deleteItemInBagFunction();
                            structureSymbol = null;
                            return;
                        } else{
                            Toast.makeText(this.getContext(),"không thể đặt tại vị trí này vì nơi này không có đất", Toast.LENGTH_SHORT).show();
                        }
                    }


                }



            }



        }



        if (this.is_clicked) {
            itemlist.checkIsClicked(x, y);
            if (itemlist.getIs_quit()) {
                this.is_clicked = false;
                itemlist.setIs_quit(false);
            }

            //nếu baglist có item được chọn thì mục tiêu xây dựng của hàm dưới là hiện thị hình ảnh tương ứng
            // với item đó kèm them cái nút lựa chọn :
            // + yes : => tạo structure mới trên bản đồ nếu vị trí đặt nó không có nhà. Nếu đặt thành công cần xóa
            // đối tượng trong baglist
            //+ no : => không làm gì cả, đưa mọi thứ trở về ban đầu

            if (itemlist.getWasStrutureThrown()) {
                this.structureSymbol = itemlist.throwStrutures();
                this.structureSymbol.setPos(500, 500);

                this.itemlist.setWas_Structure_Thrown(false);
            }
        }
    }

    public BagList getBagList() {
        return this.itemlist;

    }

    @Override
    public void update() {
        this.option.set_is_clicked(true);


        if(structureSymbol != null) {
            this.structureSymbol.update();
            this.option.update((int) structureSymbol.getPosX()
                    ,(int) structureSymbol.getPosY()+this.structureSymbol.getImage().getBitmap().getHeight());

        }
    }

    // hàm này sinh ra với vai trò giúp di chuyển được structure theo ý muốn của người dùng
    public void setPosOfStucture(float x, float y) {

        if(this.structureSymbol != null ) {

            if (structureSymbol.get_is_clicked()) {
                this.structureSymbol.setPos(Structure.fixPosX(x,structureSymbol.getImage().getWidth()),
                        Structure.fixPosY(y,structureSymbol.getImage().getHeight()));

            }
        }
    }



}
