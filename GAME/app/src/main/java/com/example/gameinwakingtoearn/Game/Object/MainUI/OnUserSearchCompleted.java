package com.example.gameinwakingtoearn.Game.Object.MainUI;

import com.example.gameinwakingtoearn.Game.Object.User.User;

public interface OnUserSearchCompleted {
    void onUserFound(User user);

    void onError(Exception e);
}
