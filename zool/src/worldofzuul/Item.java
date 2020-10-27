package worldofzuul;

public class Item {

    // attributes:
    private String name;
    // private EnergyLabel energyLabel; evt?
    private int price;
    private int scoreImpact;
    private int itemType; // fridge, window etc:

    // constructor
    public Item(String name, int price, int scoreImpact, int itemType) {
        this.name = name;
        this.price = price;
        this.scoreImpact = scoreImpact;
        this.itemType = itemType;
    }

    // methods

    // Getters
    public String getName() {
        return name;
    }

    /* evt
    public EnergyLabel getEnergyLabel() {
        return energyLabel;
    }
    */

    public int getPrice() {
        return price;
    }

    public int getScoreImpact() {
        return scoreImpact;
    }

    public int getItemType() {
        return itemType;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", energyLabel=" + energyLabel +
                ", price=" + price +
                ", scoreImpact=" + scoreImpact +
                ", itemType=" + itemType +
                '}';
    }
}