package worldofzuul;

public class Player {
    String name;
    private int wallet = 0;
    private int startAmount = 0;
    int score = 0;
    int startValue = 36000;
    private Inventory inventory = new Inventory();

    public int getStartValue() {
        return startValue;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStartAmount() {
        return startAmount;
    }


    public void setStartAmount(int startAmount) {
        this.startAmount = startAmount;
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

