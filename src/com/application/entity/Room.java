package com.application.entity;

import com.corundumstudio.socketio.SocketIOClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    private Long id;
    private List<Voter> voterList;
    private Map<Voter,Double> marks;


    public Room(Long id) {
        this.id = id;
        voterList = new ArrayList<>();
        marks = new HashMap<>();
    }

    public List<Double> findAllMarks(){
        return new ArrayList<>(marks.values());
    }

    public void putMark(Voter voter, Double mark){
        marks.put(voter,mark);
    }

    public List<Voter> getVoterList() {
        return voterList;
    }

    public void setVoterList(List<Voter> voterList) {
        this.voterList = voterList;
    }

    public void addVoter(Voter voter){
        voterList.add(voter);
    }

    public void removeVoter(Voter voter){
        voterList.remove(voter);
        marks.remove(voter);
    }

    public Voter findVoterBySocket(SocketIOClient client){
        return voterList
                .stream()
                .filter(v -> v.getSocketIOClient() == client)
                .findAny()
                .orElse(null);
    }

    public Long getId() {
        return id;
    }

    public boolean readyToShow() {
        return findAllMarks().size() == voterList.size();
    }

    public void clearMarks() {
        marks = new HashMap<>();
    }
}
