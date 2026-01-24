package xyz.starrymic.config;

import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.server.core.util.Config;
import com.natamus.hybrid.functions.ConfigFunctions;

public class ConfigHandler {

    // Config
    public static Config<ConfigHandler> config;

    // Fields
    public int MessageInterval = 1;

    // Internal
    private final BuilderCodec<ConfigHandler> codec;

    public ConfigHandler(){
        this.codec = ConfigFunctions.buildCodec(ConfigHandler.class);
    }

    public BuilderCodec<ConfigHandler> getCodec(){
        return codec;
    }

    public static ConfigHandler getConfigHandler(){
        return config.get();
    }
}