package Energy.presentationCLI;

class Command {
    // ATTRIBUTTER
    private CommandWord commandWord;
    private String secondWord;

    // CONSTRUCTOR
    public Command(CommandWord commandWord, String secondWord) {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    // GETTERS
    public CommandWord getCommandWord() {
        return commandWord;
    }

    public String getSecondWord() {
        return secondWord;
    }

    // METODER
    public boolean isUnknown() {
        return (commandWord == CommandWord.UNKNOWN);
    }

    public boolean hasSecondWord() {
        return (secondWord != null);
    }
}

