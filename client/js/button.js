"use strict"

var selected = -1;
var canSelect = true;

function updateButtons() {
    var inputs = document.getElementsByTagName("input")
    for (var i = 0; i < inputs.length; i++) {
        if (inputs[i].value == selected) {
            inputs[i].classList.add("round-button-checked");
        } else {
            inputs[i].classList.remove("round-button-checked");
        }
    }
}

function onClick(data){
    console.info(data)
    if (!canSelect) return;
    selected = data;
    updateButtons();
    window.connector.socket.emit("Mark", {'mark': data})
}

function connectToRoom(){
    var input = document.getElementById("roomInput");
    window.connector.socket.emit("ConnectToRoom", {'roomId': input.value})
    document.getElementById("roomContainer").hidden = true;
    document.getElementById("selectorContainer").hidden = false;
    document.getElementById("resultContainer").hidden = false;
    document.getElementById("roomCaption").textContent = "Room : " + input.value;
}

function showAll(){
    window.connector.socket.emit("ShowAll", {})
}

function clearAll(){
    window.connector.socket.emit("ClearAll", {})
}