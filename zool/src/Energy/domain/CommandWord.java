package Energy.domain;

enum CommandWord {
    //de forskllige kommandoer
    GO("gå"), QUIT("afslut"), HELP("hjælp"), UNKNOWN("?"), BUY("køb"), STATUS("status"),
    REPLACE("udskift"), DELETE("skrot"), INVENTORY("inventar"), NEWROUND("nytår"),
    ;

    private String commandString;

    // SETTER
    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    @Override
    public String toString() {
        return commandString;
    }
}
