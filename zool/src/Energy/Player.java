package Energy;

public class Player {

    // Attributes
    private int wallet = 0;
    private int startAmount = 0;
    private int startValue = 0; // remember start wallet??

    private int score = 0;
    private Inventory inventory = new Inventory();
    private Inventory replacedItems = new Inventory();

    // holder styr på skift imellem rum
    private int moves = 0;
    private int movesPerRound = 50;

    // holder styr på runder (år)
    private int rounds = 0;
    private int maxNumberOfRounds = 10;
    private int[] roundScore = new int[maxNumberOfRounds];


    // Metoeder

    // Getters & setteres
    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

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



    public Inventory getInventory() {
        return inventory;
    }




}

