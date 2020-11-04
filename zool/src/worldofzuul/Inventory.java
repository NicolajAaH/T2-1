package worldofzuul;

import java.util.ArrayList;

public class Inventory {
    private int maxSize;
    private ArrayList<Item> items = new ArrayList<>();

    // Constructors
    public Inventory(){
    }

    public Inventory(int maxSize) {
        this.maxSize = maxSize;
    }

    // Methods
    public void addItem(Item item) {
            items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

// SKAL DEN HER IKKE BARE VÆRE EN TOSTRING??
    public void printInventory() {
        System.out.println("Inventory:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i+1 + ". " + items.get(i).toString());
        }
    }

    public int getSize() {
        return items.size();
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public Item getItem(int i) {
        return items.get(i);
    }
}
