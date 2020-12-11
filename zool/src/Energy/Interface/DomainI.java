package Energy.Interface;

import Energy.domain.Inventory;

public interface DomainI {

    // Metoder
    Inventory getPlayerInventory();

    String getCurrentRoomName();

    // Metoder der returnerer om currentroom har en udgang i en retning
    boolean hasNorthExit();

    boolean hasSouthExit();

    boolean hasEastExit();

    boolean hasWestExit();

    // Metoder til at skifte rum
    void goNorth();

    void goSouth();

    void goEast();

    void goWest();

    boolean addMove();

    String getRoomDescriptionText();

    // Statuslinie
    int getWallet();

    int getScore();

    int getMovesRemaing();

    // metoder til rum / udenfor
    String replaceStaticGUI(int indexRoom);     // Indsætter Items fra player inventory i room inventory:  returnerer Energimærke

    String replaceDynamicGUI(int indexRoom); // Indsætter Items fra player inventory i room inventory:  returnerer navn på indsat Item

    boolean canAffordMore(); // checker om der er råd til den billigste ting i butikken

    Inventory getRoomInventory(); // Returnerer rummets Inventory

    // metoder til butikken
    Inventory getStoreInventory();  // Returnerer butikkens Inventory

    String buyItem(int ItemIndex);     // Returnere streng med status på køb

    boolean inShop();

    // INFOSKÆRME
    // start
    String setStartAmount(String value); // ved fejl: Returnere meddelelse, ingen fejl: null

    String welcomeText();

    // ny runde
    String nextRoundText();

    boolean nextRound(); // returnerer false ved max antal runder!

    int getRound();


    // exit
    String endGameText();

}
