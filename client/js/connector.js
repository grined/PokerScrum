"use strict";

window.net = {};

window.net.Connector = function (url) {

    this.socket = io.connect(url || '/battle/connector' || 'ws://localhost:8085',
        {transports: ['websocket'], 'reconnection delay': 2000});
};
