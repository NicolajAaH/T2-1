package Energy.domain;

import java.util.ArrayList;

public class Inventory {
    private int maxSize;
    private ArrayList<Item> items = new ArrayList<>();

    public Inventory(int maxSize) {
        this.maxSize = maxSize;
    }

    public Inventory() {
    }

    // METODER
    public void addItem(Item item) {
            items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void removeItem(int index) {
        items.remove(index);
    }

    public void replaceItem(int replaceIndex, Item withItem) {
        items.set(replaceIndex, withItem);
    }

    public String printInventory() {

        String result = "";

        for (int i = 0; i < items.size(); i++) {
            result += (i+1) + ". " + items.get(i).toString() + "\n";
        }

        return result;
    }

    public boolean isEmpty(){
    return items.size() == 0;
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

    public int cheapestItem() {
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
    } // Returnerer billigste Item i inventory

    public ArrayList<Item> getItems() {
        return items;
    }
}
