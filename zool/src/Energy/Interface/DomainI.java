package Energy.Interface;

import Energy.domain.Inventory;

public interface DomainI {

    // Metoder
    Inventory getPlayerInventory();

    String getCurrentRoomName();

    int getRound();

    // Info til statuslinie
    int getWallet();

    int getScore();

    int getMovesRemaing();

    // Metoder til tjek af udgange og skift af rum
    boolean hasNorthExit();

    boolean hasSouthExit();

    boolean hasEastExit();

    boolean hasWestExit();

    void goNorth();

    void goSouth();

    void goEast();

    void goWest();

    boolean addMove();

    String getRoomDescriptionText();

    // METODER TIL BUTIK
    Inventory getStoreInventory();  // Returnerer butikkens Inventory

    String buyItem(int ItemIndex);     // Returnere streng med status på køb

    // METODER TIL AT UDSKIFTE TING I RUM
    String replaceStaticGUI(int indexRoom);     // Indsætter Items fra player inventory i room inventory:  returnerer Energimærke

    String replaceDynamicGUI(int indexRoom); // Indsætter Items fra player inventory i room inventory:  returnerer navn på indsat Item

    boolean canAffordMore(); // checker om der er råd til den billigste ting i butikken

    // ANDRE METODER RELATERET TIL RUM
    boolean inShop();

    Inventory getRoomInventory(); // Returnerer rummets Inventory

    // START SKÆRM
    String welcomeText();

    String setStartAmount(String value); // ved fejl: Returnere meddelelse, ingen fejl: null

    // NY RUNDE SKÆRM
    String nextRoundText();

    boolean nextRound(); // returnerer false ved max antal runder!

    // SLUT SKÆRM
    String endGameText();


}
