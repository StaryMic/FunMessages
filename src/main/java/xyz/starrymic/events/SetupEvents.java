package xyz.starrymic.events;

import com.hypixel.hytale.server.core.event.events.BootEvent;
import com.hypixel.hytale.server.core.universe.Universe;
import fi.sulku.hytale.TinyMsg;
import xyz.starrymic.Constants;
import xyz.starrymic.classes.MessageTimer;

public class SetupEvents {

    private static MessageTimer messageTimer;

    public static void onServerBoot(BootEvent ignoredEvent)
    {
        Constants.LOGGER.atInfo().log("Server done booting. Starting MessageTimer now.");

        messageTimer = new MessageTimer(); // Create the new MessageTimer and pass the world to it.

        // Send a message that says we're working now.
        Universe.get().sendMessage(TinyMsg.parse("<gradient:gold:red>FunMessages</gradient> is now loaded! Have fun!"));
    }
}
