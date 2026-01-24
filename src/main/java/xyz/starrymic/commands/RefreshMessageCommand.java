package xyz.starrymic.commands;

import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;
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
        FunMessagesStorage.RefreshMessages();
        Universe.get().sendMessage(TinyMsg.parse("<gradient:gold:red>FunMessages</gradient> reloaded!"));
        return CompletableFuture.completedFuture(null);
    }
}
