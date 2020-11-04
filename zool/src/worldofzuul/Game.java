package worldofzuul;

import java.util.InputMismatchException;
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
    private final int BATH = 13;

    private Parser parser;
    private Player player;
    private Room currentRoom; // holder styr på det rum man befinder sig i
    Room store, outside, utlity, bathroom, bedroom, kidsRoom, room, kitchen, livingRoom, corrridor1, corridor2, corridor3, corridor4; // liste over rum

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
        // translate from danish to english

        store = new Room("nu i Super Duper Byg, her kan du købe tingene til huset", true);
        outside = new Room("ude foran huset", false);
        utlity = new Room("i bryggerset", false);
        bathroom = new Room("i badeværelset", false);
        bedroom = new Room("i soveværelsen", false);
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
        store.addToInventory(new Item("Vaskemaskine A+", 2400, 65, WASHINGMACHINE)); // færdig
        store.addToInventory(new Item("Vaskemaskine A++", 3000, 49, WASHINGMACHINE)); // færdig
        store.addToInventory(new Item("Vaskemaskine A+++", 4000, 43, WASHINGMACHINE)); // færdig
        store.addToInventory(new Item("Tørretumbler A+", 2887, 850, DRYER)); //færdig
        store.addToInventory(new Item("Tørretumbler A++", 3499, 1039, DRYER)); //færdig
        store.addToInventory(new Item("Tørretumbler A+++", 4318, 1180, DRYER)); //færdig
        store.addToInventory(new Item("Pillefyr (varmeanlæg)", 45000, 10057, HEATING)); //færdig
        store.addToInventory(new Item("Gas (varmeanlæg)", 50000, 12240, HEATING)); // færdig
        store.addToInventory(new Item("Energibesparende komfur", 4500, 1250, STOVE)); // færdig
        store.addToInventory(new Item("Køleskab A+++", 8000, 470, FRIDGE)); // færdig
        store.addToInventory(new Item("Køleskab A++", 6500, 400, FRIDGE)); // færdig
        store.addToInventory(new Item("Køleskab A+", 4500, 350, FRIDGE)); // færdig
        store.addToInventory(new Item("Opvaskemaskine A++", 3300, 400, DISHWASHER));
        store.addToInventory(new Item("Opvaskemaskine A", 2500, 300, DISHWASHER));
        store.addToInventory(new Item("Termorude (2 lag)", 1000, 375, WINDOW)); // færdig
        store.addToInventory(new Item("Sparepære", 20, 230, LIGHTS)); // færdig
        store.addToInventory(new Item("LED-pære", 60, 350, LIGHTS)); // færdig
        store.addToInventory(new Item("Energisparende TV", 5900, 520, TV)); //færdig
        store.addToInventory(new Item("Hul-fikser-kit", 150, 500, WALLFIXER)); //færdig
        store.addToInventory(new Item("Isolering", 100000, 9300, ISOLATION)); //færdig
        store.addToInventory(new Item("Solceller", 80000, 8000, SOLARCELLS)); //færdig

        utlity.addToInventory(new Item("Vaskemaskine D", 0, 0, WASHINGMACHINE));
        utlity.addToInventory(new Item("Tørretumbler D", 0, 0, DRYER));
        utlity.addToInventory(new Item("Oliefyr", 0, 0, HEATING));

        kitchen.addToInventory(new Item("Køleskab D", 0, 0, FRIDGE)); // færdig
        kitchen.addToInventory(new Item("Komfur C", 0, 0, STOVE));
        kitchen.addToInventory(new Item("Opvaskemaskine D", 0, 0, DISHWASHER));

        livingRoom.addToInventory(new Item("Enkeltlags vindue", 0, 0, WINDOW));
        livingRoom.addToInventory(new Item("Glødepære", 0, 0, LIGHTS));
        livingRoom.addToInventory(new Item("TV D", 0, 0, TV));

        bedroom.addToInventory(new Item("Glødepære", 0, 0, LIGHTS));
        bedroom.addToInventory(new Item("Enkeltlags vindue", 0, 0, WINDOW));
        bedroom.addToInventory(new Item("Hul i væggen", 0, 0, WALLFIXER));

        room.addToInventory(new Item("Glødepære", 0, 0, LIGHTS));
        room.addToInventory(new Item("Enkeltlags vindue", 0, 0, WINDOW));

        kidsRoom.addToInventory(new Item("Glødepære", 0, 0, LIGHTS));
        kidsRoom.addToInventory(new Item("Enkeltlags vindue", 0, 0, WINDOW));
        kidsRoom.addToInventory(new Item("Hul i væggen", 0, 0, WALLFIXER));

        bathroom.addToInventory(new Item("Bruser D", 0, 0, BATH));

        outside.addToInventory(new Item("Tag uden solceller", 0, 0, SOLARCELLS));
        outside.addToInventory(new Item("Tyndt isolering", 0, 0, ISOLATION));
        


        // sætter startrummet til outside
        currentRoom = outside;
    }

    public void play() {
        Scanner s = new Scanner(System.in);
        System.out.print("Indtast startbeløb: ");
        while(true) {
            String value = s.nextLine();
            if (isInt(value)) {
                int value2 = 0;
                try{
                    value2 = Integer.parseInt(value);
                }catch(NumberFormatException e){
                    System.out.println("Værdien er for høj!");
                }
                if (value2 > 0 && value2 <= 100000) {
                    player.setWallet(value2);
                    player.setStartAmount(value2);
                    break;
                } else {
                    System.out.println("Der må ikke stå bogstaver i beløbet og værdien skal være mellem 0 og 100.000kr. \nIndtast nyt beløb: ");
                }
            } else {
                System.out.println("Der må ikke stå bogstaver i beløbet og værdien skal være mellem 0 og 100.000kr. \nIndtast nyt beløb: ");
            }

        }


        printWelcome(); // velkomst hilsen


        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }

        System.out.println("Tak for, at du spillede vores spil\n");
        System.out.println("Du har sparet " + player.getScore() + " kr. om året i energiforbedringer");
        System.out.println("Og har brugt " + (player.getStartAmount() - player.getWallet()) + " kr,-");
        System.out.println("Energimærke ??");
    }

    private void printWelcome() // velkomst hilsen udskrift
    {
        System.out.println("\n Dette er dit hus");
        System.out.println("Der er mange ting der kan forbedres, så du sparer mange penge, og udnytter energien bedre");
        System.out.println("Skriv '" + CommandWord.HELP + "' hvis du har brug for hjælp.\n");
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
        } else if (commandWord == CommandWord.INVENTORY) {
            inventory();
        } else if (commandWord == CommandWord.DELETE) {
            delete(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.BUY) {
            buy(command);
        } else if (commandWord == CommandWord.REPLACE) {
            replace(command);
        } else if (commandWord == CommandWord.WALLET){
            wallet();
        } else if (commandWord == CommandWord.SCORE){
            System.out.println("Din score er: " + player.getScore());
        }
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("Dine kommandoer er:");
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
        System.out.print("Rummmet indeholder ");
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

    private void buy(Command command) {
        // Undersøger om du er i butikken
        if (!inShop()) {
            System.out.println("du kan kun handle i butikken!");
            return;
        }

        // undersøger om kommandoen har et andet ord
        if (!command.hasSecondWord()) {
            System.out.println("Køb Hvad?");
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

    private void replace(Command command) {
        // Undersøger om du er i butikken
        if (inShop()) {
            System.out.println("fejl: du kan kun handle i butikken!");
            return;
        }

        // undersøger om kommandoen har et andet ord
        if (!command.hasSecondWord()) {
            System.out.println("Udskift hvad?");
            return;
        }

        // Tjekker at det andet ord (string) kan parses til en Integer
        if (!isInt(command.getSecondWord())) {
            System.out.println("fejl: ikke gyldigt nummer!");
            return;
        }

        // laver kommandoword om til int og finder index af det der skal udskiftes
        int index = Integer.parseInt(command.getSecondWord()) - 1;

        // Tjekker om index er mellem 0 og rummets max antal items
        if (0 > index || index + 1 > currentRoom.getRoomInv().getSize()) {
            System.out.println("fejl: ikke gyldigt nummer!");
            return;
        }

        // Tjekker om players inventory har et Item af samme type. (kunne være metode!)
        boolean inInventory = false;
        int playerInvIndex = 0;

        for (int i = 0; i < player.getInventory().getSize(); i++) {

            int itemTypeRoom = currentRoom.getRoomInv().getItem(index).getItemType();

            if (player.getInventory().getItem(i).getItemType() == itemTypeRoom) {
                inInventory = true;
                playerInvIndex = i;
            }
        }

        if (!inInventory) {
            System.out.println("du har ikke den type i dit inventory, gå i Super Byg!");
            return;
        }

        // Indsætter Item i room inventory fra player inventory

        // Fjerner gammelt Item fra Room

        currentRoom.getRoomInv().removeItem(currentRoom.getRoomInv().getItem(index));

        // Kopierer item fra player index til room
        copyItem(player.getInventory(), playerInvIndex, currentRoom.getRoomInv());

        // Opdaterer score
        int nyScore = player.getScore() + player.getInventory().getItem(playerInvIndex).getScoreImpact();
        player.setScore(nyScore);

        // Sletter item fra player index
        player.getInventory().removeItem(player.getInventory().getItem(playerInvIndex));

        // Udskriver inventory fra player og Room
        System.out.print("player ");
        player.getInventory().printInventory();
        System.out.print("room ");
        currentRoom.getRoomInv().printInventory();
        System.out.println("score er nu: " + player.getScore());
    }

    private void delete(Command command) {
        // her skal være delete kode

        // undersøger om kommandoen har et andet ord
        if (!command.hasSecondWord()) {
            System.out.println("Slet hvad?");
            return;
        }

        // Tjekker at det andet ord (string) kan parses til en Integer
        if (!isInt(command.getSecondWord())) {
            System.out.println("fejl: ikke gyldigt nummer!");
            return;
        }

        // laver kommandoword om til int og finder index af det der skal udskiftes
        int index = Integer.parseInt(command.getSecondWord()) - 1;

        // Tjekker om index er mellem 0 og player items
        if (0 > index || index + 1 > player.getInventory().getSize()) {
            System.out.println("fejl: ikke gyldigt nummer!");
            return;
        }

        // fjerner Item fra inventory
        player.getInventory().removeItem(player.getInventory().getItem(index));

        // printer ny inventory
        System.out.println("ny inventory");
        player.getInventory().printInventory();

    }




    private boolean isInt(String s) {
        for (int i = 0; i < s.length(); i++) {
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

    private void inventory() {
        System.out.print("Spiller ");
        player.getInventory().printInventory();
    }
    private void copyItem(Inventory sourceInventory, int itemIndex, Inventory destInventory) {
        destInventory.addItem(sourceInventory.getItem(itemIndex));
    }
}
