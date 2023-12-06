package com.example.gameinwakingtoearn.Game.Object.MainUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

    ImageButton backBtn;

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
                Intent intent = new Intent(PendingRequest.this,Friends.class);
                startActivity(intent);
                finish();
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
        //acceptRequest(list.get(pos).getId());

    }



}