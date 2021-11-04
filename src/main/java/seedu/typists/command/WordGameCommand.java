package seedu.typists.command;

import seedu.typists.exception.IncompleteCommandException;
import seedu.typists.exception.InvalidCommandException;
import seedu.typists.game.Game;
import seedu.typists.game.WordLimitGame;

import java.util.ArrayList;

import static seedu.typists.Main.content;

public class WordGameCommand extends GameCommand {
    private static final String WORD_SIGNIFIER = "-word";

    @Override
    public Game createGame(ArrayList<String> args, boolean isReady, boolean setContent) {
        //ui.printKeyBoard
        try {
            int wordLimit = getNumber(args, WORD_SIGNIFIER);
            if (setContent) {
                content.setContent();
            }
            return new WordLimitGame(content.getContent(), 5, wordLimit, isReady);
        } catch (InvalidCommandException e) {
            //won't come here.
        } catch (NumberFormatException | IncompleteCommandException e) {
            //constructor without limit
            return new WordLimitGame(content.getContent(), 5, isReady);
        }
        return null;
    }

}