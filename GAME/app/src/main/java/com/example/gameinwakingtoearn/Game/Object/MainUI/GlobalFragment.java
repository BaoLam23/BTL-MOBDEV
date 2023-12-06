package com.example.gameinwakingtoearn.Game.Object.MainUI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gameinwakingtoearn.Game.Object.Adapters.MessageItem;
import com.example.gameinwakingtoearn.Game.Object.Adapters.UserMessageAdapter;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class GlobalFragment extends Fragment {
    private ListView listView ;
    private ArrayList<MessageItem> data ;
    GameMessageManagement gameMessageManagement = new GameMessageManagement();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.global_fragment, container, false);

        ListView listView = rootView.findViewById(R.id.globalMessageList);
        data = new ArrayList<>();
        data.add(new MessageItem("hung","hello bro"));
        data.add(new MessageItem("hieu","goodbye"));
        UserMessageAdapter adapter = new UserMessageAdapter(getContext(), data);

        // Set adapter cho ListView
        listView.setAdapter(adapter);

        return rootView;
    }

}
