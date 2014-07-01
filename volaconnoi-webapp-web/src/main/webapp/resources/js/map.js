
$(document).ready(function(){
      
    var mapOptions = {
        center: new google.maps.LatLng(44.5403, -78.5463),
        zoom: 5,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
                                  
    var mapCanvas = document.getElementById("map_canvas");
    var map = new google.maps.Map(mapCanvas, mapOptions); 
    
    $("#airportscountries").change(function(){
                
        var country = $("#airportscountries option:selected").text();
            
        $.ajax({
            type: "GET",
            url: "getcitiesbycountry",
            data: {country: country},
            dataType: "json",
            
            success: function(list){
                
                $('#airportscities')
                    .find('option')
                    .remove()
                    .end();
                                
                $(list).each(function(){
                    $('#airportscities')
                    .append($("<option></option>")
                    .attr("value",this.name)
                    .text(this.city + " " + "(" + this.name + ")")); 
                });      
            },
            
            error: function()
            {
                console.log("error");
            }
            
        });
        
    });
    
    $("#airportscities").change(function(){
        
        var airport = $("#airportscities option:selected").val();
        
        $.ajax({
            
            type: "GET",
            url: "getairportbyname",
            data: {name: airport},
            dataType: "json",
            
            success: function(list){
                
                var coordinates = new google.maps.LatLng(list[0].latitude, list[0].longitude);
             
                 var mapOptions = {
                    center: coordinates,
                    zoom: 12,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                
                var map = new google.maps.Map(mapCanvas, mapOptions); 
                
                var marker = new google.maps.Marker({
                    position: coordinates,
                    map: map,
                    title: list[0].name
                });
                
            }
            
        });
        
    });
 
});
 