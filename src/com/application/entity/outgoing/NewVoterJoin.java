package com.application.entity.outgoing;

public class NewVoterJoin {
    private long votersCount;

    public NewVoterJoin() {
    }

    public NewVoterJoin(long votersCount) {
        this.votersCount = votersCount;
    }

    public long getVotersCount() {
        return votersCount;
    }

    public void setVotersCount(long votersCount) {
        this.votersCount = votersCount;
    }
}
