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
import it.volaconnoi.entity.Airport;
import java.lang.reflect.Type;

/**
 *
 * @author Mazzy
 */
public class AirportSerializer implements JsonSerializer<Airport>
{

    @Override
    public JsonElement serialize(Airport a, Type type, JsonSerializationContext jsc)
    {
        JsonObject airport = new JsonObject();
        
        airport.add("name", new JsonPrimitive(a.getName()));
        airport.add("country", new JsonPrimitive(a.getCountry()));
        airport.add("city", new JsonPrimitive(a.getCity()));
        airport.add("latitude", new JsonPrimitive(a.getLatitude()));
        airport.add(("longitude"), new JsonPrimitive(a.getLongitude()));
        
        return airport;
    }
    
}
