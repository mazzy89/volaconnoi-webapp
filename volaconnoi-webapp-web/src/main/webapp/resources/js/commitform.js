/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
    
    $("#commit_form").submit(function(){
        
        $("#commit_body")
                        .remove()
                        .end();
                
        $(document)
                .find(".grid_9")
                .removeClass("grid_9")
                .removeClass("confirmation")
                .addClass("grid_12")
                .addClass("elaboration");
                
        $(document)
                .find(".elaboration")
                .append($("<div></div>")
                .addClass("messageOnTop")
                .text("Attenda prego. Stiamo elaborando la sua prenotazione...")
                );
                
    });
    
});