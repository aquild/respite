package dev.aquild.respite.Serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class LocationSerializer implements JsonSerializer<org.bukkit.Location> {
    @Override
    public JsonElement serialize(org.bukkit.Location src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject serialized = new JsonObject();
        serialized.addProperty("world", src.getWorld().getUID().toString());
        serialized.addProperty("x", src.getX());
        serialized.addProperty("y", src.getY());
        serialized.addProperty("z", src.getZ());

        return serialized;
    }
}
