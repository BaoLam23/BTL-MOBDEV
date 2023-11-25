package com.example.gameinwakingtoearn.Game.Object.MyGame.Game;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.gameinwakingtoearn.Game.Object.MyGame.Game.CityStructures.Structure;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireBaseMangament {


    private static  FirebaseUser firebaseUser ;
    public static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static long userMoney = -1;



    private static ArrayList<Structure> structuresList = new ArrayList<>();

    public static  void setCurrentUser(FirebaseUser user){
        firebaseUser = user;
    }

    public static void saveUserMoney(long money){

        //save user money
        db.collection("users").document(firebaseUser.getUid())
                .update("money", money)
                .addOnSuccessListener(aVoid -> Log.d("Firestore", "User money updated successfully"))
                .addOnFailureListener(e -> Log.d("Firestore", "Error updating user money", e));
    }

    public static void saveUserStructure(Structure s){
        Map<String, Object> struct = new HashMap<>();
        struct.put("id", s.getId());
        struct.put("cost", s.getCost());
        struct.put("x",s.getPosX());
        struct.put("y",s.getPosY());
        struct.put("status",s.getStatus());


        db.collection("buildings").document(firebaseUser.getUid())
                .set(struct)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                       Log.e("push structs data success full","active");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("push structs data success full"," not active");
                    }
                });

    }

    public static void saveBuildings(ArrayList<Structure> list){

        for(Structure s : list){

        }


    }




   public static void getUserDataFromFireBase(){
       String userId = firebaseUser.getUid();

       Log.e("userid : ",userId);
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



   }

   public static long getUserMoney(){
        return userMoney;
   }

   public static ArrayList<Structure> getStructuresList(){
        return structuresList;
   }
}
