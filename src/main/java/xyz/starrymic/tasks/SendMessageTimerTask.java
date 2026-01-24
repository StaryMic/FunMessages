package xyz.starrymic.tasks;

import com.hypixel.hytale.server.core.universe.Universe;
import fi.sulku.hytale.TinyMsg;
import xyz.starrymic.Constants;
import xyz.starrymic.storage.FunMessagesStorage;

import java.util.TimerTask;

public class SendMessageTimerTask extends TimerTask {

    // Store the Universe we send messages to.
    private final Universe universe = Universe.get();

    // Basic duplicate message protection.
    int prevIndex = -1; // Should prevent hitting the prev index check on the first run.

    @Override
    public void run() {
        // Grab a random index
        var randIndex = Constants.random.nextInt(FunMessagesStorage.Messages.size());

        // Do NOT reroll if there is only 1 message. We don't want to limit the min messages.
        // This also prevents an infinite loop cooking the CPU by not having a delay between rerolls. :3
        if (FunMessagesStorage.Messages.size() != 1) {
            // Ensure it isn't the same index as the previous message.
            while (randIndex == prevIndex) {
                randIndex = Constants.random.nextInt(FunMessagesStorage.Messages.size());
            }
            // Update prevIndex to be the current one so it doesn't run again.
            prevIndex = randIndex;
        }

        // Grab and send the message
        var randMessage = FunMessagesStorage.Messages.get(randIndex);
        universe.sendMessage(TinyMsg.parse(randMessage));
    }
}
