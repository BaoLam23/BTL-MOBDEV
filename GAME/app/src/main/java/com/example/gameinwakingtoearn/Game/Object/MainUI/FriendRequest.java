package com.example.gameinwakingtoearn.Game.Object.MainUI;

public class FriendRequest {

    private String fromUserId;
    private String toUserId;
    private String status; // "sent", "accepted", "declined"

    public FriendRequest() {}

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FriendRequest(String fromUserId, String toUserId, String status) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.status = status;
    }
}
