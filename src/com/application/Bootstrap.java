package com.application;

import com.application.entity.incoming.ConnectToRoom;
import com.application.entity.incoming.Mark;

public class Bootstrap {
    public static void main(String[] args) {
        final ServerHolder serverHolder = new ServerHolder();
        final ConnectionPool connectionPool = new ConnectionPool(serverHolder);

        serverHolder.getServer().addDisconnectListener(client -> {
            connectionPool.disconnectPlayer(client);
        });

        serverHolder.getServer().addEventListener("Mark", Mark.class, (client, data, ackSender) -> {
            System.out.println("Client set mark = " + data.getMark());
            connectionPool.addMark(client, data.getMark());
        });

        serverHolder.getServer().addEventListener("ShowAll", Object.class, (client, data, ackSender) -> {
            connectionPool.showAll(client);
        });

        serverHolder.getServer().addEventListener("ClearAll", Object.class, (client, data, ackSender) -> {
            connectionPool.clearAll(client);
        });

        serverHolder.getServer().addEventListener("ConnectToRoom", ConnectToRoom.class, (client, data, ackSender) -> {
            System.out.println("Client connected to room = " + data.getRoomId());
                connectionPool.addClient(data.getRoomId(), client);
        });
        serverHolder.getServer().start();
    }


}
