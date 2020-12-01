package Energy.domain;

import Energy.Interface.DomainI;

public class DomainConnect implements DomainI {

    private Game game;

    public DomainConnect(Game game) {
        this.game = game;
    }

    @Override
    public Inventory getPlayerInventory() {
        return game.getPlayer().getInventory();
    }

    

}
