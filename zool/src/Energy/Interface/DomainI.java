package Energy.Interface;

import Energy.domain.Inventory;
import Energy.domain.Room;

public interface DomainI {

    // Metoder til parent Controller
    Inventory getPlayerInventory();
    Room getCurrentRoom();

    // Metorder der ruturnerer om currentroom har en udgang i en retning
    boolean hasNorthExit();
    boolean hasSouthExit();
    boolean hasEastExit();
    boolean hasWestExit();
    // skal det måske bare være en metode der returnerer en Exit array, med mulige exits?

    // Getters til statuslinie
    int getWallet();
    int getScore();

    // Metoder til at skifte rum (ikke implementeret)
    // void goNorth();
    // void goSouth();
    // void goEast();
    // void goWest();

    // Metoder til flow i spil (ikke implementeret)
    // void newRound();
    // void exitGame();

    // metoder til rum / udenfor

    // metoder til butikken
}
