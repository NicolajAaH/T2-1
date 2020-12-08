package Energy.domain;

import java.util.Set;
import java.util.HashMap;

class Room {
    // ATTRIBUTTER
    private String description;
    private HashMap<String, Room> exits;
    private Inventory roomInv = new Inventory();
    private String name;

    // CONSTRUCTOR
    public Room(String description, String name) {
        this.name = name;
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    // GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public HashMap<String, Room> getExits() {
        return exits;
    }

    public Inventory getRoomInv() {
        return roomInv;
    }

    // METODER
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void addToInventory(Item item) {
        roomInv.addItem(item);
    }

    // String metoder
    public String getLongDescription() {
        String returnString;
        // returnerer rum og udgange
        returnString = " - Du er " + description + " -\n" + getExitString() + "\n\n";
        // tilføjer inventory til string hvis der er noget i den!
        if (!roomInv.isEmpty()) {
            returnString += getInvDescriptionString();
            returnString += roomInv;
        }
        return returnString;
    }

    private String getExitString() // henter mulige udgange (keys i hashmap) og returnerer dem i en streng.
    {
        String returnString = "Udgange:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public String getInvDescriptionString() {
        // overrides ved andre typer rum!
        return "Rummet indeholder:\n";
    }
}

