package Energy.domain;

import java.util.Set;
import java.util.HashMap;


public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private Inventory roomInv = new Inventory();

    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

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
        String returnString = "Udgange:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public void addToInventory(Item item){
        roomInv.addItem(item);
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    public void printRoomInv() {
        roomInv.printInventory();
    }

    public Inventory getRoomInv() {
        return roomInv;
    }
}

