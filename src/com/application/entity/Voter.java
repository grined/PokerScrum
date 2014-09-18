package com.application.entity;

import com.corundumstudio.socketio.SocketIOClient;

public class Voter {
    private final boolean isLeader;
    private final SocketIOClient socketIOClient;

    public Voter(boolean isLeader, SocketIOClient socketIOClient) {
        this.isLeader = isLeader;
        this.socketIOClient = socketIOClient;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public SocketIOClient getSocketIOClient() {
        return socketIOClient;
    }
}
