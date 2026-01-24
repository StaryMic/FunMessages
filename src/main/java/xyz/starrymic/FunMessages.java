package xyz.starrymic;

import com.hypixel.hytale.server.core.event.events.BootEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.natamus.hybrid.functions.ConfigFunctions;
import xyz.starrymic.commands.RefreshMessageCommand;
import xyz.starrymic.config.ConfigHandler;
import xyz.starrymic.events.SetupEvents;
import xyz.starrymic.storage.FunMessagesStorage;

import javax.annotation.Nonnull;
import java.io.FileWriter;
import java.io.IOException;

public class FunMessages extends JavaPlugin {

    private static FunMessages instance;

    public FunMessages(@Nonnull JavaPluginInit init) {
        super(init);

        // Make into an instance.
        instance = this;

        // Set up the config
        ConfigHandler.config = this.withConfig(ConfigFunctions.getConfigName("FunMessages"), new ConfigHandler().getCodec());
    }

    @Override
    protected void setup() {
        // Setting up the config with Hybrid (Serilum)
        ConfigFunctions.processConfigSetup(this, ConfigHandler.config);

        // Register the command for refreshing the FunMessages.txt file
        this.getCommandRegistry().registerCommand(new RefreshMessageCommand());

        // Get the file for the txt file.
        var messageFile = Constants.FunMessagesFilePath.toFile();

        // Check if the file exists and if it doesn't then make it.
        if (!messageFile.exists()) {
            try {
                messageFile.createNewFile();

                try (var writer = new FileWriter(messageFile)) {
                    writer.write("""
                            Add new messages by changing the <gradient:gold:red>FunMessages.txt</gradient> in <b>saves/[yoursave]/mods/xyz.starrymic_TimedServerMessages/FunMessages.txt</b>.
                            You can change the message interval in the <b>saves/[worldname]/mods/xyz.starrymic.TimedServerMessages/timedservermessages.config.json</b> file!
                            Added new messages? Reload them with <b>/refreshfunmessages</b>.
                            Messages can be styled with TinyMsg's formatting tags. You can find those at the <b><link:https://github.com/Zoltus/TinyMessage>TinyMsg Github Repo</link></b>!""");
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Ensure messages are loaded.
        FunMessagesStorage.RefreshMessages();

        // Registers the BootEvent to start the MessageTimer on server boot.
        this.getEventRegistry().registerGlobal(BootEvent.class, SetupEvents::onServerBoot);
    }

    public static FunMessages getInstance() {
        return instance;
    }
}