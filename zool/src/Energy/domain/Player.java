package Energy.domain;

public class Player {

    // Attributes
    private int wallet = 0;
    private int startAmount = 0; // årligt renoverings budget - start Wallet
    private int startValue = 36000; // start forbrug (til Energimærkeberegning)

    private int score = 0;
    private Inventory inventory = new Inventory();
    private Inventory replacedItems = new Inventory();

    // holder styr på skift imellem rum
    private int moves = 0;
    private int movesPerRound = 100;

    // holder styr på runder (år)
    private int round = 0;
    private int maxNumberOfRounds = 5;
    private int[] roundScore = new int[maxNumberOfRounds];
    private int totalUsedAmount = 0;

    // Metoeder
    public void saveRoundScore() {
        roundScore[round] = score;
    }

    public void addMove() {
        moves++;
    }

    public void addAmountToTotal(int amount){
        totalUsedAmount += amount;
    }


    // Getters & setteres


    public int getRoundScore(int roundNr) {
        return roundScore[roundNr];
    }

    public int getWallet() {
        return wallet;
    }

    public int getTotalUsedAmount() {
        return totalUsedAmount;
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

    public int getRounds() {
        return round;
    }

    public void setRounds(int rounds) {
        this.round = rounds;
    }

    public int getMaxNumberOfRounds() {
        return maxNumberOfRounds;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getMovesPerRound() {
        return movesPerRound;
    }

    public void setMovesPerRound(int movesPerRound) {
        this.movesPerRound = movesPerRound;
    }
}

