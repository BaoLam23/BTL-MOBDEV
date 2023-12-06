package com.example.gameinwakingtoearn.Game.Object.MyGame.Game;

import android.content.Context;
import android.util.Log;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Dirt;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;

public class FriendCity extends Game{
    public FriendCity(Context context, long moneyOfUser, boolean checkPermission, GameSurfaceViewListener game) {
        super(context, moneyOfUser, checkPermission, game);
    }

    @Override
    public void pushDataFromFireBaseIntoGame() {
        Log.e("check friends city size : ", FriendsFireBase.getStructuresList().size()+ "");

        for (Structure s : FriendsFireBase.getStructuresList()) {
            s.setMycity(this.mycity);
            s.setContext(this.context);
            s.setMyDirt(myDirt);
            s.setBag(this.mybag);
            s.setMyStore(this.myStore);

            if (s instanceof Dirt) {
                myDirt.add(s);
                Log.e("add dirt", "active");
            } else {
                mycity.add(s);
            }

        }
    }
}
