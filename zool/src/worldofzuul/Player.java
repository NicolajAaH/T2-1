package worldofzuul;

public class Player {
    String name;
    private int wallet = 30000;
    int score;
    private Inventory inventory = new Inventory();

    public int viewScore(){
        return score;
    }

    public int viewWallet(){
        return wallet;
    }

    public Inventory getInventory() {
        return inventory;
    }
}

