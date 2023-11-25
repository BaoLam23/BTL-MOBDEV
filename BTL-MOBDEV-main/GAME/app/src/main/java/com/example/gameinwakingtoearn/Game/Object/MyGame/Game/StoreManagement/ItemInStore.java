package com.example.gameinwakingtoearn.Game.Object.MyGame.Game.StoreManagement;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouseInBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.MyBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.Building;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.MyDesignList.AItemInList;
import com.example.gameinwakingtoearn.Game.Object.User.CurrentUser;
import com.example.gameinwakingtoearn.Game.Object.User.User;
import com.example.gameinwakingtoearn.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.List;

public class ItemInStore extends AItemInList {


    private ArrayList<Structure> city;
    private ArrayList<ArrayList<Integer>> area ;
    private MyBag bag;
    private MyStore myStore;
    private final int cost;

    public static  final int height = 100;
    public static  final int width = 100;
    public ItemInStore(float x, float y, Context context, MyBag b, ArrayList<Structure> city, ArrayList<ArrayList<Integer>> area ,int cost,
                       MyStore myStore) {
        super(x, y, context, R.drawable.icon_item_in_myteam, height,width);
        this.bag=b;
        this.city=city;
        this.area=area;
        this.cost =cost;
        this.myStore = myStore;
    }

    @Override
    public void check_is_clicked(float x,float y){

        super.check_is_clicked(x,y);

        User currentUser = CurrentUser.getInstance().getUser();

        if(this.is_clicked){
            //add item to bag
            if(myStore.getMoney() - cost >= 0) {
                Log.e("add new item in bag", "ok");
                bag.getBagList().addNewItem(new ItemHouseInBag(0, 0, context, city, area), -400);
                myStore.setMoney(myStore.getMoney() - cost);

//                Building newBuilding = new Building();
//                double newMoneyValue = currentUser.getMoney() - 100;
//                FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//                WriteBatch batch = db.batch();
//
//                // Add the new building to the buildings collection
//                DocumentReference newBuildingRef = db.collection("buildings").document();
//                batch.set(newBuildingRef, newBuilding);
//
//                // Update the user's building array
//                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//                if (firebaseUser != null) {
//                    DocumentReference userRef = db.collection("users").document(firebaseUser.getUid());
//
//                    batch.update(userRef, "buildings", FieldValue.arrayUnion(newBuildingRef.getId()));
//                    batch.update(userRef, "money", newMoneyValue);
//                }
//
//                // Commit the batch write
//                batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(context.getApplicationContext(), "The building was added and the user was updated", Toast.LENGTH_SHORT).show();
//
//
//                            List<Structure> updatedBuildings = currentUser.getBuildings();
//                            updatedBuildings.add(newBuildingRef.getId());
//
//                            currentUser.setBuildings(updatedBuildings);
//                            currentUser.setMoney(newMoneyValue);
//
//                            CurrentUser.getInstance().setUser(currentUser);
//                        } else {
//                            Toast.makeText(context.getApplicationContext(), "Transaction failure", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });

            } else {
                Toast.makeText(context, "Money is not enough", Toast.LENGTH_SHORT).show();
            }

            this.is_clicked = false;
        }

    }

    public int getCost(){
        return this.cost;
    }
}