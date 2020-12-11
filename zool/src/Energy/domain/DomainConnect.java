package Energy.domain;

import Energy.Interface.DomainI;

import java.util.Set;

public class DomainConnect implements DomainI {

    // ATTRIBUTTER
    private Game game;

    // CONSTRUCTOR
    public DomainConnect() {
        this.game = new Game();
    }

    // METODER
    @Override
    public Inventory getPlayerInventory() {
        return game.getPlayer().getInventory(); // Returnerer spillerens inventory
    }

    @Override
    public String getCurrentRoom() {
        return game.getCurrentRoom().getName(); // Returnerer navnet på current room
    }

    @Override
    public boolean addMove() {
        // hvis returner false = gå til næste år
        game.getPlayer().addMove();
        return game.getPlayer().getMoves() != game.getPlayer().getMovesPerRound();
    }

    // Info til statuslinie
    @Override
    public int getWallet() {
        return game.getPlayer().getWallet();
    }

    @Override
    public int getScore() {
        return game.getPlayer().getScore();
    }

    @Override
    public int getMovesRemaing() {
        return game.getPlayer().getMovesPerRound() - game.getPlayer().getMoves();
    }


    // Metoder til tjek af udgange og skift af rum
    @Override
    public boolean hasNorthExit() {
        Set<String> keys = game.getCurrentRoom().getExits().keySet();

        for (String exit : keys) {
            if (exit.equals("nord")) return true;
        }

        return false;
    }

    @Override
    public boolean hasSouthExit() {
        Set<String> keys = game.getCurrentRoom().getExits().keySet();

        for (String exit : keys) {
            if (exit.equals("syd")) return true;
        }

        return false;
    }

    @Override
    public boolean hasEastExit() {
        Set<String> keys = game.getCurrentRoom().getExits().keySet();

        for (String exit : keys) {
            if (exit.equals("øst")) return true;
        }

        return false;
    }

    @Override
    public boolean hasWestExit() {
        Set<String> keys = game.getCurrentRoom().getExits().keySet();

        for (String exit : keys) {
            if (exit.equals("vest")) return true;
        }

        return false;
    }

    @Override
    public void goNorth() {
        game.setCurrentRoom(game.getCurrentRoom().getExit("nord"));
    }

    @Override
    public void goSouth() {
        game.setCurrentRoom(game.getCurrentRoom().getExit("syd"));
    }

    @Override
    public void goEast() {
        game.setCurrentRoom(game.getCurrentRoom().getExit("øst"));
    }

    @Override
    public void goWest() {
        game.setCurrentRoom(game.getCurrentRoom().getExit("vest"));
    }


    // METODER TIL STORE
    @Override
    public Inventory getStoreInventory() {
        return game.store.getRoomInv();
    }

    @Override
    public String buyItem(int itemIndex) {
        // returnerer status om købet lykkedes eller fejlrapport til print på statuslabel i butik
        CommandWords cvs = new CommandWords();
        CommandWord cv = cvs.getCommandWord("køb");
        Command cmd = new Command(cv, String.valueOf(itemIndex));
        return game.buy(cmd);
    }

    @Override
    public Inventory getRoomInventory() {
        return game.getCurrentRoom().getRoomInv();
    }

    // METODER TIL AT REPLACE TING I RUM
    @Override
    public String replaceStaticGUI(int indexRoom) {
        // METODE til at replace faste ting i baggrundsbilledet der skal have en label

        // finder index i player's inventory
        int indexPlayer = game.getPlayerInvIndex(indexRoom);
        if (indexPlayer == -1) return null; // findes ikke i player inventory

        // henter energimærke på det der indsættes: null=ingen mærkning
        String returnEnergylabel = game.getPlayer().getInventory().getItem(indexPlayer).getEnergyLabel();

        // indsætter item og opdaterer status & inventory
        game.insertItem(indexRoom, indexPlayer);

        // returnerer streng med energimærke
        return returnEnergylabel;
    }

    @Override
    public String replaceDynamicGUI(int indexRoom) {
        // METODE til at replace faste ting i baggrundsbilledet der skal have en label

        int indexPlayer = game.getPlayerInvIndex(indexRoom); // finder index i player's inventory
        if (indexPlayer == -1) return null; // findes ikke i player inventory

        String returnName = game.getPlayer().getInventory().getItem(indexPlayer).getName(); // henter navn på det der indsættes

        game.insertItem(indexRoom, indexPlayer);  // indsætter item og opdaterer status & inventory

        return returnName; // returnerer streng med navn på item
    }

    public boolean canAffordMore() {
        return game.canAffordMore();
    }

    // START SKÆRM
    @Override
    public String welcomeText() {
        return game.welcomeText();
    }

    public String setStartAmountGUI(String value) {
        return game.setStartAmountGUI(value);
    }

    // NY RUNDE SKÆRM
    @Override
    public String nextRoundText() {

        // opdaterer runde score
        game.getPlayer().saveRoundScore();

        String result;
        result = "\nDu har nu afsluttet " + ((game.getPlayer().getRounds()) + 1) + ". år\n";
        result += game.statusText();
        return result;
    }

    @Override
    public boolean nextRoundGUI() {
        // starter ny runde, returnerer false, hvis max runder er udført!

        // tjekker om vi er nået max antal runder
        if (game.getPlayer().getRounds() == (game.getPlayer().getMaxNumberOfRounds() - 1)) {
            game.getPlayer().setRounds((game.getPlayer().getRounds()) + 1);
            return false;
        }

        // intialiser ny runde
        game.initNewRound();
        return true;
    }

    // SLUT SKÆRM
    @Override
    public String endGameText() {
        return game.endGameText();
    }

}