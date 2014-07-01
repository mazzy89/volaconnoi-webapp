/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
    
    var airport_source;
    var airport_dest;
    
    $("#airports_source").change(function(){
        
        airport_source = $("#airports_source option:selected").text();
                
        $.ajax({
            
            type: "GET",
            url: "getairportsdest",
            data: {airport: airport_source},
            dataType: "json",
            
            success: function(data){
                
                $('#airports_dest')
                    .find('option')
                    .remove()
                    .end();
            
                $("#airports_dest")
                        .append($("<option>Seleziona un aeroporto di destinazione</option>"));
            
                $(data).each(function(i){
                    $('#airports_dest')
                    .append($("<option></option>")
                    .attr("value",data[i])
                    .text(data[i])); 
                }); 
                
            }
            
        });
        
    });
    
    $("#airports_dest").change(function(){
        
        airport_dest = $("#airports_dest option:selected").text();
                
        $.ajax({
            
            
            type:"GET",
            url:"getroutestimes",
            data: {airport_source: airport_source, airport_dest: airport_dest},
            dataType: "json",
                        
            success: function(data){
                
                $("#result_times")
                    .find("tr")
                    .remove()
                    .end();

                var table = $('#result_times table');

                $.each(data, function(){
                    table.append(
                    $('<tr></tr>').append(
                        $('<td></td>').text(this.airlane),
                        $('<td></td>').text(this.airport_city_source),
                        $('<td></td>').text(this.airport_city_dest),
                        $('<td></td>').text(this.departure_date),
                        $('<td></td>').text(this.arrival_date)
                    )
                    );
                });
             }
            
        });
        
    });
    
    
});