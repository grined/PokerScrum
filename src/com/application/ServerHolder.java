package com.application;

import com.application.entity.outgoing.MarksCount;
import com.application.entity.outgoing.MarksMsg;
import com.application.entity.outgoing.UpdateVoters;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;

import java.util.List;

public class ServerHolder {
    private final SocketIOServer server;

    public ServerHolder(){
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname("0.0.0.0");
        config.setPort(8085);
        SocketConfig socketConfig = config.getSocketConfig();
        socketConfig.setReuseAddress(true);
        config.setTransports(Transport.WEBSOCKET);
        server = new SocketIOServer(config);
    }

    public SocketIOServer getServer() {
        return server;
    }

    public void sendNewVoterJoin(long roomId, int size){
        server.getAllClients().stream()
                .filter(c->c.getAllRooms().contains(ConnectionPool.ROOM_PREFIX + String.valueOf(roomId)))
                .forEach(c->c.sendEvent("UpdateVoters", new UpdateVoters(size)));
    }

    public void sendMarkCounts(long roomId, int size){
        server.getAllClients().stream()
                .filter(c->c.getAllRooms().contains(ConnectionPool.ROOM_PREFIX + String.valueOf(roomId)))
                .forEach(c->c.sendEvent("MarksCount", new MarksCount(size)));
    }

    public void sendMarks(Long roomId, List<Double> allMarks) {
        server.getAllClients().stream()
                .filter(c->c.getAllRooms().contains(ConnectionPool.ROOM_PREFIX + String.valueOf(roomId)))
                .forEach(c->c.sendEvent("MarksMsg", new MarksMsg(allMarks)));
    }

    public void sendYouAreLeader(long roomId) {
        server.getAllClients().stream()
                .filter(c->c.getAllRooms().contains(ConnectionPool.ROOM_PREFIX + String.valueOf(roomId)))
                .forEach(c->c.sendEvent("YouAreLeader", new Object()));
    }
}
