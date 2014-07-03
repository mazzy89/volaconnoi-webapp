/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$("#checkinRow").click(function(e){
    
    var reservId = $("#reservid").text();
    
    console.log(reservId);
    
    $.get("CheckinConfirm", {reservId: reservId}, function(){
       
        $("#successCheckin").addClass("messageOnTop")
                            .append("Il Check-in relativo alla prenotazione " + reservId +  " \350 stato effettuato!");
        
    });
    
});
