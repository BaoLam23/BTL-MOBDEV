package com.example.gameinwakingtoearn.Game.Object.MainUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gameinwakingtoearn.Game.Object.Adapters.FriendItemAdapter;
import com.example.gameinwakingtoearn.Game.Object.Adapters.FriendReqItemAdapter;
import com.example.gameinwakingtoearn.Game.Object.Models.FriendReqItem;
import com.example.gameinwakingtoearn.Game.Object.User.CurrentUser;
import com.example.gameinwakingtoearn.Game.Object.User.User;
import com.example.gameinwakingtoearn.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.List;

public class PendingRequest extends AppCompatActivity implements ItemClickedListener{

    Button backBtn;

    List<FriendReqItem> list;
    RecyclerView recyclerView;
    FriendReqItemAdapter friendReqItemAdapter;
    User user = CurrentUser.getInstance().getUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_request);

        backBtn = findViewById(R.id.backBtn);
        recyclerView = findViewById(R.id.recycleView);
        list = new ArrayList<>();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Friends.class);
                startActivity(intent);
            }
        });

        findSentFriendRequests(user.getUid());
    }

    public void findSentFriendRequests(String currentUserId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("friendRequests")
                .whereEqualTo("toUserId", currentUserId)
                .whereEqualTo("status", "sent")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        //List<FriendRequest> requests = new ArrayList<>();
                        list.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            FriendRequest request = document.toObject(FriendRequest.class);
                            //requests.add(request);
                            FriendReqItem item = new FriendReqItem(request.getId() ,R.drawable.user2,R.drawable.decline, R.drawable.accept, R.drawable.xp_badge, request.getFromUserUsername(), request.getFromUserLevel());
                            list.add(item);
                        }

                        if (friendReqItemAdapter == null) {

                            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                            recyclerView.setLayoutManager(layoutManager);

                            friendReqItemAdapter = new FriendReqItemAdapter(list);
                            recyclerView.setAdapter(friendReqItemAdapter);
                            friendReqItemAdapter.setClickedListener(this);
                        } else {

                            friendReqItemAdapter.notifyDataSetChanged();
                        }

                    } else {
                        Log.d("Friend Request", "Cannot get friend request");
                    }
                });
    }

    @Override
    public void onClick(View view, int pos) {
        Toast.makeText(this, "You click " + list.get(pos).getUsername(), Toast.LENGTH_SHORT).show();
        acceptRequest(list.get(pos).getId());

    }

    public void acceptRequest(String requestId) {

        db.collection("friendRequests").document(requestId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {

                        String fromUserId = documentSnapshot.getString("fromUserId");
                        String toUserId = documentSnapshot.getString("toUserId");

                        user.getFriendList().add(fromUserId);

                        WriteBatch batch = db.batch();

                        DocumentReference fromUserRef = db.collection("users").document(fromUserId);
                        DocumentReference toUserRef = db.collection("users").document(toUserId);

                        batch.update(fromUserRef, "friendList", FieldValue.arrayUnion(toUserId));
                        batch.update(toUserRef, "friendList", FieldValue.arrayUnion(fromUserId));

                        DocumentReference requestRef = db.collection("friendRequests").document(requestId);
                        batch.update(requestRef, "status", "accepted");

                        batch.commit().addOnSuccessListener(aVoid -> {
                            Log.d("AcceptRequest", "Friend request accepted and friend lists updated.");
                        }).addOnFailureListener(e -> {
                            Log.e("AcceptRequest", "Error updating friend lists", e);
                        });

                    } else {
                        Log.d("AcceptRequest", "No such document");
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("AcceptRequest", "Error getting friend request", e);
                });
    }



    public void declineRequest(String requestId) {

        db.collection("friendRequests").document(requestId)
                .update("status", "declined")
                .addOnSuccessListener(aVoid -> {
                    Log.d("DeclineRequest", "Friend request declined successfully.");
                })
                .addOnFailureListener(e -> {
                    Log.e("DeclineRequest", "Error declining friend request", e);
                });
    }


}