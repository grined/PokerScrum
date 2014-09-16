package com.application;

import com.application.entity.Mark;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;

public class Bootstrap {
    public static void main(String[] args) {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname("0.0.0.0");
        config.setPort(8085);
        SocketConfig socketConfig = config.getSocketConfig();
        socketConfig.setReuseAddress(true);
        config.setTransports(Transport.WEBSOCKET);
        final SocketIOServer server = new SocketIOServer(config);
        ConnectionPool connectionPool = new ConnectionPool();
        connectionPool.addRoom(1);
        server.addConnectListener(c -> {
            System.out.println(c.getHandshakeData().getTime());
            connectionPool.addClient(1, false, c);
        });

        server.addEventListener("mark", Mark.class, (client, data, ackSender) -> {
            System.out.println("Client set mark = " + data.getMark());
        });
        server.start();
    }


}
