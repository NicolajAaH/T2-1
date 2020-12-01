package Energy.domain;

import Energy.Interface.DomainI;

public class DomainConnect implements DomainI {

    private Game game;

    public DomainConnect() {
        this.game = new Game();
    }

    @Override
    public Inventory getPlayerInventory() {
        return game.getPlayer().getInventory();
    }

}
