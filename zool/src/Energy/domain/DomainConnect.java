package Energy.domain;

import Energy.Interface.DomainI;

import java.util.Set;

public class DomainConnect implements DomainI {

    private Game game;

    public DomainConnect() {
        this.game = new Game();
    }

    @Override
    public Inventory getPlayerInventory() {
        return game.getPlayer().getInventory();
    }

    // Metorder der ruturnerer om currentroom har en udgang i en retning
    @Override
    public boolean hasNorthExit() {
            Set<String> keys = game.getCurrentRoom().getExits().keySet();

            for(String exit : keys) {
                if (exit.equals("nord") ) return true;
            }

        return false;
    }

    @Override
    public boolean hasSouthExit() {
        Set<String> keys = game.getCurrentRoom().getExits().keySet();

        for(String exit : keys) {
            if (exit.equals("syd") ) return true;
        }

        return false;

    }

    @Override
    public boolean hasEastExit() {
        Set<String> keys = game.getCurrentRoom().getExits().keySet();

        for(String exit : keys) {
            if (exit.equals("øst") ) return true;
        }

        return false;
    }

    @Override
    public boolean hasWestExit() {
        Set<String> keys = game.getCurrentRoom().getExits().keySet();

        for(String exit : keys) {
            if (exit.equals("vest") ) return true;
        }

        return false;
    }

    // Returnerer navnet på current room
    @Override
    public String getCurrentRoom() {
        return game.getCurrentRoom().getName();
    }

    // Getters til statuslinie
    @Override
    public int getWallet() {
        return game.getPlayer().getWallet();
    }

    @Override
    public int getScore() {
        return game.getPlayer().getScore();
    }


    // Metoder til at skifte rum
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


    // Metoder til butikken
    @Override
    public Inventory getStoreInventory() {
        return game.store.getRoomInv();
    }

    @Override
    // returnerer status om købet lykkedes eller fejlrapport til print på statuslabel i butik
    public String buyItem(int itemIndex) {
        CommandWords cvs = new CommandWords();
        CommandWord cv = cvs.getCommandWord("køb");
        Command cmd = new Command(cv,String.valueOf(itemIndex));
        return game.buy(cmd);
    }

    @Override
    public Inventory getRoomInventory() {
        return game.getCurrentRoom().getRoomInv();
    }

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

    public String replaceDynamicGUI(int indexRoom) {
        // METODE til at replace faste ting i baggrundsbilledet der skal have en label

        // finder index i player's inventory
        int indexPlayer = game.getPlayerInvIndex(indexRoom);
        if (indexPlayer == -1) return null; // findes ikke i player inventory

        // henter navn på det der indsættes: null=ingen mærkning
        String returnName = game.getPlayer().getInventory().getItem(indexPlayer).getName();

        // indsætter item og opdaterer status & inventory
        game.insertItem(indexRoom, indexPlayer);

        // returnerer streng med energimærke
        return returnName;
    }

    public boolean canAffordMore() {
        return game.canAffordMore();
    }

    // METODER TIL SPIL FLOW

    // START SKÆRM
    // returnerer velkomst tekst
    @Override
    public String welcomeText() {
        return game.welcomeText();
    }

    public String setStartAmountGUI(String value){
        return game.setStartAmountGUI(value);
    }

    // RUNDE SKÆRM

    @Override
    public String newRoundText() {
        String result;

        result = "\nDu har nu afsluttet " + ((game.getPlayer().getRounds()) + 1)  + ". år\n";
        result += game.endStatusText();

        return result;
    }

    // starter ny runde, returnerer false hvis max runder er udført!
    public boolean newRound() {
        // tjekker om vi er nået max antal runder
        if (game.getPlayer().getRounds() == (game.getPlayer().getMaxNumberOfRounds() -1 ) ) {
            game.getPlayer().setRounds((game.getPlayer().getRounds()) + 1);
            return false;
        }
        // intialiser ny runde
        game.initNewRound();
        return true;
    }

    public String endGameText() {
        String result ="";

        // Tilføjer tekst hvis slutskærmen vises pga max antal år
        if (game.getPlayer().getRounds() == game.getPlayer().getMaxNumberOfRounds()) {
            result += " - Du har spillet max antal år - \n";
            game.getPlayer().setRounds((game.getPlayer().getRounds()) - 1);
        }
        result += "\n--- Tak for, at du spillede vores spil ---\n";
        result += game.endStatusText();
        result += "\n\nLavet af: Yusuf Baysoz, Victor Poulsen, Emil Spangenberg, Theis Langlands & Nicolaj Hansen";

        return result;
    }

    @Override
    // false = gå til næste år
    public boolean addMove() {
        game.getPlayer().addMove();
        return game.getPlayer().getMoves() != game.getPlayer().getMovesPerRound();
    }

    public int getMovesRemaing() {
        return game.getPlayer().getMovesPerRound() - game.getPlayer().getMoves();
    }
}