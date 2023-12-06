package com.example.gameinwakingtoearn.Game.Object.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gameinwakingtoearn.Game.Object.Models.Item;
import com.example.gameinwakingtoearn.R;

import java.util.ArrayList;

public class UserMessageAdapter extends ArrayAdapter<MessageItem> {
    private ArrayList<MessageItem> itemList;
    private Context context;
    private TextView userName;
    private TextView message;

    public UserMessageAdapter(Context context, ArrayList<MessageItem> itemList) {
        super(context, 0, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_style, parent, false);
        }

        // Lấy thông tin của item ở vị trí position
        MessageItem currentItem = getItem(position);


        userName = convertView.findViewById(R.id.usernameChat);
        message = convertView.findViewById(R.id.message);

        // Đặt thông tin cho các thành phần
        if (currentItem != null) {
            userName.setText(currentItem.getUserName());
            message.setText(currentItem.getMessage());
        }

        return convertView;
    }
}