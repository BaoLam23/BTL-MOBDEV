package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement;


import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement.MyStore;

import java.util.ArrayList;


//mục đích xây dựng interface này để dùng được tính đa hình của java
// bắt buộc mọi đối tượng cụ thể của ItemInBag viết lại hàm này để khi ta xử lý structure được ném ra bới
// mỗi item trong danh sách thì không cần dùng đến if,else mà nó sẽ tự định dạng lại đối tượng cần ném
public interface ItemInBagInterface {
     public void addSymbolStruture();

     //hàm này sinh ra với mục đích để tạo ra các đối tượng structure tương ứng với các loại item để đỡ phải if, else
     public Structure createStructure(float x, float y, ArrayList<Structure> city, ArrayList<Structure> dirt, MyStore store, MyBag bag );
}
