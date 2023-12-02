package com.example.gameinwakingtoearn.Game.Object.MainUI;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gameinwakingtoearn.Game.Object.Adapters.FriendItemAdapter;
import com.example.gameinwakingtoearn.Game.Object.Models.Item;
import com.example.gameinwakingtoearn.Game.Object.User.User;
import com.example.gameinwakingtoearn.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FindFriend extends AppCompatActivity implements ItemClickedListener{

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button backBtn;
    List<User> users = new ArrayList<>();
    RecyclerView recyclerView;
    List<Item> itemList;
    FriendItemAdapter friendItemAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);

        backBtn = findViewById(R.id.backBtn);
        recyclerView = findViewById(R.id.recycleView);
        itemList = new ArrayList<>();
        searchView = findViewById(R.id.searchBar);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // User pressed the search button
                searchForUser(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Search text has changed
                return true;
            }
        });

//        Item item1 = new Item(R.drawable.house_1, "Hieu", "123");
//        Item item2 = new Item(R.drawable.house_1, "JJJ", "123");
//        Item item3 = new Item(R.drawable.house_1, "MMM", "123");

//        itemList.add(item1);
//        itemList.add(item2);
//        itemList.add(item3);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        friendItemAdapter = new FriendItemAdapter(itemList);
//        recyclerView.setAdapter(friendItemAdapter);
//
//        friendItemAdapter.setClickedListener(this);

        Log.d("Check", String.valueOf(itemList.size()));

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Friends.class);
                startActivity(intent);
            }
        });
    }

    public void searchForUser(String username) {
        db.collection("users")
                .whereEqualTo("username", username)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        itemList.clear();

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            User user = document.toObject(User.class);
                            Item item = new Item(R.drawable.house_1, user.getUsername(), String.valueOf(user.getLevel()));
                            itemList.add(item);
                        }

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
                        Log.d("find user", "not found");
                    }
                });
    }


    public void sendFriendRequest(String fromUserId, String toUserId) {
        FriendRequest request = new FriendRequest(fromUserId, toUserId, "sent");

        db.collection("friendRequests")
                .add(request)
                .addOnSuccessListener(documentReference -> {
                    Log.d("FriendRequest", "Friend request sent successfully.");
                })
                .addOnFailureListener(e -> {
                    Log.w("FriendRequest", "Error sending friend request", e);
                });
    }
    public void listenForFriendRequests(String currentUserId) {
        db.collection("friendRequests")
                .whereEqualTo("toUserId", currentUserId)
                .whereEqualTo("status", "sent")
                .addSnapshotListener((snapshots, e) -> {
                    if (e != null) {
                        Log.w("FriendRequest", "Listen failed.", e);
                        return;
                    }

                    for (DocumentChange dc : snapshots.getDocumentChanges()) {
                        if (dc.getType() == DocumentChange.Type.ADDED) {
                            FriendRequest request = dc.getDocument().toObject(FriendRequest.class);
                        }
                    }
                });
    }

    @Override
    public void onClick(View view, int pos) {
        Toast.makeText(this, "you choose: " + itemList.get(pos).getUsername(), Toast.LENGTH_SHORT).show();
    }
}
