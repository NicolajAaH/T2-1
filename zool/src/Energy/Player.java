package Energy;

public class Player {
    private int wallet = 0;
    private int startAmount = 0;
    private int score = 0;
    private int startValue = 36000;
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

