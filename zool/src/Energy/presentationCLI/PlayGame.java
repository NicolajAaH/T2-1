package Energy.presentationCLI;

import Energy.Interface.DomainI;
import Energy.domain.DomainConnect;

class PlayGame {
    private Parser parser = new Parser();

    //vores 'spil' og vores bindeled mellem domain og interface
    private DomainI domainI = new DomainConnect();

    //getter til domainI
    public DomainI getDomainI() {
        return domainI;
    }

    public void playCLI() {

        printWelcome();

        boolean gameFinished = false;

        while (!gameFinished) {
            Command command = parser.getCommand();
            gameFinished = processCommand(command);
        }

        printExit();
    }

    public void printWelcome() {
        // velkomst tekst til CLI
        System.out.println(domainI.welcomeText());

        domainI.setStartAmountCLI();

        System.out.println("\nSkriv '" + CommandWord.HELP + "' hvis du har brug for hjælp.\n");
        System.out.println(domainI.getRoomDescriptionText());
    }

    public void printExit() {
        // Udskriver sluttekst - CLI
        System.out.println(domainI.endGameText());
    }

    private boolean processCommand(Command command) {

        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            printUnknown();
        } else if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            wantToQuit = goRoom(command);
        } else if (commandWord == CommandWord.NEWROUND) {
            wantToQuit = nextRoundCLI();
        } else if (commandWord == CommandWord.INVENTORY) {
            printInventory();
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.BUY) {
            buyCLI(command);
        } else if (commandWord == CommandWord.REPLACE) {
            wantToQuit = replaceCLI(command);
        } else if (commandWord == CommandWord.STATUS) {
            printStatus();
        }
        return wantToQuit;
    }

    private void printUnknown(){
        System.out.println("Jeg ved ikke, hvad du mener. Skriv 'hjælp' for at se dine kommandoer.");
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
        System.out.println("Kommandoen: 'printStatus' fortæller din årlige besparelse, og dit budget for året.");
    }

    private void printInventory() {
        if (domainI.getPlayerInventory().isEmpty()) {
            System.out.println("Du har ikke nogle ting, tag en tur i Superbyg");
        } else {
            System.out.print("Du har: \n");
            System.out.println(domainI.getPlayerInventory());
        }
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Afslut hvad?");
            return false;
        }
        return true;
    }

    private void printStatus() {
        System.out.println("Du har opnået en samlet årlig besparelse på: " + domainI.getScore() +
                ", og du har " + domainI.getWallet() + "kr. tilbage på budgettet i år");
    }

    // metoder til køb og replace kommandoer
    void buyCLI(Command command) {
        String errorCheck = checkBuyConditionsCLI(command);
        if (errorCheck != null) {
            System.out.println(errorCheck); // udskriver hvis fejl!
            return;
        }

        // finder index til replacement
        int index = Integer.parseInt(command.getSecondWord()) - 1;

        System.out.println(domainI.buyItem(index)); // udfører køb eller udskriver fejl
        System.out.println("Du har nu\n" + domainI.getPlayerInventory());
    }

    String checkBuyConditionsCLI(Command command) {
        // Tjekker betingelser kun relateret CLI og returnerer fejlmeddelelse hvis ugyldigt input ellers null

        // Undersøger om du er i butikken
        if (!domainI.inShop()) {
            return "Du kan kun handle i butikken!";
        }

        // undersøger om kommandoen har et andet ord
        if (!command.hasSecondWord()) {
            return "Køb Hvad?";
        }

        // Tjekker at det andet ord (string) kan parses til en Integer
        int index = 0;

        try {
            index = Integer.parseInt(command.getSecondWord()) - 1;
        } catch (NumberFormatException e) {
            return "Dette er ikke et gyldigt nummer!";
        }

        // Tjekker om index er mellem 0 og butikkens max antal varer
        if (0 > index || index + 1 > domainI.getStoreInventory().getSize()) {
            return "Dette er ikke et gyldigt nummer!";
        }
        return null;
    }

    private boolean replaceCLI(Command command) {
        // tjekker input
        String error = checkReplaceConditions(command);
        if (error != null) {
            System.out.println(error);
            return false;
        }

        int roomInvIndex = Integer.parseInt(command.getSecondWord()) - 1;

        // tjekker om spiller har item i inventar
        String result = domainI.replaceDynamicGUI(roomInvIndex);
        if (result != null) {
            System.out.println("Du har ikke købt den type, gå i Super Byg!");
            return false;
        }

        // Udskriver inventory fra player og Room
        printInventory();
        System.out.println("Rummet indeholder: ");
        System.out.println(domainI.getRoomInventory());
        System.out.println("Du har nu opnået en årlig besparelse på: " + domainI.getScore());

        // Tjekker om spiller har råd til at købe flere item - ellers gåes videre til næste runde!
        if (!domainI.canAffordMore()) {
            System.out.println("Du har brugt dette års budget, og har ikke råd til mere i butikken");
            return nextRoundCLI();
        }
        return false;
    }

    private String checkReplaceConditions(Command command) {
        // returnerer om det er muligt at udføre replace kommando, returnerer streng med fejl

        // Undersøger om du er i butikken
        if (domainI.inShop()) {
            return "Du kan kun handle når du er i butikken!";
        }

        // undersøger om kommandoen har et andet ord
        if (!command.hasSecondWord()) {
            return "Udskift hvad?";
        }
        // tjekker om andet ord er integer
        int index = 0;
        try {
            index = Integer.parseInt(command.getSecondWord()) - 1;
        } catch (NumberFormatException e) {
            return "Dette er ikke et gyldigt nummer!";
        }

        // Tjekker om index er mellem 0 og rummets max antal items
        if (0 > index || index + 1 > domainI.getRoomInventory().getSize()) {
            return "Dette er ikke et gyldigt nummer!";
        }
        return null;
    }

    private boolean nextRoundCLI() {

        if (!domainI.nextRound()) return true; // hvis next round er false sætte wantToQuit til true

        System.out.println(domainI.nextRoundText());

        // Udskriver velkommen til nyt år
        System.out.println("\n --- Velkommen til år " + (domainI.getRound() + 1) + " ---\n");
        System.out.println("Dit nye årsbudget er " + domainI.getWallet());
        System.out.println();

        return false; // fortsæt spil
    }

    // metoder til processCommand
    private boolean goRoom(Command command) {

        // Tjekker om der er et ord efter go i kommando
        if (!command.hasSecondWord()) {
            System.out.println("Hvilken retning?");
            return false;
        }

        String direction = command.getSecondWord();

        switch(direction){
            case "nord":
                if (domainI.hasNorthExit()) {
                    domainI.goNorth();
                } else {
                    System.out.println("Der er ingen udgang den vej!");
                }
                break;

            case "syd":
                if (domainI.hasSouthExit()) {
                    domainI.goSouth();
                } else {
                    System.out.println("Der er ingen udgang den vej!");
                }
                break;

            case "øst":
                if (domainI.hasEastExit()) {
                    domainI.goEast();
                } else {
                    System.out.println("Der er ingen udgang den vej!");
                }
                break;

            case "vest":
                if (domainI.hasWestExit()) {
                    domainI.goWest();
                } else {
                    System.out.println("Der er ingen udgang den vej!");
                }
                break;

            default:
                System.out.println("Fejl i indtastning");
                return false;

        }
        // lægger en til move og tjekker om vi er på max
        domainI.addMove();
        if (domainI.getMovesRemaing() == 0) {
            System.out.println("Du har nået max antal bevægelser i dette år.");
            return nextRoundCLI();
        }

        /*
        Room nextRoom = currentRoom.getExit(direction);

        // tjekker om der er et rum i den retning
        if (nextRoom == null) {
            System.out.println("Der er ingen udgang den vej!");
            return false;
        }

        // lægger en til move og tjekker om vi er på max
        domainI.addMove();
        if (player.getMoves() == player.getMovesPerRound()) {
            System.out.println("Du har nået max antal bevægelser i dette år.");
            return nextRoundCLI();
        }

        currentRoom = nextRoom;

         */
        System.out.println(domainI.getRoomDescriptionText());

        return false;
    }
}

