package com.application.entity.outgoing;

public class UpdateVoters {
    private long votersCount;

    public UpdateVoters() {
    }

    public UpdateVoters(long votersCount) {
        this.votersCount = votersCount;
    }

    public long getVotersCount() {
        return votersCount;
    }

    public void setVotersCount(long votersCount) {
        this.votersCount = votersCount;
    }
}
