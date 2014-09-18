"use strict";

window.net = {};
var votersCount = 0;
var marksCount = 0;
var marks = [];

window.net.Connector = function (url) {
    console.info(url);
    this.socket = io.connect(url || 'ws://localhost:8085',
        {transports: ['websocket'], 'reconnection delay': 2000});

    this.socket.on("UpdateVoters", function (data){
        console.log("updateVoters " + data.votersCount);
        votersCount = data.votersCount;
        updateResultPanel(false);

    });

    this.socket.on("MarksCount", function (data){
        console.log("MarksCount " + data.marksCount);
        marksCount = data.marksCount;
        updateResultPanel(false);
    });

    this.socket.on("YouAreLeader", function (data){
        console.log("YouAreLeader ");
        document.getElementById("toolbar").hidden = false;
    });

    this.socket.on("MarksMsg", function (data){
        console.log("MarksMsg " + data.allMarks);
        marks = data.allMarks;
        canSelect = false;
        selected = -1;
        updateResultPanel(true);
    });
};

function updateResultPanel(showMarks){
    var resultContainer = document.getElementById('resultContainer');
    while (resultContainer.hasChildNodes()) {
        resultContainer.removeChild(resultContainer.lastChild);
    }
    for (var i =0; i< votersCount; i++){
        var newDiv = document.createElement('div');
        newDiv.classList.add('card');
        if (showMarks) {
            newDiv.textContent = marks[i];
        } else if (i<marksCount) {
            newDiv.textContent = "ok";
        } else {
            newDiv.textContent = "-";
            canSelect = true;
            updateButtons();
        }
        resultContainer.appendChild(newDiv);
    }
}

