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

import com.example.gameinwakingtoearn.Game.Object.Adapters.FriendItemAdapter;
import com.example.gameinwakingtoearn.Game.Object.Models.Item;
import com.example.gameinwakingtoearn.Game.Object.User.CurrentUser;
import com.example.gameinwakingtoearn.Game.Object.User.User;
import com.example.gameinwakingtoearn.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Friends extends AppCompatActivity implements ItemClickedListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<Item> itemList;
    FriendItemAdapter friendItemAdapter;
    RecyclerView recyclerView;
    Button findFriendBtn;
    Button goToFriendReqBtn;
    ImageButton backBtn;
    User user = CurrentUser.getInstance().getUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        backBtn = findViewById(R.id.backBtnOfFriends);
        findFriendBtn = findViewById(R.id.findFriendBtn);
        goToFriendReqBtn = findViewById(R.id.goToFriendReq);
        recyclerView = findViewById(R.id.recycleView);
        itemList = new ArrayList<>();

        fetchFriendDetails(user.getFriendList());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Authentication.class);
                startActivity(intent);
                finish();
            }
        });

        findFriendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindFriend.class);
                startActivity(intent);
                finish();
            }
        });

        goToFriendReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PendingRequest.class);
                startActivity(intent);
            }
        });
    }
    public void fetchFriendDetails(List<String> friendIds) {

        for (String friendId : friendIds) {
            db.collection("users").document(friendId)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            User friend = documentSnapshot.toObject(User.class);
                            assert friend != null;
                            Item item = new Item(R.drawable.user2, R.drawable.xp_badge,
                                                R.drawable.friends, friend.getUsername(),
                                                String.valueOf(friend.getLevel()));
                            itemList.add(item);

                            if (friendItemAdapter == null) {

                                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                                recyclerView.setLayoutManager(layoutManager);

                                friendItemAdapter = new FriendItemAdapter(itemList);
                                recyclerView.setAdapter(friendItemAdapter);
                                friendItemAdapter.setClickedListener(this);
                            } else {

                                friendItemAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.d("FetchFriends", "Friend document does not exist");
                        }
                    })
                    .addOnFailureListener(e -> {
                        Log.e("FetchFriends", "Error fetching friend details", e);
                    });
        }
    }


    @Override
    public void onClick(View view, int pos) {

    }
}