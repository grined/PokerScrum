"use strict"

var selected = 0;
function onClick(data){
    console.info(data)
    selected = data;
    var inputs = document.getElementsByTagName("input")
    for (var i=0; i<inputs.length ; i++){
        if (inputs[i].value == selected){
            inputs[i].class = "";//"round-button-checked";
        }
    }
}