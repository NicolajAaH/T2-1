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
                if (exit.equals("nord") ) return true;
            }

        return false;
    }

    @Override
    public boolean hasSouthExit() {
        Set<String> keys = game.getCurrentRoom().getExits().keySet();

        for(String exit : keys) {
            if (exit.equals("syd") ) return true;
        }

        return false;

    }

    @Override
    public boolean hasEastExit() {
        Set<String> keys = game.getCurrentRoom().getExits().keySet();

        for(String exit : keys) {
            if (exit.equals("øst") ) return true;
        }

        return false;
    }

    @Override
    public boolean hasWestExit() {
        Set<String> keys = game.getCurrentRoom().getExits().keySet();

        for(String exit : keys) {
            if (exit.equals("vest") ) return true;
        }

        return false;
    }

    @Override
    public String getCurrentRoom() {
        return game.getCurrentRoom().getName();
    }

    @Override
    public int getWallet() {
        return game.getPlayer().getWallet();
    }

    @Override
    public int getScore() {
        return game.getPlayer().getScore();
    }

    @Override
    public void goNorth() {
        game.setCurrentRoom(game.getCurrentRoom().getExit("nord"));
    }

    @Override
    public void goSouth() {
        game.setCurrentRoom(game.getCurrentRoom().getExit("syd"));
    }

    @Override
    public void goEast() {
        game.setCurrentRoom(game.getCurrentRoom().getExit("øst"));
    }

    @Override
    public void goWest() {
        game.setCurrentRoom(game.getCurrentRoom().getExit("vest"));
    }
}

