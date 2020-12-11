package Energy.domain;

import java.util.Scanner;

class Game {
    // ATTRIBUTTER
    private Parser parser;
    private Player player;
    private Room currentRoom; // holder styr på det rum man befinder sig i
    Room store, outside, utility, bathroom, bedroom, kidsRoom, room, kitchen, livingRoom, corridor1, corridor2, corridor3, corridor4; // liste over rum

    private final int maxStartAmount = 100000; // top grænsen for beløb

    // konstanter til Item - typer
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

    // CONSTRUCTORS
    public Game() {
        // opretter nyt spil
        createRooms(); // opretter rum, udgange og indhold
        parser = new Parser();
        player = new Player();
        player.getInventory().setMaxSize(5);  // sætter max kapacitet i player inventory
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

    // Metoder
    private void createRooms() {

        // Opretter rum og beskrivelser
        store = new Store("nu i Super Byg, her kan du købe tingene til huset", "Store");
        outside = new Outside("ude foran huset", "Outside");
        utility = new Room("i bryggerset", "Utility");
        bathroom = new Room("i badeværelset", "Bathroom");
        bedroom = new Room("i soveværelsen", "Bedroom");
        kidsRoom = new Room("i børneværelset", "KidsRoom");
        room = new Room("i værelset", "Room");
        kitchen = new Room("i køkkenet", "Kitchen");
        livingRoom = new Room("i stuen", "LivingRoom");
        corridor1 = new Room("i første del af gangen", "Corridor1");
        corridor2 = new Room("i anden del af gangen", "Corridor2");
        corridor3 = new Room("i tredje del af gangen", "Corridor3");
        corridor4 = new Room("i fjerde del af gangen", "Corridor4");

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

        // tilføjer varer til butikken
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
        store.addToInventory(new Item("Komfur A+", 4500, 284, STOVE, "A+"));
        store.addToInventory(new Item("TV A+", 5900, 375, TV, "A+"));
        store.addToInventory(new Item("Termorude (2 lag)", 1250, 175, WINDOW, null));
        store.addToInventory(new Item("Sparepære", 200, 50, LIGHTS, null));
        store.addToInventory(new Item("LED-pære", 600, 170, LIGHTS, null));
        store.addToInventory(new Item("Pillefyr (varmeanlæg)", 22500, 2500, HEATING, null));
        store.addToInventory(new Item("Gas (varmeanlæg)", 25000, 3000, HEATING, null));
        store.addToInventory(new Item("Hul-fikser-kit", 1500, 1500, WALLFIXER, null));
        store.addToInventory(new Item("Isolering", 10000, 750, ISOLATION, null));
        store.addToInventory(new Item("Solceller", 30000, 3500, SOLARCELLS, null));
        store.addToInventory(new Item("Bruser A+", 2000, 300, BATH, "A+"));

        // tilføjer ting i rum
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
        outside.addToInventory(new Item("Tynd isolering", 0, 0, ISOLATION, null));

        // sætter startrummet
        currentRoom = outside;
    }

    public void playCLI() {

        printWelcome(); // opstart af spil

        boolean gameFinished = false;
        while (!gameFinished) {
            Command command = parser.getCommand();
            gameFinished = processCommand(command);
        }

        printExit();
    }

    private void setStartAmountCLI() {
        // Henter budget fra bruger
        System.out.print("Indtast dit årlige renoverings budget: ");
        Scanner s = new Scanner(System.in);
        while (true) {
            String value = s.nextLine();
            if (isInt(value)) {
                int value2 = 0;
                try {
                    value2 = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    System.out.println("Værdien er for høj!");
                }
                if (value2 > 0 && value2 <= maxStartAmount) {
                    player.setWallet(value2);
                    player.setStartAmount(value2);
                    break;
                } else {
                    System.out.println("Der må ikke stå bogstaver i beløbet og værdien skal være mellem 0 og " + maxStartAmount + " kr. \nIndtast nyt beløb: ");
                }
            } else {
                System.out.println("Der må ikke stå bogstaver i beløbet og værdien skal være mellem 0 og " + maxStartAmount + " kr. \nIndtast nyt beløb: ");
            }
        }
    }

    String setStartAmountGUI(String value) {
        // metode til GUI input af start amount, returnerer streng med fejlmeddelelse og null ved succes

        int result = 0;

        // Tjekker om input er et heltal
        if (!isInt(value)) {
            return "Fejl: indtast et heltal imellem 0 og " + maxStartAmount;
        }

        // Laver string til integer
        try {
            result = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return "Fejl: Værdien skal være mellem 0 og " + maxStartAmount + " kr.";
        }

        // Tjekker om beløbet er mellem og max grænsen.
        if (!(result > 0 && result <= maxStartAmount)) {
            return "Fejl: Værdien skal være mellem 0 og " + maxStartAmount + " kr.";
        }

        // Sætter startbeløb og husker årlige budget budget
        player.setWallet(result);
        player.setStartAmount(result);
        return null;
    }


    private boolean processCommand(Command command) {
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("Jeg ved ikke, hvad du mener. Skriv 'hjælp' for at se dine kommandoer.");
        } else if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            wantToQuit = goRoom(command);
        } else if (commandWord == CommandWord.NEWROUND) {
            wantToQuit = newRound();
        } else if (commandWord == CommandWord.INVENTORY) {
            printInventory();
        } else if (commandWord == CommandWord.DELETE) {
            delete(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.BUY) {
            buy(command);
        } else if (commandWord == CommandWord.REPLACE) {
            wantToQuit = replaceCLI(command);
        } else if (commandWord == CommandWord.STATUS) {
            status();
        }
        return wantToQuit;
    }

    // metoder til processCommand
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
            return nextRoundCLI();
        }

        currentRoom = nextRoom;
        System.out.println(currentRoom.getLongDescription());

        return false;
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Afslut hvad?");
            return false;
        }
        return true;
    }

    private void status() {
        System.out.println("Du har opnået en samlet årlig besparelse på: " + player.getScore() +
                ", og du har " + player.getWallet() + "kr. tilbage på budgettet i år");
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
        System.out.println(player.getInventory());
    }

    private void printInventory() {
        if (player.getInventory().isEmpty()) {
            System.out.println("Du har ikke nogle ting, tag en tur i Superbyg");
        } else {
            System.out.print("Du har: \n");
            System.out.println(player.getInventory());
        }
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

    // metoder til køb og replace kommandoer
    String buy(Command command) {
        // returnerer streng med fejlmeddelelse

        // Tjekker CLI conditions
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
        if (0 > index || index + 1 > store.getRoomInv().getSize()) {
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
        player.getInventory().addItem(store.getRoomInv().getItem(index));

        // fratrækker købet fra players wallet og tilføjer til totalforbrug
        int amount = player.getWallet() - price;
        player.setWallet(amount);
        player.addAmountToTotal(price);

        // udskriver køb
        System.out.println("Du har købt " + store.getRoomInv().getItem(index).getName() + "\n");
        return "Du har købt " + store.getRoomInv().getItem(index).getName();
    }

    private boolean inShop() {
        return currentRoom == store;
    }

    private boolean replaceCLI(Command command) {
        // tjekker input og returnerer index: eller -1 = ugyldigt input
        int roomInvIndex = checkReplaceConditions(command);
        if (roomInvIndex == -1) return false;

        // Finder index i players inventory.
        int playerInvIndex = getPlayerInvIndex(roomInvIndex);

        // tjekker om spiller har item i inventar
        if (playerInvIndex == -1) {
            System.out.println("Du har ikke den type i dit inventar, gå i Super Byg!");
            return false;
        }

        insertItem(roomInvIndex, playerInvIndex);

        // Udskriver inventory fra player og Room
        printInventory();
        System.out.println("Rummet indeholder: ");
        System.out.println(currentRoom.getRoomInv());
        System.out.println("Du har nu opnået en årlig besparelse på: " + player.getScore());

        // Tjekker om spiller har råd til at købe flere item - ellers gåes videre til næste runde!
        if (!canAffordMore()) {
            System.out.println("Du har brugt dette års budget, og har ikke råd til mere i butikken");
            return nextRoundCLI();
        }
        return false;
    }

    private int checkReplaceConditions(Command command) {
        // Undersøger om du er i butikken
        if (inShop()) {
            System.out.println("Du kan kun handle når du er i butikken!");
            return -1;
        }

        // undersøger om kommandoen har et andet ord
        if (!command.hasSecondWord()) {
            System.out.println("Udskift hvad?");
            return -1;
        }

        // Tjekker at det andet ord (string) kan parses til en Integer
        if (!isInt(command.getSecondWord())) {
            System.out.println("Dette er ikke et gyldigt nummer!");
            return -1;
        }

        // laver kommandoword om til int og finder index af det der skal udskiftes
        int index = Integer.parseInt(command.getSecondWord()) - 1;

        // Tjekker om index er mellem 0 og rummets max antal items
        if (0 > index || index + 1 > currentRoom.getRoomInv().getSize()) {
            System.out.println("Dette er ikke et gyldigt nummer!");
            return -1;
        }
        return index;
    }

    boolean canAffordMore() {
        // tjekker om spiller inventroy er tomt og om spiller har råd til den billigste ting i butikken
        if ((player.getInventory().isEmpty()) &&
                (player.getWallet() < store.getRoomInv().cheapestItem())) {
            return false;
        }
        return true;
    }

    void insertItem(int roomInvIndex, int playerInvIndex) {

        // Gemmer scoren af items
        // old_score = scoren af det item der skiftes,
        // new_score = scoren på det item der sættes ind
        int old_score = currentRoom.getRoomInv().getItem(roomInvIndex).getScoreImpact();
        int new_score = player.getInventory().getItem(playerInvIndex).getScoreImpact();

        // Erstatter Item i room inventory med et item fra player inventory
        currentRoom.getRoomInv().replaceItem(roomInvIndex, player.getInventory().getItem(playerInvIndex));

        // Opdaterer score
        int nyScore = player.getScore() + (new_score - old_score);
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

    private boolean isInt(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }

    // metoder til ny runde kommando
    private boolean newRound() {
        return nextRoundCLI();
    }

    private boolean nextRoundCLI() {
        System.out.println("\nDu har nu afsluttet " + (player.getRounds() + 1) + ". år\n");

        // checker om vi har nået max antal runder
        if (player.getRounds() == (player.getMaxNumberOfRounds() - 1)) {
            getPlayer().setRounds((getPlayer().getRounds()) + 1);
            return true; // spillet er slut - sætter want to quit til True
        }

        // opdaterer runde sore
        player.saveRoundScore();

        // Udskriver status
        System.out.println(statusText());

        // opretter ny runde!
        initNewRound();

        // Udskriver velkommen til nyt år
        System.out.println("\n --- Velkommen til år " + (player.getRounds() + 1) + " ---\n");
        System.out.println("Dit nye årsbudget er " + player.getWallet());
        System.out.println();

        return false;
    }

    void initNewRound() {
        // opdatere runde count
        player.setRounds(player.getRounds() + 1);

        // intialiserer nyt år
        player.setWallet(player.getWallet() + player.getStartAmount() + player.getScore()); // nyt årsbudget!
        player.setMoves(0); // resetter moves
        currentRoom = outside;
    }

    //  metoder til udskrift
    private void printExit() {
        // Udskriver sluttekst - CLI
        System.out.println(endGameText());
    }

    String statusText() {
        String result;
        result = "Du har samlet brugt " + player.getTotalUsedAmount() + " kr,-\n" +
                " - Energibesparelse -\n";

        for (int i = 0; i <= player.getRounds(); i++) {
            result += " År " + (i + 1) + ": " + player.getRoundScore(i) + "\n";
        }

        result += "Total " + player.getScore() + " kr. om året i energiforbedringer\n" +
                "\nDu startede med energimærke " + EnergyLabel.createEnergyLabel(0, player.getStartValue()) + "\n" +
                "og er nu på energimærke " + EnergyLabel.createEnergyLabel(player.getScore(), player.getStartValue());

        return result;
    }

    public String endGameText() {
        String result = "";

        // Tilføjer tekst, hvis slutskærmen vises pga max antal år
        if (getPlayer().getRounds() == getPlayer().getMaxNumberOfRounds()) {
            result += " --- Du har spillet max antal år --- \n";
            getPlayer().setRounds((getPlayer().getRounds()) - 1);
        }

        result += statusText();
        result += "\n\nLavet af: Yusuf Baysoz, Victor Poulsen, Emil Spangenberg, Theis Langlands & Nicolaj Hansen";
        result += "\n--- Tak for, at du spillede vores spil ---\n";

        return result;
    }

    private void printWelcome() {
        // velkomst tekst til CLI
        System.out.println(welcomeText());

        setStartAmountCLI();

        System.out.println("\nSkriv '" + CommandWord.HELP + "' hvis du har brug for hjælp.\n");
        System.out.println(currentRoom.getLongDescription());
    }

    String welcomeText() {
        String result;
        result = "Du befinder dig i et dansk parcelhus på 160 m2\n" +
                " Dit årlige forbrug er på " + player.getStartValue() +
                " som giver huset energimærke " + EnergyLabel.createEnergyLabel(player.getScore(), player.getStartValue()) + "\n\n" +
                "Din mission er at forbedre din boligs energiforbrug\n\n" +
                "Du kan købe mere energivenlige produkter til dit hus i Super Byg, " +
                "så din bolig bruger mindre energi, og dermed opnår bedre energimærke\n\n" +
                "Du skal opnå de størst mulige forbedringer med det tilgængelige budget\n\n" +
                "Spillet løber over en årrække, når du har brugt dit budget,\n" +
                "eller efter " + player.getMovesPerRound() + " bevægelser skiftes til nyt år\n";
        return result;
    }
}
