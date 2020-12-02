package Energy.Interface;

import Energy.domain.Inventory;

public interface DomainI {

    // Metoder til parent Controller

    Inventory getPlayerInventory();
    String getCurrentRoom();

    // Metorder der ruturnerer om currentroom har en udgang i en retning
    boolean hasNorthExit();
    boolean hasSouthExit();
    boolean hasEastExit();
    boolean hasWestExit();
    // skal det måske bare være en metode der returnerer en Exit array, med mulige exits?

    // Getters til statuslinie
    int getWallet();
    int getScore();

    // Metoder til at skifte rum
    void goNorth();
    void goSouth();
    void goEast();
    void goWest();
/*
    // Metoder til flow i spil (ikke implementeret)
    // void newRound();
    // void exitGame();
*/
    // metoder til rum / udenfor - klik på ting i billedet og skift ud fra player inventory
//    String ReplaceInRoom(int itemIndex);

    // metoder til butikken
    Inventory getStoreInventory();
    // Returnere streng med status
    String buyItem(int ItemIndex);
}
