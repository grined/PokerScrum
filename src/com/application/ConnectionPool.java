package com.application;

import com.application.entity.Room;
import com.application.entity.Voter;
import com.corundumstudio.socketio.SocketIOClient;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectionPool {
    public static final String ROOM_PREFIX = "room/";
    private final ServerHolder serverHolder;
    private List<Room> rooms;

    public ConnectionPool(ServerHolder serverHolder){
        rooms = new CopyOnWriteArrayList<>();
        this.serverHolder = serverHolder;
    }

    public Room addRoom(String roomId){
        Room room = new Room(roomId);
        rooms.add(room);
        return room;
    }

    public Room findRoomById(String roomId) {
        return rooms.stream().filter(r -> r.getId().equalsIgnoreCase(roomId)).findFirst().orElse(null);
    }

    public void addClient(String roomId, SocketIOClient client){
        Room room = findRoomById(roomId);
        if (room == null){
            room = addRoom(roomId);
        }
        boolean isLeader = room.getVoterList().isEmpty();
        room.addVoter(new Voter(isLeader, client));
        client.joinRoom(ROOM_PREFIX + String.valueOf(roomId));
        serverHolder.sendNewVoterJoin(roomId, room.getVoterList().size());
        if (isLeader){
            serverHolder.sendYouAreLeader(roomId);

        }
    }

    public void disconnectPlayer(SocketIOClient client) {
        Room room = findRoomByClient(client);
        if (room!=null){
            Voter voterBySocket = room.findVoterBySocket(client);
            room.removeVoter(voterBySocket);
            serverHolder.sendNewVoterJoin(room.getId(), room.getVoterList().size());
        }
    }

    public Room findRoomByClient(SocketIOClient client) {
        return rooms.stream()
                    .filter(r -> r.findVoterBySocket(client) != null)
                    .findFirst()
                    .orElse(null);
    }

    public void addMark(SocketIOClient client, double mark) {
        Room roomByClient = findRoomByClient(client);
        if (roomByClient!=null){
            roomByClient.putMark(roomByClient.findVoterBySocket(client), mark);
            serverHolder.sendMarkCounts(roomByClient.getId(), roomByClient.findAllMarks().size());
            if (roomByClient.readyToShow()){
                serverHolder.sendMarks(roomByClient.getId(), roomByClient.findAllMarks());
            }
        }
    }

    public void showAll(SocketIOClient client) {
        Room roomByClient = findRoomByClient(client);
        if (roomByClient != null){
            serverHolder.sendMarks(roomByClient.getId(), roomByClient.findAllMarks());
        }
    }

    public void clearAll(SocketIOClient client) {
        Room roomByClient = findRoomByClient(client);
        if (roomByClient != null){
            roomByClient.clearMarks();
            serverHolder.sendMarkCounts(roomByClient.getId(), roomByClient.findAllMarks().size());
        }
    }
}
