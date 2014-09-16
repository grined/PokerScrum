package com.application.entity;

public class Voter {
    private boolean isLeader;

    public Voter(boolean isLeader) {
        this.isLeader = isLeader;
    }

    public Voter() {
        this(false);
    }

    public boolean isLeader() {
        return isLeader;
    }
}
