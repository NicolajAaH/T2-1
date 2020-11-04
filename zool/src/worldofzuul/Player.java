package worldofzuul;

public class Player {
    String name;
    private int wallet = 0;
    int score;
    private Inventory inventory = new Inventory();

    public int getScore(){
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

