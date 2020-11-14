package Energy;

import java.util.Scanner;

public class Game {


    private Parser parser;
    private Player player;
    private Room currentRoom; // holder styr på det rum man befinder sig i
    Room store, outside, utility, bathroom, bedroom, kidsRoom, room, kitchen, livingRoom, corrridor1, corridor2, corridor3, corridor4; // liste over rum

    // kategorier af Items der kan oprettes
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


    public Game() // opretter nyt spil
    {
        createRooms(); // sætter rum og udgange
        parser = new Parser();
        player = new Player();
        // max kapacitet i player inventory
        player.getInventory().setMaxSize(5);
    }

    private void createRooms() {

        // create room and description

        store = new Room("nu i Super Duper Byg, her kan du købe tingene til huset");
        outside = new Room("ude foran huset");
        utility = new Room("i bryggerset");
        bathroom = new Room("i badeværelset");
        bedroom = new Room("i soveværelsen");
        kidsRoom = new Room("i børneværelset");
        room = new Room("i værelset");
        kitchen = new Room("i køkkenet");
        livingRoom = new Room("i stuen");
        corrridor1 = new Room("i første del af gangen");
        corridor2 = new Room("i anden del af gangen");
        corridor3 = new Room("i tredje del af gangen");
        corridor4 = new Room("i fjerde del af gangen");

        // opretter udgange
        store.setExit("øst", outside); //Fra butikken kan man gå outside

        outside.setExit("vest", store); //fra outside kan man gå i butikken og gang 1
        outside.setExit("øst", corrridor1);

        corrridor1.setExit("nord", utility); //fra gang 1 kan man gå i utility, kitchen, gang 2 og outside
        corrridor1.setExit("syd", kitchen);
        corrridor1.setExit("øst", corridor2);
        corrridor1.setExit("vest", outside);

        utility.setExit("syd", corrridor1); //fra utility kan man gå i gang 1

        kitchen.setExit("nord", corrridor1); //fra kitchen kan man gå til gang 1 og livingRoom
        kitchen.setExit("øst", livingRoom);

        livingRoom.setExit("vest", kitchen); //fra livingRoom kan man gå til kitchen og gang 3
        livingRoom.setExit("nord", corridor3);

        corridor2.setExit("nord", bathroom); //fra gang 2 kan man gå til gang 1, gang 3 og bathroom
        corridor2.setExit("øst", corridor3);
        corridor2.setExit("vest", corrridor1);

        bathroom.setExit("syd", corridor2); //fra bathroom kan man gå til gang 2

        corridor3.setExit("nord", bedroom); //fra gang 3 kan man gå til gang 2, gang 4, bedroom og livingRoom
        corridor3.setExit("syd", livingRoom);
        corridor3.setExit("øst", corridor4);
        corridor3.setExit("vest", corridor2);

        bedroom.setExit("syd", corridor3); //fra bedroom kan man gå til gang 3

        corridor4.setExit("nord", kidsRoom); //fra gang 4 kan man gå til gang 3, kidsRoom og room
        corridor4.setExit("syd", room);
        corridor4.setExit("vest", corridor3);

        kidsRoom.setExit("syd", corridor4); //fra kidsRoom kan man gå til gang 4

        room.setExit("nord", corridor4); //fra room kan man gå til gang 4

        //add to inventory
        store.addToInventory(new Item("Vaskemaskine A+", 2400, 65, WASHINGMACHINE));
        store.addToInventory(new Item("Vaskemaskine A++", 3000, 49, WASHINGMACHINE));
        store.addToInventory(new Item("Vaskemaskine A+++", 4000, 43, WASHINGMACHINE)); 
        store.addToInventory(new Item("Tørretumbler A+", 2887, 850, DRYER));
        store.addToInventory(new Item("Tørretumbler A++", 3499, 1039, DRYER));
        store.addToInventory(new Item("Tørretumbler A+++", 4318, 1180, DRYER));
        store.addToInventory(new Item("Pillefyr (varmeanlæg)", 45000, 10057, HEATING));
        store.addToInventory(new Item("Gas (varmeanlæg)", 50000, 12240, HEATING));
        store.addToInventory(new Item("Energibesparende komfur", 4500, 1250, STOVE));
        store.addToInventory(new Item("Køleskab A+++", 8000, 470, FRIDGE));
        store.addToInventory(new Item("Køleskab A++", 6500, 400, FRIDGE));
        store.addToInventory(new Item("Køleskab A+", 4500, 350, FRIDGE));
        store.addToInventory(new Item("Opvaskemaskine A++", 3300, 400, DISHWASHER));
        store.addToInventory(new Item("Opvaskemaskine A", 2500, 300, DISHWASHER));
        store.addToInventory(new Item("Termorude (2 lag)", 1000, 375, WINDOW));
        store.addToInventory(new Item("Sparepære", 20, 230, LIGHTS));
        store.addToInventory(new Item("LED-pære", 60, 350, LIGHTS));
        store.addToInventory(new Item("Energisparende TV", 5900, 520, TV));
        store.addToInventory(new Item("Hul-fikser-kit", 150, 500, WALLFIXER));
        store.addToInventory(new Item("Isolering", 10000, 9300, ISOLATION));
        store.addToInventory(new Item("Solceller", 30000, 8000, SOLARCELLS));

        utility.addToInventory(new Item("Vaskemaskine D", 0, 0, WASHINGMACHINE));
        utility.addToInventory(new Item("Tørretumbler D", 0, 0, DRYER));
        utility.addToInventory(new Item("Oliefyr", 0, 0, HEATING));

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

        printWelcome(); // opstart af spil

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }

        printExit();

    }

    private void printWelcome() // opstart af spil
    {
        System.out.println("Du befinder dig i et dejligt dansk parcelhus på 160 m2 med energimærke F ");
        System.out.println("- din mission er at forbedre huset så godt som muligt, så du sparer mange penge, og udnytter energien bedre");
        System.out.println("Du kan handle i Super Byg og udskifte ting i dit hus\n");
        System.out.print("Indtast dit årlige renoverings budget: ");

        // Henter budget fra bruger
        Scanner s = new Scanner(System.in);
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

        System.out.println("\nSkriv '" + CommandWord.HELP + "' hvis du har brug for hjælp.\n");
        System.out.println(currentRoom.getLongDescription()); // skriver beskrivelsen af første rum
    }

    private void printExit(){
        System.out.println("Tak for, at du spillede vores spil\n");
        System.out.println("Du har sparet " + player.getScore() + " kr. om året i energiforbedringer");
        System.out.println("Og har brugt " + (player.getStartAmount() - player.getWallet()) + " kr,-");
        System.out.println("Du startede med energimærke " + EnergyLabel.createEnergyLabel(0,player.getStartValue()));
        System.out.println("Du er nu på energimærke " + EnergyLabel.createEnergyLabel(player.getScore(), player.getStartValue()));
        System.out.println("Lavet af: Yusuf Bayoz, Victor Poulsen, Emil Spangenberg, Theis Langlands & Nicolaj Hansen");
    } // afslutning af spil


    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("Jeg ved ikke hvad du mener...");
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
        } else if (commandWord == CommandWord.STATUS){
            System.out.println("Din score er: " + player.getScore() + " og du har " + player.getWallet() + "kr. i pungen");
        }
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("");
        System.out.println("Dine kommandoer er:");
        parser.showCommands();
    }

    private boolean goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Hvilken retning?");
            return false;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        // tjekker om der er et rum i den retning
        if (nextRoom == null) {
            System.out.println("Der er ingen udgang den vej!");
            return false;
        }

            player.addMove(); // lægger en til move!
            currentRoom = nextRoom;   
            System.out.println(currentRoom.getLongDescription());

        // tjekker om der er noget i rummets inventory
        if (currentRoom.getRoomInv().getSize() == 0) {
            return false;
        }

        // printer inventory tekst afhængig af rum
        if (currentRoom == outside) {
            System.out.println("Dit hus har:");
        } else if (currentRoom == store) {
            System.out.println("Du kan købe:");
        } else {
            System.out.println("Rummmet indeholder:");
        }

        currentRoom.printRoomInv();

        return false;
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit hvad?");
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
        System.out.println("Du har nu: ");
        player.getInventory().printInventory();
    }

    private void replace(Command command) {
        // Undersøger om du er i butikken
        if (inShop()) {
            System.out.println("fejl: du kan kun handle når du er i butikken!");
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
        System.out.println(player.getWallet());
    }

    private void inventory() {
        System.out.print("Spiller ");
        player.getInventory().printInventory();
    }

    private void copyItem(Inventory sourceInventory, int itemIndex, Inventory destInventory) {
        destInventory.addItem(sourceInventory.getItem(itemIndex));
    }

    private boolean nextRound() {
        System.out.println("Du har nu afsluttet " + (player.getRounds() +1 ) + ". år");
        printStatus();

        // checker om vi har nået max antal runder
        if (player.getRounds() == (player.getMaxNumberOfRounds() -1 ) ) {
            System.out.println("Du har spillet max antal år\n");
            return true; // spillet er slut
        }

        player.saveRoundScore(); // gemmer scoren for runden
        player.setRounds(player.getRounds()+1); // opdatere rounds

        player.setWallet(player.getStartAmount() + player.getScore()); // nyt årsbudget!
        System.out.println("Velkommen til år " + player.getRounds());
        System.out.println("Dit nye årsbudget er " + player.getWallet());
        System.out.println();

        currentRoom = outside;

        return false;



    }

    private void printStatus() {
        System.out.println("Du har samlet sparet " + player.getScore() + " kr. om året i energiforbedringer");
        System.out.println("Og har brugt " + (player.getStartAmount() - player.getWallet()) + " kr,-");
        System.out.println("Du startede med energimærke " + EnergyLabel.createEnergyLabel(0,player.getStartValue()));
        System.out.println("Du er nu på energimærke " + EnergyLabel.createEnergyLabel(player.getScore(), player.getStartValue()));

    }
}
