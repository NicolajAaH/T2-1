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
    // metoder til rum / udenfor
    // Indsætter ting fra player index i room index og returnerer String med Energimærke
    public String replaceStaticGUI(int indexRoom);
    public String replaceDynamicGUI(int indexRoom);

    // metoder til butikken
    Inventory getStoreInventory();
    // Returnere streng med status på køb
    String buyItem(int ItemIndex);
}
