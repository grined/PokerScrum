"use strict"

var selected = -1;
function onClick(data){
    console.info(data)
    selected = data;
    var inputs = document.getElementsByTagName("input")
    for (var i=0; i<inputs.length ; i++){
        if (inputs[i].value == selected){
            inputs[i].classList.add("round-button-checked");
        } else {
            inputs[i].classList.remove("round-button-checked");
        }
    }
    window.connector.socket.io.emit("mark", {mark : data})
}