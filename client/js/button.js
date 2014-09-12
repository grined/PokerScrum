"use strict"

var inputList = document.getElementsByTagName("input");
for (var i =0; i<inputList.length; i++){
    var btn = inputList[i];
    if (btn.type == "button"){
        btn.onclick = onClick(btn);
    }
}

function onClick(btn){
    console.info(btn.value)
}