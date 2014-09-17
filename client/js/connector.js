"use strict";

window.net = {};

window.net.Connector = function (url) {
    console.info(url);
    this.socket = io.connect(url || 'ws://localhost:8085',
        {transports: ['websocket'], 'reconnection delay': 2000});

    this.socket.on("NewVoterJoin", function (data){
        console.log("NewVoterJoin" + data);
    });
};

