package worldofzuul;

public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), BUY("buy"), WALLET("wallet"), CHECK("check"),REPLACE("replace"),DELETE("delete"),INVENTORY("inventory"),SCORE("score");
    
    private String commandString;
    
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    public String toString()
    {
        return commandString;
    }
}
