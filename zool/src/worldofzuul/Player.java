package worldofzuul;

public class Player {
    String name;
    private int wallet = 0;
    int score = 0;
    private Inventory inventory = new Inventory();

    public int viewScore(){
        return score;
    }

    public int viewWallet(){
        return wallet;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public Inventory getInventory() {
        return inventory;
    }


}

