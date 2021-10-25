package seedu.typists.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


import static seedu.typists.common.Utils.getWordLineFromStringArray;
import static seedu.typists.common.Utils.getWordLines;

public class TimeModeGame extends Game {
    private static final Logger logger = Logger.getLogger("Foo");

    protected final ArrayList<String[]> wordLines;
    public ArrayList<String[]> userLines;

    protected int gameTime;
    protected double realGameTime;

    public TimeModeGame(String targetWordSet, int wordsPerLine) {
        super();
        assert targetWordSet != null : "text passed into Time Game should not be null.";
        this.wordLines = getWordLines(targetWordSet, wordsPerLine);
        this.userLines = new ArrayList<>();
        this.gameTime = getGameTime();
    }

    public boolean readyToStartTimer() {
        Scanner in = new Scanner(System.in);
        String command = "";
        while (!command.equals("yes")) {
            ui.printScreen("Do you want to start now?");
            command = in.nextLine();
        }
        return true;
    }

    public int getGameTime() {
        Scanner in = new Scanner(System.in);
        ui.printScreen("Enter how long (in seconds) you want the game to run: ");
        try {
            return Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Not a Number!");
            return getGameTime();
        }
    }

    public void runGame() {
        Scanner in = new Scanner(System.in);
        List<String> inputs = new ArrayList<>();

        if (readyToStartTimer()) {
            int i = 0;
            long beginTime = System.currentTimeMillis();
            boolean timeOver = false;

            while (!timeOver) {
                long currTime = System.currentTimeMillis() - beginTime;
                if (currTime >= gameTime * 1000L) {
                    timeOver = true;
                    realGameTime = (double) currTime / 1000;
                } else {
                    try {
                        ui.printLine(wordLines.get(i));
                    } catch (IndexOutOfBoundsException e) {
                        logger.log(Level.WARNING, "no more content.");
                    }
                    inputs.add(in.nextLine());
                    i++;
                }
            }
            updateUserLines(inputs);
            ui.printScreen("Game Finished.");
        }
    }

    public void updateUserLines(List<String> stringArray) {
        userLines = getWordLineFromStringArray(stringArray);
    }
}
