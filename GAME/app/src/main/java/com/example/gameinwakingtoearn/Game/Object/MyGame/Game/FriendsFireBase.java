package com.example.gameinwakingtoearn.Game.Object.MyGame.Game;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gameinwakingtoearn.Game.Object.MainUI.Login;
import com.example.gameinwakingtoearn.Game.Object.MainUI.visitFriendsCityListener;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemDirt1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouse1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouse2InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemHouse3InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemInBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemTree1InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemTree2InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemTree3InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.BagManagement.ItemTree4InBag;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Dirt1;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.House1;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.House2;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.House3;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Tree1;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Tree2;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Tree3;
import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Tree4;
import com.example.gameinwakingtoearn.Game.Object.User.CurrentUser;
import com.example.gameinwakingtoearn.Game.Object.User.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FriendsFireBase {


    public static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static List<String> structureId = new ArrayList<>();

    private static  ArrayList<Structure> structuresList = new ArrayList<>();
    private  static   ArrayList<ItemInBag> bagList = new ArrayList<>();
    private static User friend;
    private static int count = 0;


    public static void createStructureRelyOnName(DocumentSnapshot document, String structureName, Structure structure,
                                                 ArrayList<Structure> structuresList){
        if(!document.getString("name").equals(null)) {
            if (document.getString("name").equals(structureName)) {

                Log.e("add structure at firebasemanagement", "active");
                Log.e("check structure pos at firebasemanagement", structure.getPosX() + " " + structure.getPosY());
                structuresList.add(structure);
            }
        }

    }

    public static void createItemInBagRelyOnName(DocumentSnapshot document, String ItemName, ItemInBag item,
                                                 ArrayList<ItemInBag> itemInBagsList){
        if(document.getString("name").equals(ItemName)){

            Log.e("add item at firebasemanagement","active");
            itemInBagsList.add(item);
        }

    }

    public static void fetchUserData(String id, visitFriendsCityListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("users").document(id);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // Create a user object from the document
                        User user = document.toObject(User.class);
                        friend = user;
                        getUserBuildings(id,listener);


                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }




    public  static void getUserBuildings(String id, visitFriendsCityListener listener){


        db.collection("users").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        // Set the user info to your text views
//                            textView.setText(document.getId());

                        //textView.setText(document.getString("username"));

                        //get structure id from firebase
                        structureId = friend.getBuildings();
                        structuresList.clear();
                        bagList.clear();
                        Log.e("check structure id size in saveUserBuildingsId", structureId.size() + "");

                        //push buildings data into structure list
                        Log.e("check friends list id : ", structureId.size()+" ");
                        for(int i =0 ; i< structureId.size();i++) {
                            Log.e("check structure id :",structureId.get(i));


                            db.collection("buildings").document(structureId.get(i)).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                    if (task.isSuccessful()) {
                                        DocumentSnapshot document = task.getResult();
                                        if (document != null && document.exists()) {

                                            if(document.getBoolean("status")) {
                                                // get structures type and create into map:
                                                Log.e("check document positions : ",document.getLong("x") + " " + document.getLong("y") );
                                                createStructureRelyOnName(document, House1.name, new House1(document.getLong("x"), document.getLong("y"), null, null,null,null,null), structuresList);

                                                createStructureRelyOnName(document, House2.name, new House2(document.getLong("x"), document.getLong("y"), null, null,null,null,null), structuresList);

                                                createStructureRelyOnName(document, House3.name, new House3(document.getLong("x"), document.getLong("y"), null, null,null,null,null), structuresList);

                                                createStructureRelyOnName(document, Tree1.name, new Tree1(document.getLong("x"), document.getLong("y"), null, null,null,null,null), structuresList);

                                                createStructureRelyOnName(document, Tree3.name, new Tree3(document.getLong("x"), document.getLong("y"), null, null,null,null,null), structuresList);

                                                createStructureRelyOnName(document, Tree2.name, new Tree2(document.getLong("x"), document.getLong("y"), null, null,null,null,null), structuresList);

                                                createStructureRelyOnName(document, Tree4.name, new Tree4(document.getLong("x"), document.getLong("y"), null, null,null,null,null), structuresList);

                                                createStructureRelyOnName(document, Dirt1.name, new Dirt1(document.getLong("x"), document.getLong("y"), null, null,null,null,null), structuresList);
                                            } else{
                                                createItemInBagRelyOnName(document,House1.name, new ItemHouse1InBag(document.getLong("x"), document.getLong("y"), null, null,null), bagList);

                                                createItemInBagRelyOnName(document,House2.name, new ItemHouse2InBag(document.getLong("x"), document.getLong("y"), null, null,null), bagList);

                                                createItemInBagRelyOnName(document,House3.name, new ItemHouse3InBag(document.getLong("x"), document.getLong("y"), null, null,null), bagList);

                                                createItemInBagRelyOnName(document,Tree1.name, new ItemTree1InBag(document.getLong("x"), document.getLong("y"), null, null,null), bagList);

                                                createItemInBagRelyOnName(document,Tree2.name, new ItemTree2InBag(document.getLong("x"), document.getLong("y"), null, null,null), bagList);

                                                createItemInBagRelyOnName(document,Tree3.name, new ItemTree3InBag(document.getLong("x"), document.getLong("y"), null, null,null), bagList);

                                                createItemInBagRelyOnName(document,Tree4.name, new ItemTree4InBag(document.getLong("x"), document.getLong("y"), null, null,null), bagList);

                                                createItemInBagRelyOnName(document,Dirt1.name, new ItemDirt1InBag(document.getLong("x"), document.getLong("y"), null, null,null), bagList);


                                            }

                                            if(structuresList.size() + bagList.size() == structureId.size()){
                                                listener.moveIntoFriendCity();
                                            }

                                        } else {
                                            Log.d("buildings", "No such document");
                                        }
                                    } else {
                                        Log.d("buildings", "get failed with ", task.getException());
                                    }
                                }
                            });


                        }





                    } else {
                        Log.d("User In FireBaseManagement", "No such document");
                    }
                } else {
                    Log.d("User In FireBaseManagement", "get failed with ", task.getException());
                }
            }
        });

    }


    public static ArrayList<Structure> getStructuresList(){
        return structuresList;
    }
    public static ArrayList<ItemInBag> getBagList(){
        return bagList;
    }


}
