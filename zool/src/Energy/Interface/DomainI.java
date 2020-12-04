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

    // INFOSKÆRME (START; NYRUNDE; EXIT;)

    // STARTSKÆRM
    // konverter string til int og sættter startbeløb, returnerer string ved fejl-meddelelse, null hvis ingen fejl
    public String setStartAmountGUI(String value);
    public String welcomeText();

    // NY RUNDE SKÆRM
    String newRoundText();
    boolean newRound(); // returnerer false ved max antal runder!

    // void exitGame();
    String endGameText();

    // metoder til rum / udenfor
    // Indsætter ting fra player index i room index og returnerer String med Energimærke
    public String replaceStaticGUI(int indexRoom);
    // Indsætter ting fra player index i room index og returnerer String med navn på indsat item
    public String replaceDynamicGUI(int indexRoom);

    public Inventory getRoomInventory();

    // metoder til butikken
    Inventory getStoreInventory();
    // Returnere streng med status på køb
    String buyItem(int ItemIndex);
}
