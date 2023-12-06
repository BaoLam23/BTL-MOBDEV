package com.example.gameinwakingtoearn.Game.Object.MainUI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyPaperAdapter extends FragmentPagerAdapter {
    public static final int globalMessage = 0;
    public static final int friendMessage = 1;

    private final int quantitiesFrame = 2;
    public MyPaperAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case globalMessage :
                return new GlobalFragment();
            case friendMessage:
                return new FriendMessage();


        }
        return null;
    }

    @Override
    public int getCount() {
        return quantitiesFrame;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        // Tiêu đề của từng tab
        switch (position) {
            case globalMessage:
                return "Global";

            default:
                return "Friends";
        }
    }
}
