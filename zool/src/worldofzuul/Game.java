package worldofzuul;

import java.util.Scanner;

public class Game {

    private final int WASHINGMACHINE = 1;
    private final int DRYER = 2;
    private final int HEATING = 3;
    private final int STOVE = 4;
    private final int FRIDGE = 5;
    private final int DISHWASHER = 6;
    private final int WINDOW = 7;
    private final int LIGHTS = 8;
    private final int TV = 9;
    private final int WALLFIXER = 10;
    private final int ISOLATION = 11;
    private final int SOLARCELLS = 12;

    private Parser parser;
    private Player player;
    private Room currentRoom; // holder styr på det rum man befinder sig i
    Room store, outside, utility, bathroom, bedroom, kidsRoom, room, kitchen, livingRoom, corrridor1, corridor2, corridor3, corridor4; // liste over rum

    public Game() // opretter nyt spil
    {
        createRooms(); // kalder createRooms() sætter rum og udgange
        parser = new Parser();
        player = new Player();
        // max kapacitet i player inventory
        player.getInventory().setMaxSize(5);
    }

    private void createRooms() {

        // create room and description
        // translate from Danish to English

        store = new Room("nu i Super Duper Byg, her kan du købe nye ting til dit hus", true);
        outside = new Room("ude foran huset", false);
        utility = new Room("i bryggerset", false);
        bathroom = new Room("i badeværelset", false);
        bedroom = new Room("i soveværelset", false);
        kidsRoom = new Room("i børneværelset", false);
        room =200 new Room("i værelset", false);
        kitchen = new Room("i køkkenet", false);
        livingRoom = new Room("i stuen", false);
        corrridor1 = new Room("i første del af gangen", false);
        corridor2 = new Room("i anden del af gangen", false);
        corridor3 = new Room("i tredje del af gangen", false);
        corridor4 = new Room("i fjerde del af gangen", false);

        // opretter udgange
        store.setExit("øst", outside); //Fra butikken kan man gå outside

        outside.setExit("vest", store); //fra outside kan man gå i butikken og gang 1
        outside.setExit("øst", corrridor1);

        corrridor1.setExit("nord", utility); //fra gang 1 kan man gå i utilityet, kitchenet, gang 2 og outside
        corrridor1.setExit("syd", kitchen);
        corrridor1.setExit("øst", corridor2);
        corrridor1.setExit("vest", outside);

        utility.setExit("syd", corrridor1); //fra utilityet kan man gå i gang 1

        kitchen.setExit("nord", corrridor1); //fra kitchenet kan man gå til gang 1 og livingRoom
        kitchen.setExit("øst", livingRoom);

        livingRoom.setExit("vest", kitchen); //fra livingRoom kan man gå til kitchenet og gang 3
        livingRoom.setExit("nord", corridor3);

        corridor2.setExit("nord", bathroom); //fra gang 2 kan man gå til gang 1, gang 3 og bathroom
        corridor2.setExit("øst", corridor3);
        corridor2.setExit("vest", corrridor1);

        bathroom.setExit("syd", corridor2); //fra bathroom kan man gå til gang 2

        corridor3.setExit("nord", bedroom); //fra gang 3 kan man gå til gangn 2, gang 4, bedroom og livingRoom
        corridor3.setExit("syd", livingRoom);
        corridor3.setExit("øst", corridor4);
        corridor3.setExit("vest", corridor2);

        bedroom.setExit("syd", corridor3); //fra bedroom kan man gå til gang 3

        corridor4.setExit("nord", kidsRoom); //fra gang 4 kan man gå til gang 3, kidsRoom og room
        corridor4.setExit("syd", room);
        corridor4.setExit("vest", corridor3);

        kidsRoom.setExit("syd", corridor4); //fra kidsRoom kan man gå til gang 4

        room.setExit("nord", corridor4); //fra room kan man gå til gang 4

        //add to inventory ÆNDRER SCOREIMPACT TIL REALISTISKE
        store.addToInventory(new Item("Vaskemaskine", 2000, 1000, WASHINGMACHINE));
        store.addToInventory(new Item("Tørretumbler", 2000, 1000, DRYER));
        store.addToInventory(new Item("Varmeanlæg", 40000, 10000, HEATING));
        store.addToInventory(new Item("Komfur", 5000, 500, STOVE));
        store.addToInventory(new Item("Køleskab A+++", 8000, 470, FRIDGE)); // færdig
        store.addToInventory(new Item("Køleskab A++", 6500, 400, FRIDGE)); // færdig
        store.addToInventory(new Item("Køleskab A+", 4500, 350, FRIDGE)); // færdig
        store.addToInventory(new Item("Opvaskemaskine", 2500, 300, DISHWASHER));
        store.addToInventory(new Item("Vindue", 1000, 300, WINDOW));
        store.addToInventory(new Item("Belysning", 100, 100, LIGHTS));
        store.addToInventory(new Item("TV", 2800, 200, TV));
        store.addToInventory(new Item("Hul-fikser-kit", 150, 500, WALLFIXER));
        store.addToInventory(new Item("Isolering", 20000, 5000, ISOLATION));
        store.addToInventory(new Item("Solceller", 40000, 5500, SOLARCELLS));

        kitchen.addToInventory(new Item("Køleskab D", 0, 0, FRIDGE)); // færdig

        // sætter startrummet til outside
        currentRoom = outside;
    }

    public void play() {
        Scanner s = new Scanner(System.in);
        System.out.print("Indtast startbeløb: ");
        player.setWallet(s.nextInt());
        System.out.println();

        printWelcome(); // velkomst hilsen


        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Tak for, at du spillede vores spil");
    }

    private void printWelcome() // velkomst hilsen udskrift
    {
        System.out.println();
        System.out.println("Dette er dit hus!");
        System.out.println("Der er mange gamle hvidevarer som kan udskiftes, og ting der kan forbedreds,");
        System.out.println("som vil spare mere på energien... Og dine penge! Optimer dit hus og se hvor meget du kan spare.");
        System.out.println("Skriv '" + CommandWord.HELP + "' hvis du har brug for hjælp.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription()); // skriver beskrivelsen af første rum
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("Det forstod jeg ikke helt... Prøv noget andet eller skriv 'hjælp' for commands!");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.BUY) {
            buy(command);
        } else if (commandWord == CommandWord.WALLET){
            wallet();
        }
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("Dine kommandoer er:");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Hvilken vej vil du gå?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Du kan ikke gå den vej!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
        currentRoom.printRoomInv();
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Slut hvad?");
            return false;
        } else {
            return true;
        }
    }

    private void buy(Command command) {
        // Undersøger om du er i butikken
        if (!inShop()) {
            System.out.println("du kan kun handle i butikken!");
            return;
        }

        // undersøger om kommandoen har et andet ord
        if (!command.hasSecondWord()) {
            System.out.println("Køb hvad?");
            return;
        }

        // Tjekker at det andet ord (string) kan parses til en Integer
       if (!isInt(command.getSecondWord())) {
           System.out.println("fejl: ikke gyldigt nummer!");
           return;
       }

        // laver kommandoword om til int og finder index af det der skal købes
        int index = Integer.parseInt(command.getSecondWord()) - 1;

        // Tjekker om index er mellem 0 og butikkens max antal varer
        if (0 > index || index+1 > store.getRoomInv().getSize() ) {
            System.out.println("fejl: ikke gyldigt nummer!");
            return;
        }

        // finder pris på det der skal købes
        int price = store.getRoomInv().getItem(index).getPrice();

        // tjekker om spiller har råd
        if (player.getWallet() < price) {
            System.out.println("du har ikke råd!");
            return;
        }

        // Undersøger om spillers inventory er fyldt
        if (player.getInventory().getSize() == player.getInventory().getMaxSize()) {
            System.out.println("Inventory er fyldt!");
            return;
        }

        // kopierer fra butikkens inventory (index) til players inventory
        copyItem(store.getRoomInv(), index, player.getInventory());

        // fratrækker købet fra players wallet
        int amount = player.getWallet() - price;
        player.setWallet(amount);

        // udskriver køb og spiller inventory
        System.out.println("Du har købt " + store.getRoomInv().getItem(index).getName() + "\n");
        System.out.print("Spiller ");
        player.getInventory().printInventory();
    }

    private boolean isInt(String s) {
        for (int i = 0; i < s.length(); i++) {
      //      if(i==0 && s.charAt(i) == '-') continue;
            if( !Character.isDigit(s.charAt(i)) ) return false;
        }
        return true;
    }

    private boolean inShop() {
        return currentRoom == store;
    }

    private void wallet(){
        System.out.println(player.viewWallet());
    }

    private void copyItem(Inventory sourceInventory, int itemIndex, Inventory destInventory) {
        destInventory.addItem(sourceInventory.getItem(itemIndex));
    }
}
