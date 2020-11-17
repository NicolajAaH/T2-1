package Energy;

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

    public String printInventory() {

        String result = "";

        for (int i = 0; i < items.size(); i++) {
            result += (i+1) + ". " + items.get(i).toString() + "\n";
        }

        //System.out.println(result);
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
        // checker oom inventory er tomt
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
}
