package Energy;
import java.util.HashMap;


public class CommandWords
{
    private HashMap<String, CommandWord> validCommands;

    public CommandWords() // opretter commandwords i valid commands
    {
        validCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    public CommandWord getCommandWord(String commandWord) // getter
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    public boolean isCommand(String aString) // tjekker om en streng er en valid command
    {
        return validCommands.containsKey(aString);
    }

    public void showAll() // printer tilgængelige kommandoer
    {
        for(String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
