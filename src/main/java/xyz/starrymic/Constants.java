package xyz.starrymic;

import com.hypixel.hytale.logger.HytaleLogger;
import com.natamus.hybrid.functions.DataFunctions;

import java.nio.file.Path;
import java.util.Random;

public class Constants {
    // Code from Serilum's mod "Hybrid" (functions/DataFunctions.java)
//    public static final Path FunMessagesFilePath = PluginManager.MODS_PATH.resolve(TimedServerMessages.getInstance().getName().replace(":", "_")).resolve("FunMessages.txt");
    public static final Path FunMessagesFilePath = DataFunctions.getModDataDirectory(FunMessages.getInstance()).resolve("FunMessages.txt");

    public static final Random random = new Random();

    public static final HytaleLogger LOGGER = HytaleLogger.get("TimedServerMessages");
}
