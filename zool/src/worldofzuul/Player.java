package worldofzuul;

public class Player {
    String name;
    int wallet;
    int score;
    worldofzuul.Game check = new worldofzuul.Game();
    public int viewScore(){
        return score;
    }

    public int viewWallet(){
        return wallet;
    }

    public Item buyItem(Item){
        if(check.isShop()){

        }
    }
}
