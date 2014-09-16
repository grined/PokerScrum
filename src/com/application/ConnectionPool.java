package com.application;

import com.application.entity.Room;
import com.application.entity.Voter;
import com.corundumstudio.socketio.SocketIOClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionPool {
    private Map<Room, Map<Voter,SocketIOClient>> connectionPool;
    public ConnectionPool(){
        connectionPool = new ConcurrentHashMap<>();
    }

    public void addRoom(long roomId){
        Room room = new Room(roomId);
        connectionPool.put(room, new ConcurrentHashMap<>());
    }

    public Room findRoomById(long roomId) {
        return connectionPool.keySet()
                .stream()
                .filter(r -> r.getId() == roomId)
                .findFirst()
                .orElse(null);
    }


    public void addClient(long roomId, boolean isLeader, SocketIOClient client){
        Room room = findRoomById(roomId);
        if (connectionPool.containsKey(room)) {
            connectionPool.get(room).put(new Voter(isLeader), client);
        }
    }
}
