package Energy.Interface;

import Energy.domain.Inventory;

public interface DomainI {

    Inventory getPlayerInventory();

    boolean hasNorthExit();
    boolean hasSouthExit();
    boolean hasEastExit();
    boolean hasWestExit();


}
