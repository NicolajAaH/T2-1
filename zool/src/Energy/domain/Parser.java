package Energy.domain;

import java.util.Scanner;

class Parser
{
    private CommandWords commands;
    private Scanner reader;

    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    public Command getCommand() 
    {
        String inputLine; // variablen hvor input fra bruger gemmes
        String word1 = null; // kommando ord 1
        String word2 = null; // kommando ord 2

        System.out.print("> "); 

        inputLine = reader.nextLine(); // tager input fra bruger

        Scanner tokenizer = new Scanner(inputLine); // deler input i to ord!
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next(); 
            }
        }

        return new Command(commands.getCommandWord(word1), word2);
    }

    public void showCommands()
    {
        commands.showAll();
    }
}
