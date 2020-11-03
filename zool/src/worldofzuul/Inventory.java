package worldofzuul;

import java.util.ArrayList;

public class Inventory {
    private int MaxSizeInv;
    private ArrayList<Item> items = new ArrayList<>();

    public Inventory(){
    }

    public void addItem(Item item) {
            items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void printInventory() {
        System.out.println("Inventory:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(i+1 + ". " + items.get(i).toString());
        }
    }

    public Item getItem(int i) {
        return items.get(i);
    }
}