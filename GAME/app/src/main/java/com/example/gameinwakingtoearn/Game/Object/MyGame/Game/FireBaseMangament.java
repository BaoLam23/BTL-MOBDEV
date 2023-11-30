package com.example.gameinwakingtoearn.Game.Object.MyGame.Game;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireBaseMangament {


    private static  FirebaseUser firebaseUser ;
    public static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static long userMoney = -1;

    private static List<String> structureId = new ArrayList<>();

    private static  ArrayList<Structure> structuresList = new ArrayList<>();
    private  static   ArrayList<ItemInBag> bagList = new ArrayList<>();


    public static  void setCurrentUser(FirebaseUser user){
        firebaseUser = user;
    }

    public static void deleteBuilding(Structure s) {


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        int index = -1;
        String buildingId = "";
        for(int i=0;i<structuresList.size();i++){
            if(structuresList.get(i) == s){
                index = i;

                Log.e("building id : ", buildingId);
                break;
            }
        }

        if(index != -1){
            buildingId = structureId.get(index);
            structureId.remove(index);
        }

        if(!buildingId .equals("") && index != -1) {

            DocumentReference userDocRef = db.collection("users").document(firebaseUser.getUid());

            DocumentReference buildingDocRef = db.collection("buildings").document(buildingId);

            WriteBatch batch = db.batch();

            batch.delete(buildingDocRef);


            batch.update(userDocRef, "buildings", FieldValue.arrayRemove(buildingId));

            batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.d("Firestore", "Building successfully deleted from both collections");
                    } else {
                        Log.e("Firestore", "Error deleting building", task.getException());
                    }
                }
            });
        }
    }

    public static void saveUserMoney(long money){

        //save user money
        userMoney = money;
        db.collection("users").document(firebaseUser.getUid())
                .update("money", money)
                .addOnSuccessListener(aVoid -> Log.d("Firestore", "User money updated successfully"))
                .addOnFailureListener(e -> Log.d("Firestore", "Error updating user money", e));
    }

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




    public  static void getUserBuildings(){


        db.collection("users").document(firebaseUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        // Set the user info to your text views
//                            textView.setText(document.getId());
                        User user = CurrentUser.getInstance().getUser();
                        //textView.setText(document.getString("username"));

                        //get structure id from firebase
                        structureId = user.getBuildings();
                        structuresList.clear();
                        bagList.clear();
                        Log.e("check structure id size in saveUserBuildingsId", structureId.size() + "");

                        //push buildings data into structure list
                        for(int i =0 ; i< structureId.size();i++) {
                            Log.e("check structure id :",structureId.get(i));


                            //bug in here
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

    public static  void saveUserBuildings(ArrayList<Structure> s){



        structuresList.clear();

        bagList.clear();
        for(int i=0;i<s.size();i++) {
            Structure newStructure = s.get(i);
            Log.e("check bulidings input at fireBaseManagement",newStructure.getName());
            if(s.get(i).getStatus()) {
                structuresList.add(s.get(i));
            } else{
                bagList.add(s.get(i).changeToItemInBag());
            }

            Map<String, Object> newBuldings = new HashMap<>();
            newBuldings.put("cost", newStructure.getCost());
            newBuldings.put("x", newStructure.getPosX());
            newBuldings.put("y", newStructure.getPosY());
            newBuldings.put("name", newStructure.getName());
            newBuldings.put("id", newStructure.getId());
            newBuldings.put("status", newStructure.getStatus());


            db.collection("buildings").document(structureId.get(i))
                    .update(newBuldings)
                    .addOnSuccessListener(aVoid -> Log.d("Firestore", "Building updated successfully"))
                    .addOnFailureListener(e -> Log.d("Firestore", "Error updating Building", e));
        }

    }
    public  static void CreateNewUserBuilding( Context context){
        User currentUser = CurrentUser.getInstance().getUser();

            Building newBuilding = new Building();
            newBuilding.setStatus(false);

            WriteBatch batch = db.batch();

            // Add the new building to the buildings collection
            DocumentReference newBuildingRef = db.collection("buildings").document();
            batch.set(newBuildingRef, newBuilding);

            // Update the user's building array
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            if (firebaseUser != null) {
                DocumentReference userRef = db.collection("users").document(firebaseUser.getUid());

                batch.update(userRef, "buildings", FieldValue.arrayUnion(newBuildingRef.getId()));

            }

            // Commit the batch write
            batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {



                        List<String> updatedBuildings = currentUser.getBuildings();
                        updatedBuildings.add(newBuildingRef.getId());

                        currentUser.setBuildings(updatedBuildings);


                        CurrentUser.getInstance().setUser(currentUser);
                    } else {
                        Toast.makeText(context.getApplicationContext(), "Transaction failure", Toast.LENGTH_SHORT).show();

                    }
                }
            });



    }

    public static void updateUserData(){
        // Check if there is a current user logged in before attempting to save.
        if (CurrentUser.getInstance().getUser() != null) {

            User currentUser = CurrentUser.getInstance().getUser();

            Map<String, Object> userUpdates = new HashMap<>();
            userUpdates.put("email", currentUser.getEmail());
            userUpdates.put("username", currentUser.getUsername());
            userUpdates.put("money", userMoney);
            userUpdates.put("totalDistance", currentUser.getTotalDistance());
            userUpdates.put("friendList", currentUser.getFriendList());
            userUpdates.put("buildings", currentUser.getBuildings());
            // Add other fields that you want to update

            // Get the database reference
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            // Update the user document in Firestore
            DocumentReference userDocRef = db.collection("users").document(currentUser.getUid());
            userDocRef.update(userUpdates)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("Firestore", "User data successfully updated on stop!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("Firestore", "Error updating user data on stop", e);
                        }
                    });
        }
    }





   public static void getUserDataFromFireBase(){
       String userId = firebaseUser.getUid();




       db.collection("users").document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
           @Override
           public void onComplete(@NonNull Task<DocumentSnapshot> task) {

               if (task.isSuccessful()) {
                   DocumentSnapshot document = task.getResult();
                   if (document != null && document.exists()) {

                       // get money

                       userMoney = document.getLong("money");




                   } else {
                       Log.d("GameUI", "No such document");
                   }
               } else {
                   Log.d("GameUI", "get failed with ", task.getException());
               }
           }
       });


       getUserBuildings();




   }

   public static long getUserMoney(){
        return userMoney;
   }



   public static ArrayList<Structure> getStructuresList(){
        return structuresList;
   }
    public static ArrayList<ItemInBag> getBagList(){
        return bagList;
    }

}
