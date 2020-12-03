package Energy.domain;

import java.util.Scanner;

public class Game {
    private Parser parser;
    private Player player;
    private Room currentRoom; // holder styr på det rum man befinder sig i
    Room store, outside, utility, bathroom, bedroom, kidsRoom, room, kitchen, livingRoom, corridor1, corridor2, corridor3, corridor4; // liste over rum

    public final int WASHINGMACHINE = 1;
    public final int DRYER = 2;
    public final int HEATING = 3;
    public final int STOVE = 4;
    public final int FRIDGE = 5;
    public final int DISHWASHER = 6;
    public final int WINDOW = 7;
    public final int LIGHTS = 8;
    public final int TV = 9;
    public final int WALLFIXER = 10;
    public final int ISOLATION = 11;
    public final int SOLARCELLS = 12;
    public final int BATH = 13;

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
        store = new Store("nu i Super Byg, her kan du købe tingene til huset", "Store");
        outside = new Outside("ude foran huset", "Outside");
        utility = new Room("i bryggerset", "Utility");
        bathroom = new Room("i badeværelset", "Bathroom");
        bedroom = new Room("i soveværelsen","Bedroom");
        kidsRoom = new Room("i børneværelset", "KidsRoom");
        room = new Room("i værelset", "Room");
        kitchen = new Room("i køkkenet","Kitchen");
        livingRoom = new Room("i stuen","LivingRoom");
        corridor1 = new Room("i første del af gangen", "Corridor1");
        corridor2 = new Room("i anden del af gangen", "Corridor2");
        corridor3 = new Room("i tredje del af gangen","Corridor3");
        corridor4 = new Room("i fjerde del af gangen","Corridor4");

        // opretter udgange
        store.setExit("øst", outside); //Fra butikken kan man gå outside

        outside.setExit("vest", store); //fra outside kan man gå i butikken og gang 1
        outside.setExit("øst", corridor1);

        corridor1.setExit("nord", utility); //fra gang 1 kan man gå i utility, kitchen, gang 2 og outside
        corridor1.setExit("syd", kitchen);
        corridor1.setExit("øst", corridor2);
        corridor1.setExit("vest", outside);

        utility.setExit("syd", corridor1); //fra utility kan man gå i gang 1

        kitchen.setExit("nord", corridor1); //fra kitchen kan man gå til gang 1 og livingRoom
        kitchen.setExit("øst", livingRoom);

        livingRoom.setExit("vest", kitchen); //fra livingRoom kan man gå til kitchen og gang 3
        livingRoom.setExit("nord", corridor3);

        corridor2.setExit("nord", bathroom); //fra gang 2 kan man gå til gang 1, gang 3 og bathroom
        corridor2.setExit("øst", corridor3);
        corridor2.setExit("vest", corridor1);

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

        // tilføj til inventory
        //add to inventory
        store.addToInventory(new Item("Opvaskemaskine A", 2500, 150, DISHWASHER, "A"));
        store.addToInventory(new Item("Opvaskemaskine A++", 3300, 200, DISHWASHER, "A++"));
        store.addToInventory(new Item("Vaskemaskine A+", 2400, 65, WASHINGMACHINE, "A+"));
        store.addToInventory(new Item("Vaskemaskine A++", 3000, 114, WASHINGMACHINE, "A++"));
        store.addToInventory(new Item("Vaskemaskine A+++", 3850, 157, WASHINGMACHINE, "A+++"));
        store.addToInventory(new Item("Tørretumbler A+", 2887, 145, DRYER, "A+"));
        store.addToInventory(new Item("Tørretumbler A++", 3499, 189, DRYER, "A++"));
        store.addToInventory(new Item("Tørretumbler A+++", 4318, 243, DRYER, "A+++"));
        store.addToInventory(new Item("Køleskab A+", 3375, 221, FRIDGE, "A+"));
        store.addToInventory(new Item("Køleskab A++", 4875, 308, FRIDGE, "A++"));
        store.addToInventory(new Item("Køleskab A+++", 6000, 394, FRIDGE, "A+++"));
        store.addToInventory(new Item("Energibesparende komfur", 4500, 284, STOVE, null));
        store.addToInventory(new Item("Energibesparende TV", 5900, 375, TV, null));
        store.addToInventory(new Item("Termorude (2 lag)", 1250, 175, WINDOW, null));
        store.addToInventory(new Item("Sparepære", 200, 50, LIGHTS, null));
        store.addToInventory(new Item("LED-pære", 600, 170, LIGHTS, null));
        store.addToInventory(new Item("Pillefyr (varmeanlæg)", 22500, 2500, HEATING, null));
        store.addToInventory(new Item("Gas (varmeanlæg)", 25000, 3000, HEATING, null));
        store.addToInventory(new Item("Hul-fikser-kit", 1500, 1500, WALLFIXER, null));
        store.addToInventory(new Item("Isolering", 10000, 750, ISOLATION, null));
        store.addToInventory(new Item("Solceller", 30000, 3500, SOLARCELLS, null));

        utility.addToInventory(new Item("Vaskemaskine D", 0, 0, WASHINGMACHINE, "D"));
        utility.addToInventory(new Item("Tørretumbler D", 0, 0, DRYER, "D"));
        utility.addToInventory(new Item("Oliefyr", 0, 0, HEATING, null));

        kitchen.addToInventory(new Item("Køleskab D", 0, 0, FRIDGE, "D")); // færdig
        kitchen.addToInventory(new Item("Komfur C", 0, 0, STOVE, "C"));
        kitchen.addToInventory(new Item("Opvaskemaskine D", 0, 0, DISHWASHER, "D"));

        livingRoom.addToInventory(new Item("Enkeltlags vindue", 0, 0, WINDOW, null));
        livingRoom.addToInventory(new Item("Glødepære", 0, 0, LIGHTS, null));
        livingRoom.addToInventory(new Item("TV D", 0, 0, TV, "D"));

        bedroom.addToInventory(new Item("Glødepære", 0, 0, LIGHTS, null));
        bedroom.addToInventory(new Item("Enkeltlags vindue", 0, 0, WINDOW, null));
        bedroom.addToInventory(new Item("Hul i væggen", 0, 0, WALLFIXER, null));

        room.addToInventory(new Item("Glødepære", 0, 0, LIGHTS, null));
        room.addToInventory(new Item("Enkeltlags vindue", 0, 0, WINDOW, null));

        kidsRoom.addToInventory(new Item("Glødepære", 0, 0, LIGHTS, null));
        kidsRoom.addToInventory(new Item("Enkeltlags vindue", 0, 0, WINDOW, null));
        kidsRoom.addToInventory(new Item("Hul i væggen", 0, 0, WALLFIXER, null));

        bathroom.addToInventory(new Item("Bruser D", 0, 0, BATH, "D"));

        outside.addToInventory(new Item("Tag uden solceller", 0, 0, SOLARCELLS, null));
        outside.addToInventory(new Item("Tyndt isolering", 0, 0, ISOLATION, null));

        // sætter startrummet til outside
        currentRoom = kitchen;
    }

    public void play() {

        printWelcome(); // opstart af spil

        boolean gameFinished = false;

        while (!gameFinished) {
            Command command = parser.getCommand();
            gameFinished = processCommand(command);
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
        System.out.println("--- Tak for, at du spillede vores spil ---\n");
        printStatus();
        System.out.println("\nLavet af: Yusuf Bayoz, Victor Poulsen, Emil Spangenberg, Theis Langlands & Nicolaj Hansen");
    } // afslutning af spil

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("Jeg ved ikke, hvad du mener. Skriv 'hjælp' for at se dine kommandoer.");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            wantToQuit = goRoom(command);
        } else if (commandWord == CommandWord.NEWROUND) {
            wantToQuit = newRound();
        } else if (commandWord == CommandWord.INVENTORY) {
            inventory();
        } else if (commandWord == CommandWord.DELETE) {
            delete(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.BUY) {
            buy(command);
        } else if (commandWord == CommandWord.REPLACE) {
            wantToQuit = replace(command);
        } else if (commandWord == CommandWord.STATUS){
            status();
        }
        return wantToQuit;
    }

    private boolean newRound() {
        return nextRound();
    }

    private void printHelp() {
        System.out.println("");
        System.out.println("Dine kommandoer er:\n");
        parser.showCommands();
        System.out.println("\nKommandoen: 'nytår' starter et nyt år.");
        System.out.println("Kommandoen: 'afslut' afslutter spillet.");
        System.out.println("Kommandoen: 'køb' NUMMER køber en ting fra butikken.");
        System.out.println("Kommandoen: 'udskift' NUMMER udskifter en ting i rummet eller udenfor.");
        System.out.println("Kommandoen: 'hjælp' printer dette igen.");
        System.out.println("Kommandoen: 'skrot' NUMMER skrotter og fjerner en ting fra dit inventar.");
        System.out.println("Kommandoen: 'inventar' viser dit inventar.");
        System.out.println("Kommandoen: 'gå' RETNING bevæger dig rundt.");
        System.out.println("Kommandoen: 'status' fortæller din årlige besparelse, og dit budget for året.");
    }

    private boolean goRoom(Command command) {

        // Tjekker om der er et ord efter go i kommando
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

        // lægger en til move og tjekker om vi er på max
        player.addMove();
        if (player.getMoves() == player.getMovesPerRound()) {
            System.out.println("Du har nået max antal bevægelser i dette år.");
            return nextRound();
        }

            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());

        return false;
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Afslut hvad?");
            return false;
        } else {
            return true;
        }
    }

    String buy(Command command) {

        // Tjekker CLI conditions?

        // Undersøger om du er i butikken
        if (!inShop()) {
            System.out.println("Du kan kun handle i butikken!");
            return null;
        }

        // undersøger om kommandoen har et andet ord
        if (!command.hasSecondWord()) {
            System.out.println("Køb Hvad?");
            return null;
        }

        // Tjekker at det andet ord (string) kan parses til en Integer
       if (!isInt(command.getSecondWord())) {
           System.out.println("Dette er ikke et gyldigt nummer!");
           return null;
       }

        // laver kommandoword om til int og finder index af det der skal købes
        int index = Integer.parseInt(command.getSecondWord()) - 1;

        // Tjekker om index er mellem 0 og butikkens max antal varer
        if (0 > index || index+1 > store.getRoomInv().getSize() ) {
            System.out.println("Dette er ikke et gyldigt nummer!");
            return null;
        }


        // TJEKKER BETINGELSER FOR KØB
        // finder pris på det der skal købes
        int price = store.getRoomInv().getItem(index).getPrice();

        // tjekker om spiller har råd
        if (player.getWallet() < price) {
            System.out.println("Du har ikke råd!");
            return "Det har du ikke råd til!";
        }

        // Undersøger om spillers inventory er fyldt
        if (player.getInventory().getSize() == player.getInventory().getMaxSize()) {
            System.out.println("Inventar er fyldt!");
            return "Du har ikke plads til mere!";
        }

        // kopierer fra butikkens inventory (index) til players inventory
        copyItem(store.getRoomInv(), index, player.getInventory());

        // fratrækker købet fra players wallet og tilføjer til totalforbrug
        int amount = player.getWallet() - price;
        player.setWallet(amount);
        player.addAmountToTotal(price);

        // udskriver køb
        System.out.println("Du har købt " + store.getRoomInv().getItem(index).getName() + "\n");
        return "Du har købt " + store.getRoomInv().getItem(index).getName();
    }

    private boolean replace(Command command) {

        Integer roomInvIndex = checkReplaceConditions(command);
        if (roomInvIndex == null) return false;

        // Finder index i players inventory.
        int playerInvIndex = getPlayerInvIndex(roomInvIndex);

        // tjekker om spiller har item i inventar
        if (playerInvIndex==-1) {
            System.out.println("Du har ikke den type i dit inventar, gå i Super Byg!");
            return false;
        }

        insertItem(roomInvIndex, playerInvIndex);

        // Udskriver inventory fra player og Room
        System.out.print("Du har: ");
        player.getInventory().printInventory();
        System.out.print("Rummet indeholder: ");
        currentRoom.getRoomInv().printInventory();
        System.out.println("Du har nu opnået en årlig besparelse på: " + player.getScore());

        // Tjekker om spiller har råd til at købe flere item - ellers gåes videre til næste runde!
        if (    (player.getInventory().isEmpty()) &&
                (player.getWallet() < store.getRoomInv().cheapestItem()) ) {
            System.out.println("Du har brugt dette års budget, og har ikke råd til mere i butikken");
            return nextRound();
        }
        return false;
    }

    void insertItem(Integer roomInvIndex, int playerInvIndex) {
        // Indsætter Item i room inventory fra player inventory

        // Fjerner gammelt Item fra Room
        currentRoom.getRoomInv().removeItem(currentRoom.getRoomInv().getItem(roomInvIndex));

        // Kopierer item fra player index til room
        copyItem(player.getInventory(), playerInvIndex, currentRoom.getRoomInv());

        // Opdaterer score
        int nyScore = player.getScore() + player.getInventory().getItem(playerInvIndex).getScoreImpact();
        player.setScore(nyScore);

        // Sletter item fra player index
        player.getInventory().removeItem(player.getInventory().getItem(playerInvIndex));
    }

    int getPlayerInvIndex(int roomInvIndex) {
        // Finder index i players inventory returnere -1 hvis der ikke er Item af samme type.)

        int playerInvIndex = -1;
        int itemTypeRoom = currentRoom.getRoomInv().getItem(roomInvIndex).getItemType();

        for (int i = 0; i < player.getInventory().getSize(); i++) {
            if (player.getInventory().getItem(i).getItemType() == itemTypeRoom) {
                playerInvIndex = i;
            }
        }
        return playerInvIndex;
    }

    private Integer checkReplaceConditions(Command command) {
        // Undersøger om du er i butikken
        if (inShop()) {
            System.out.println("Du kan kun handle når du er i butikken!");
            return null;
        }

        // undersøger om kommandoen har et andet ord
        if (!command.hasSecondWord()) {
            System.out.println("Udskift hvad?");
            return null;
        }

        // Tjekker at det andet ord (string) kan parses til en Integer
        if (!isInt(command.getSecondWord())) {
            System.out.println("Dette er ikke et gyldigt nummer!");
            return null;
        }

        // laver kommandoword om til int og finder index af det der skal udskiftes
        int index = Integer.parseInt(command.getSecondWord()) - 1;

        // Tjekker om index er mellem 0 og rummets max antal items
        if (0 > index || index + 1 > currentRoom.getRoomInv().getSize()) {
            System.out.println("Dette er ikke et gyldigt nummer!");
            return null;
        }
        return index;
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

    private void status() {
    System.out.println("Du har opnået en samlet årlig besparelse på: " + player.getScore() + ", og du har " + player.getWallet() + "kr. tilbage på budgettet i år");
    }

    private void delete(Command command) {

        // undersøger om kommandoen har et andet ord
        if (!command.hasSecondWord()) {
            System.out.println("Slet hvad?");
            return;
        }

        // Tjekker at det andet ord (string) kan parses til en Integer
        if (!isInt(command.getSecondWord())) {
            System.out.println("Dette er ikke et gyldigt nummer!");
            return;
        }

        // laver kommandoword om til int og finder index af det der skal udskiftes
        int index = Integer.parseInt(command.getSecondWord()) - 1;

        // Tjekker om index er mellem 0 og player items
        if (0 > index || index + 1 > player.getInventory().getSize()) {
            System.out.println("Dette er ikke et gyldigt nummer!");
            return;
        }

        // fjerner Item fra inventory
        player.getInventory().removeItem(player.getInventory().getItem(index));

        // printer ny inventory
        System.out.println("Nyt inventar:");
        player.getInventory().printInventory();

    }

    // skal den ikke slettes - indeholdt i status? - måske skal den bruges i GUI?
    private void wallet(){
        System.out.println(player.getWallet());
    }

    private void inventory() {
        if (player.getInventory().isEmpty()) {
            System.out.println("Du har ikke nogle ting, tag en tur i Superbyg");
        } else {
            System.out.print("Du har: \n");
            System.out.println(player.getInventory().printInventory());
        }
    }

    private void copyItem(Inventory sourceInventory, int itemIndex, Inventory destInventory) {
        destInventory.addItem(sourceInventory.getItem(itemIndex));
    }

    private boolean nextRound() {
        System.out.println("\nDu har nu afsluttet " + (player.getRounds() +1 ) + ". år");

        // checker om vi har nået max antal runder
        if (player.getRounds() == (player.getMaxNumberOfRounds() -1 ) ) {
            System.out.println("Du har spillet max antal år\n");
            return true; // spillet er slut - sætter want to quit til True
        }

        // Udskriver status
        printStatus();

        // gemmer score og opdatere runde count
        player.saveRoundScore();
        player.setRounds(player.getRounds()+1);

        // intialiserer nyt år
        player.setWallet(player.getStartAmount() + player.getScore()); // nyt årsbudget!
        player.setMoves(0); // resetter moves

        // Udskriver velkommen til nyt år
        System.out.println("\n --- Velkommen til år " + (player.getRounds() + 1) + " ---");
        System.out.println("Dit nye årsbudget er " + player.getWallet());
        System.out.println();

        currentRoom = outside;

        return false;
    }

    private void printStatus() {
        System.out.println("Du har samlet brugt " + player.getTotalUsedAmount() + " kr,-\n");
        System.out.println(" - Energibesparelse -");
        for (int i=0; i<=player.getRounds(); i++) {
            System.out.println("År " + (i+1) + ": " + player.getRoundScore(i) );
        }
        System.out.println("Total " + player.getScore() + " kr. om året i energiforbedringer");
        System.out.println("\nDu startede med energimærke " + EnergyLabel.createEnergyLabel(0,player.getStartValue()));
        System.out.println("og er nu på energimærke " + EnergyLabel.createEnergyLabel(player.getScore(), player.getStartValue()));
    }

    // GETTERS & SETTERS
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room newCurrentRoom) {
        this.currentRoom = newCurrentRoom;
    }

    public Player getPlayer() {
        return player;
    }
}
