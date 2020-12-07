package Energy.domain;

class Command
{
    private CommandWord commandWord;
    private String secondWord;
    //setter
    public Command(CommandWord commandWord, String secondWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }
    //getters
    public CommandWord getCommandWord()
    {
        return commandWord;
    }

    public String getSecondWord()
    {
        return secondWord;
    }

    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

