package worldofzuul;

public class Game 
{
    private Parser parser;
    private Room currentRoom; // holder styr på det rum man befinder sig i
        

    public Game() // opretter nyt spil
    {
        createRooms(); // kalder createRooms() sætter rum og udgange
        parser = new Parser();
    }


    private void createRooms()
    {
        Room butik, udenfor, bryggers, badeværelse, soveværelse, børneværelse, værelse, køkken, stue, gang1, gang2, gang3, gang4; // liste over rum
        // opretter rum og beskrivelse
        butik = new Room("nu i Super Duper Byg, her kan du købe tingene til huset");
        udenfor = new Room("ude foran huset");
        bryggers = new Room("i bryggerset");
        badeværelse = new Room("i badeværelset");
        soveværelse = new Room("i soveværelset");
        børneværelse = new Room("i børneværelset");
        værelse = new Room("i værelset");
        køkken = new Room("i køkkenet");
        stue = new Room("i stuen");
        gang1 = new Room("i første del af gangen");
        gang2 = new Room("i anden del af gangen");
        gang3 = new Room("i tredje del af gangen");
        gang4 = new Room("i fjerde del af gangen");

        // opretter udgange
        butik.setExit("east", udenfor); //Fra butikken kan man gå udenfor

        udenfor.setExit("west", butik); //fra udenfor kan man gå i butikken og gang 1
        udenfor.setExit("east", gang1);

        gang1.setExit("north", bryggers); //fra gang 1 kan man gå i bryggerset, køkkenet, gang 2 og udenfor
        gang1.setExit("south", køkken);
        gang1.setExit("east", gang2);
        gang1.setExit("west", udenfor);

        bryggers.setExit("south", gang1); //fra bryggerset kan man gå i gang 1

        køkken.setExit("north", gang1); //fra køkkenet kan man gå til gang 1 og stuen
        køkken.setExit("east", stue);

        stue.setExit("west", køkken); //fra stuen kan man gå til køkkenet og gang 3
        stue.setExit("north", gang3);

        gang2.setExit("north", badeværelse); //fra gang 2 kan man gå til gang 1, gang 3 og badeværelse
        gang2.setExit("east", gang3);
        gang2.setExit("west", gang1);

        badeværelse.setExit("south", gang2); //fra badeværelset kan man gå til gang 2

        gang3.setExit("north", soveværelse); //fra gang 3 kan man gå til gangn 2, gang 4, soveværelse og stuen
        gang3.setExit("south", stue);
        gang3.setExit("east", gang4);
        gang3.setExit("west", gang2);

        soveværelse.setExit("south", gang3); //fra soveværelse kan man gå til gang 3

        gang4.setExit("north", børneværelse); //fra gang 4 kan man gå til gang 3, børneværelse og værelset
        gang4.setExit("south", værelse);
        gang4.setExit("west", gang3);

        børneværelse.setExit("south", gang4); //fra børneværelset kan man gå til gang 4

        værelse.setExit("north", gang4); //fra værelset kan man gå til gang 4

        // sætter startrummet til udenfor
        currentRoom = udenfor;
    }

    public void play() 
    {            
        printWelcome(); // velkomst hilsen

                
        boolean finished = false;
        while (! finished) {
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

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}
