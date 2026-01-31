package xyz.starrymic.commands;

import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.permissions.PermissionsModule;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import fi.sulku.hytale.TinyMsg;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import xyz.starrymic.storage.FunMessagesStorage;

import java.util.concurrent.CompletableFuture;

public class RefreshMessageCommand extends AbstractCommand {

    public RefreshMessageCommand() {
        super("RefreshFunMessages", "Refreshes the FunMessages message list.");
    }

    @NullableDecl
    @Override
    protected CompletableFuture<Void> execute(@NonNullDecl CommandContext commandContext) {
        // Get the Permissions Module.
        PermissionsModule perms = PermissionsModule.get();

        // Check if this command is being called by a Player or by the Server
        if (commandContext.isPlayer()) {

            // Check if the player has the perms.
            var permCheck = perms.hasPermission(commandContext.sender().getUuid(), "xyz.starrymic.refreshfunmessages");
            if (permCheck){
                FunMessagesStorage.RefreshMessages();
                Universe.get().sendMessage(TinyMsg.parse("<gradient:gold:red>FunMessages</gradient> reloaded!"));
                return CompletableFuture.completedFuture(null);
            }
        }
        else{
            FunMessagesStorage.RefreshMessages();
            Universe.get().sendMessage(TinyMsg.parse("<gradient:gold:red>FunMessages</gradient> reloaded!"));
            return CompletableFuture.completedFuture(null);
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    protected boolean canGeneratePermission() {
        return true;
    }

    @NullableDecl
    @Override
    protected String generatePermissionNode() {
        return "xyz.starrymic.refreshfunmessages";
    }
}
