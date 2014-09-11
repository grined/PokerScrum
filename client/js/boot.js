"use strict"
function boot(){
    window.connector = new net.Connector("ws://" + document.domain + ":8085/");
    console.info("boot success");
};
