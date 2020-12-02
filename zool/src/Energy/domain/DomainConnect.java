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

    @Override
    public boolean hasNorthExit() {
            Set<String> keys = game.getCurrentRoom().getExits().keySet();

            for(String exit : keys) {
                if (exit.equals("north") ) return true;
            }

        return false;
    }

    @Override
    public boolean hasSouthExit() {
        Set<String> keys = game.getCurrentRoom().getExits().keySet();

        for(String exit : keys) {
            if (exit.equals("south") ) return true;
        }

        return false;

    }

    @Override
    public boolean hasEastExit() {
        Set<String> keys = game.getCurrentRoom().getExits().keySet();

        for(String exit : keys) {
            if (exit.equals("east") ) return true;
        }

        return false;
    }

    @Override
    public boolean hasWestExit() {
        Set<String> keys = game.getCurrentRoom().getExits().keySet();

        for(String exit : keys) {
            if (exit.equals("west") ) return true;
        }

        return false;
    }

    @Override
    public Room getCurrentRoom() {
        return game.getCurrentRoom();
    }

    @Override
    public int getWallet() {
        return game.getPlayer().getWallet();
    }

    @Override
    public int getScore() {
        return game.getPlayer().getScore();
    }
}

