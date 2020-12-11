package Energy.domain;

class Game {
    // ATTRIBUTTER
    private Player player;
    private Room currentRoom; // holder styr på det rum man befinder sig i
    Room store, outside, utility, bathroom, bedroom, kidsRoom, room, kitchen, livingRoom, corridor1, corridor2, corridor3, corridor4; // liste over rum

    private final int MAX_START_AMOUNT = 100000; // top grænsen for spillerens budget
    private final int START_VALUE = 30500; // start forbrug (til Energimærkeberegning)
    private final int MOVES_PER_ROUND = 40; // max antal træk pr. år
    private final static int MAX_NUMBER_OF_ROUNDS = 5;

    // CONSTRUCTORS
    public Game() {
        // opretter nyt spil
        createRooms(); // opretter rum, udgange og indhold
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

    public int getMovesPerRound() {
        return MOVES_PER_ROUND;
    }

    public static int getMaxNumberOfRounds() {
        return MAX_NUMBER_OF_ROUNDS;
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
        // konstanter til Item - typer
        final int WASHINGMACHINE = 1;
        final int DRYER = 2;
        final int HEATING = 3;
        final int STOVE = 4;
        final int FRIDGE = 5;
        final int DISHWASHER = 6;
        final int WINDOW = 7;
        final int LIGHTS = 8;
        final int TV = 9;
        final int WALLFIXER = 10;
        final int ISOLATION = 11;
        final int SOLARCELLS = 12;
        final int BATH = 13;

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
        store.addToInventory(new Item("Tyk isolering", 10000, 750, ISOLATION, null));
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

    String setStartAmount(String input) {
        // returnerer fejlmeddelelse ved fejlinput ellers sættes startbeløb
        int value = 0;
        try {
            value = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // invalid input
        }

        if (value > 0 && value <= MAX_START_AMOUNT) {
            player.setWallet(value);
            player.setStartAmount(value);
            return null;
        }
        return "Fejl: Indtast beløb mellem 0 og " + MAX_START_AMOUNT + " kr.";
    }

    // metorder til buy og replace
    String buy(int index) {
        // returnerer streng med evt. fejlmeddelelse

        // finder pris på det der skal købes
        int price = store.getRoomInv().getItem(index).getPrice();

        // tjekker om spiller har råd
        if (player.getWallet() < price) {
            return "Det har du ikke råd til!";
        }

        // Undersøger om spillers inventory er fyldt
        if (player.getInventory().getSize() == player.getInventory().getMaxSize()) {
            return "Du har ikke plads til mere!";
        }

        // kopierer fra butikkens inventory (index) til players inventory
        player.getInventory().addItem(store.getRoomInv().getItem(index));

        // fratrækker købet fra players wallet og tilføjer til totalforbrug
        int amount = player.getWallet() - price;
        player.setWallet(amount);
        player.addAmountToTotal(price);

        // udskriver køb
        return "Du har købt " + store.getRoomInv().getItem(index).getName();
    }

    boolean inShop() {
        return currentRoom == store;
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

    // metoder til ny runde kommando

    public boolean nextRound() {
        // starter ny runde, returnerer false, hvis max runder er udført!

        // tjekker om vi er nået max antal runder
        if (player.getRounds() == (MAX_NUMBER_OF_ROUNDS - 1)) {
            player.setRounds((player.getRounds()) + 1);
            return false;
        }

        // intialiser nyt år
        player.setRounds(player.getRounds() + 1); // opdatere runde count
        player.setWallet(player.getWallet() + player.getStartAmount() + player.getScore()); // nyt årsbudget!
        player.setMoves(0); // resetter moves
        currentRoom = outside; // sætter startrummet
        return true;
    }

    //  metoder til udskrift

    String welcomeText() {
        String result;
        result = "Du befinder dig i et dansk parcelhus på 160 m2\n" +
                "Dit årlige forbrug er på " + START_VALUE +
                ", hvilket giver huset energimærket " + EnergyLabel.createEnergyLabel(player.getScore(), START_VALUE) + "\n\n" +
                "Din mission er at forbedre din boligs energiforbrug\n\n" +
                "Du kan købe mere energivenlige produkter til dit hus i Super Byg, " +
                "så din bolig bruger mindre energi, og dermed opnår bedre energimærke\n\n" +
                "Du skal opnå de størst mulige forbedringer med det tilgængelige budget\n\n" +
                "Spillet løber over en årrække\n" +
                "Når du har brugt dit budget, eller efter " + MOVES_PER_ROUND + " bevægelser, skiftes der til et nyt år\n";
        return result;
    }

    public String nextRoundText() {
        // opdaterer runde score
        player.saveRoundScore();

        // opretter streng til udskrift
        String result;
        result = "\nDu har nu afsluttet " + ((player.getRounds()) + 1) + ". år\n";
        result += statusText();
        return result;
    }

    String statusText() {
        String result;
        result = "Du har samlet brugt " + player.getTotalUsedAmount() + " kr.\n" +
                " - Energibesparelse -\n";

        for (int i = 0; i <= player.getRounds(); i++) {
            result += " År " + (i + 1) + ": " + player.getRoundScore(i) + "\n";
        }

        result += "Du har samlet opnået " + player.getScore() + " kr. om året i energiforbedringer\n" +
                "\nDu startede med energimærke " + EnergyLabel.createEnergyLabel(0, START_VALUE) + "\n" +
                "og er nu på energimærke " + EnergyLabel.createEnergyLabel(player.getScore(), START_VALUE);

        return result;
    }

    String endGameText() {
        String result = "";

        // Tilføjer tekst, hvis slutskærmen vises pga max antal år
        if (getPlayer().getRounds() == MAX_NUMBER_OF_ROUNDS) {
            result += " --- Du har spillet max antal år --- \n\n";
            getPlayer().setRounds((getPlayer().getRounds()) - 1);
        }

        result += statusText();
        result += "\n\nLavet af: Yusuf Baysoz, Victor Poulsen, Emil Spangenberg, Theis Langlands & Nicolaj Hansen";
        result += "\n--- Tak for, at du spillede vores spil ---\n";

        return result;
    }
}
