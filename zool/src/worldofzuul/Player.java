package worldofzuul;

public class Player {
    String name;
    int wallet;
    int score;
    private Inventory inventory = new Inventory();

    public int viewScore(){
        return score;
    }

    public int viewWallet(){
        return wallet;
    }
    public void copyItem(Inventory storeInv, Item item){
        inventory.addItem(storeInv.getItem(item));
    }

}
