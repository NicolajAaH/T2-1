package Energy.domain;

import java.awt.*;

public class Item {

    // attributes:
    private String name;
    private int price;
    private int scoreImpact;
    private int itemType;
    private String energyLabel;
    // fridge, window etc:

    // constructor
    public Item(String name, int price, int scoreImpact, int itemType, String energyLabel) {
        this.name = name;
        this.price = price;
        this.scoreImpact = scoreImpact;
        this.itemType = itemType;
        this.energyLabel = energyLabel;
    }

    // Getters & Methods
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getScoreImpact() {
        return scoreImpact;
    }

    public int getItemType() {
        return itemType;
    }

    @Override
    public String toString() {
        return "" + name + ", Pris: " + price + ", Årlig besparelse: " + scoreImpact;
    }

}