package com.example.gameinwakingtoearn.Game.Object.MainUI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gameinwakingtoearn.Game.Object.Adapters.MessageItem;
import com.example.gameinwakingtoearn.Game.Object.Adapters.UserMessageAdapter;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class FriendMessage extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.friends_fragment, container, false);


        return rootView;
    }
}
