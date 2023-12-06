package com.example.gameinwakingtoearn.Game.Object.Adapters;

import com.example.gameinwakingtoearn.Game.Object.Models.Item;

public class MessageItem extends Item {
    private String userName;
    private String message;

    public MessageItem(String userName,String message){
        this.userName = userName;
        this.message = message;
    }

    public String getUserName(){
        return  userName;
    }

    public String getMessage(){
        return message;
    }
}
