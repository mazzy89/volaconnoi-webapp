/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var start_price;
var start_final_price;

$(document).ready(function(){
   
   if($("#price") !== null)
   {
       start_price = $("#price").text();
   }
   
   if($("#price_final") !== null)
   {
       start_final_price = $("#price_final").text();
   }
    
});

$('#passengers, #luggages').change(function(){
		
    var passengers = $("#passengers").val();
    var luggages = $("#luggages option:selected").text();

    $.getJSON('pricer', {passengers: passengers, luggages: luggages, start_price: start_price}, function(data){

        $("#price").html(parseFloat(data).toFixed(2));

    });      
});

$('#points').change(function(){
    
    var price = $("#price_final").text();
    var points = $("#points").val();
           
    $.getJSON('fidelity', {start_final_price: price, points: points}, function(data){
        
        $("#price_final").html((parseFloat(data).toFixed(2)));
        
    });
    
});
