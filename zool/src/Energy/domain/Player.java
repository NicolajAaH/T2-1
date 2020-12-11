package Energy.domain;

class Player {

    // Attributter
    private int wallet = 0; // aktuel saldo
    private int startAmount = 0; // årligt renoverings budget - start saldo
    private int totalUsedAmount = 0; // summeret forbrug

    private int round = 0; // holder styr på runder (år)

    private int score = 0; // samlet energibesparelse
    private int[] roundScore = new int[Game.getMaxNumberOfRounds()]; // score i de individuelle år

    private int moves = 0; // holder styr på antal skift imellem rum

    private Inventory inventory = new Inventory();

    // GETTERS & SETTERS
    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public int getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(int startAmount) {
        this.startAmount = startAmount;
    }

    public int getTotalUsedAmount() {
        return totalUsedAmount;
    }

    public int getRounds() {
        return round;
    }

    public void setRounds(int rounds) {
        this.round = rounds;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRoundScore(int roundNr) {
        return roundScore[roundNr];
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public Inventory getInventory() {
        return inventory;
    }

    // METODER
    public void addMove() {
        moves++;
    }

    public void saveRoundScore() {
        roundScore[round] = score;
    }

    public void addAmountToTotal(int amount) {
        totalUsedAmount += amount;
    }
}

