package worldofzuul;

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
    Room store, outside, utlity, bathroom, bedroom, kidsRoom, room, kitchen, livingRoom, corrridor1, corridor2, corridor3, corridor4; // liste over rum

    public Game() // opretter nyt spil
    {
        createRooms(); // kalder createRooms() sætter rum og udgange
        parser = new Parser();
        player = new Player();
    }

    private void createRooms() {

        // create room and description
        // translate from danish to english

        store = new Room("nu i Super Duper Byg, her kan du købe tingene til huset", true);
        outside = new Room("ude foran huset", false);
        utlity = new Room("i bryggerset", false);
        bathroom = new Room("i badeværelset", false);
        bedroom = new Room("i badeværelse", false);
        kidsRoom = new Room("i børneværelset", false);
        room = new Room("i værelset", false);
        kitchen = new Room("i køkkenet", false);
        livingRoom = new Room("i stuen", false);
        corrridor1 = new Room("i første del af gangen", false);
        corridor2 = new Room("i anden del af gangen", false);
        corridor3 = new Room("i tredje del af gangen", false);
        corridor4 = new Room("i fjerde del af gangen", false);

        // opretter udgange
        store.setExit("east", outside); //Fra butikken kan man gå outside

        outside.setExit("west", store); //fra outside kan man gå i butikken og gang 1
        outside.setExit("east", corrridor1);

        corrridor1.setExit("north", utlity); //fra gang 1 kan man gå i utlityet, kitchenet, gang 2 og outside
        corrridor1.setExit("south", kitchen);
        corrridor1.setExit("east", corridor2);
        corrridor1.setExit("west", outside);

        utlity.setExit("south", corrridor1); //fra utlityet kan man gå i gang 1

        kitchen.setExit("north", corrridor1); //fra kitchenet kan man gå til gang 1 og livingRoomn
        kitchen.setExit("east", livingRoom);

        livingRoom.setExit("west", kitchen); //fra livingRoomn kan man gå til kitchenet og gang 3
        livingRoom.setExit("north", corridor3);

        corridor2.setExit("north", bathroom); //fra gang 2 kan man gå til gang 1, gang 3 og bathroom
        corridor2.setExit("east", corridor3);
        corridor2.setExit("west", corrridor1);

        bathroom.setExit("south", corridor2); //fra bathroomt kan man gå til gang 2

        corridor3.setExit("north", bedroom); //fra gang 3 kan man gå til gangn 2, gang 4, bedroom og livingRoomn
        corridor3.setExit("south", livingRoom);
        corridor3.setExit("east", corridor4);
        corridor3.setExit("west", corridor2);

        bedroom.setExit("south", corridor3); //fra bedroom kan man gå til gang 3

        corridor4.setExit("north", kidsRoom); //fra gang 4 kan man gå til gang 3, kidsRoom og roomt
        corridor4.setExit("south", room);
        corridor4.setExit("west", corridor3);

        kidsRoom.setExit("south", corridor4); //fra kidsRoomt kan man gå til gang 4

        room.setExit("north", corridor4); //fra roomt kan man gå til gang 4

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
        printWelcome(); // velkomst hilsen


        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() // velkomst hilsen udskrift
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription()); // skriver beskrivelsen af første rum
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
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
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
        currentRoom.printRoomInv();
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

    private void buy(Command command){
        if (!command.hasSecondWord()) {
            System.out.println("Buy what?");
            return;
        }

        // finder index af det der skal købes
        int index = -1 + Integer.parseInt(command.getSecondWord());
        // TO DO:   check at second word er en integer inden parsing
        //          check at det er en gyldig INT, dvs mellem 0 og size()

        // finder pris på det der skal købes
        // int price =

        // kopierer fra index fra butikkens inventory til players inventory
        copyItem(store.getRoomInv(), index, player.getInventory());

        // fratrækker købet fra players wallet
        int amount = player.getWallet() - store.getRoomInv().getItem(index).getPrice();
        player.setWallet(amount);


        // udskriver køb og index (for tjek!)
        System.out.println("item er købt");
        System.out.println("player index:");
        player.getInventory().printInventory();
    }

    private void wallet(){
        System.out.println(player.viewWallet());
    }

    private void copyItem(Inventory sourceInventory, int itemIndex, Inventory destInventory) {
        destInventory.addItem(sourceInventory.getItem(itemIndex));
    }
}
