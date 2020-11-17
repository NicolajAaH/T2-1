package Energy;

import java.util.Set;
import java.util.HashMap;


public class Room
{
    // Attributes
    private String description;
    private HashMap<String, Room> exits;
    private Inventory roomInv = new Inventory();

    // Constructor
    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    // Methods
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    public void addToInventory(Item item){
        roomInv.addItem(item);
    }

    public Inventory getRoomInv() {
        return roomInv;
    }

    // String methods
    public String getLongDescription()
    {
        String returnString;
        returnString = " - Du er " + description + " -\n" + getExitString()+"\n";
        if (!roomInv.isEmpty()) {
            returnString += getInvDescriptionString();
            returnString += getRoomInvString();
        }

        return returnString;
    }

    public String getShortDescription()
    {
        return description;
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

    public String getRoomInvString() {
        return roomInv.printInventory();
    }

    public String getInvDescriptionString() {
        return "Rummet indeholder:\n";
    }
}

