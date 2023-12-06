package com.example.gameinwakingtoearn.Game.Object.MainUI;

public class FriendRequest {

    private String id;
    private String fromUserId;

    private String fromUserUsername;
    private String fromUserLevel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FriendRequest(String id, String fromUserId, String fromUserUsername, String fromUserLevel, String toUserId, String status) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.fromUserUsername = fromUserUsername;
        this.fromUserLevel = fromUserLevel;
        this.toUserId = toUserId;
        this.status = status;
    }

    public String getFromUserLevel() {
        return fromUserLevel;
    }

    public void setFromUserLevel(String fromUserLevel) {
        this.fromUserLevel = fromUserLevel;
    }

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

    public String getFromUserUsername() {
        return fromUserUsername;
    }

    public void setFromUserUsername(String fromUserUsername) {
        this.fromUserUsername = fromUserUsername;
    }

}