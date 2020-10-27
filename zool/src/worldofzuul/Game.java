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
        Room store, outside, utlity, bathroom, bedroom, kidsRoom, room, kitchen, livingRoom, corrridor1, corridor2, corridor3, corridor4; // liste over rum
        // create room and description
        // translate from danish to english
        
        store = new Room("nu i Super Duper Byg, her kan du købe tingene til huset");
        outside = new Room("ude foran huset");
        utlity = new Room("i bryggerset");
        bathroom = new Room("i badeværelset");
        bedroom = new Room("i badeværelse");
        kidsRoom = new Room("i børneværelset");
        room = new Room("i værelset");
        kitchen = new Room("i køkkenet");
        livingRoom = new Room("i stuen");
        corrridor1 = new Room("i første del af gangen");
        corridor2 = new Room("i anden del af gangen");
        corridor3 = new Room("i tredje del af gangen");
        corridor4 = new Room("i fjerde del af gangen");

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

        // sætter startrummet til outside
        currentRoom = outside;
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
