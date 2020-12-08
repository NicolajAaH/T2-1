package Energy.domain;

import java.util.ArrayList;

public class Inventory {
    // ATTRIBUTTER
    private int maxSize;
    private ArrayList<Item> items = new ArrayList<>();

    // CONSTRUCTORS
    public Inventory() {
    }

    public Inventory(int maxSize) {
        this.maxSize = maxSize;
    }

    // GETTERS & SETTERS
    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    // METODER
    // - metoder til håndtering af Items
    public Item getItem(int i) {
        return items.get(i);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void replaceItem(int replaceIndex, Item withItem) {
        items.set(replaceIndex, withItem);
    }

    public int cheapestItem() {
        // Returnerer prisen på billigste Item i inventory

        // checker om inventory er tomt
        if (this.items.size() == 0) {
            return 0;
        }
        // finder mindste pris
        int min = this.items.get(0).getPrice();

        for (Item i : items) {
            if (i.getPrice() < min) {
                min = i.getPrice();
            }
        }

        return min;
    }

    // - metoder relateret til størrelsen af inventory
    public boolean isEmpty() {
        return items.size() == 0;
    }

    public int getSize() {
        return items.size();
    }

    // - metode til udskrift af inventory (kunne også være toString())
    public String printInventory() {

        String result = "";

        for (int i = 0; i < items.size(); i++) {
            result += (i + 1) + ". " + items.get(i).toString() + "\n";
        }

        return result;
    }
}
