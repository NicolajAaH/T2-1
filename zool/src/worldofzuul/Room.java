package worldofzuul;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private boolean isShop;

    public Room(String description, boolean isShop)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.isShop = isShop;
    }
    // hej med dig
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "Du er " + description + ".\n" + getExitString();
    }

    private String getExitString() // henter mulige udgange (keys i hashmap) og returnerer dem i en streng.
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    public boolean isShop(){
        return isShop;
    }

}

