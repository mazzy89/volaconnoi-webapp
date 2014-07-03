/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.volaconnoi.miscellaneous;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import it.volaconnoi.entity.Route;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Mazzy
 */
public class RouteSerializer implements JsonSerializer<Route>
{
    @Override
    public JsonElement serialize(Route r, Type type, JsonSerializationContext jsc)
    {
        JsonObject route = new JsonObject();
        
        route.add("airlane", new JsonPrimitive(r.getAirlane()));
        route.add("airport_city_source", new JsonPrimitive(r.getAirport_city_source().getName()));
        route.add("airport_city_dest", new JsonPrimitive(r.getAirport_city_dest().getName()));
        route.add("departure_date", new JsonPrimitive(dateToString(r.getDeparture_date())));
        route.add("arrival_date", new JsonPrimitive(dateToString(r.getArrival_date())));
        
        return route;
    }
    
    private String dateToString(Date d)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        return sdf.format(d);
        
    }


}
