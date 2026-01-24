package xyz.starrymic.storage;

import xyz.starrymic.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FunMessagesStorage {

    public static List<String> Messages = new ArrayList<>(); // Is this right?

    public static void RefreshMessages(){
        Constants.LOGGER.atInfo().log("FunMessage refresh requested.");

        try {
            // Read in the messages.
            List<String> messagesUnfiltered = Files.readAllLines(Constants.FunMessagesFilePath);
            // Filter out comments and blank lines.
            for (String s : messagesUnfiltered) {
                if(s.startsWith("#") || s.isBlank()) continue;
                Messages.add(s);
            }

            Constants.LOGGER.atInfo().log("FunMessage refresh successful.");
        } catch (IOException e) {
            Constants.LOGGER.atWarning().log("FunMessage refresh failed. Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
