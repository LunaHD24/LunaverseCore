package dev.lunaa.lunaversecore;

import dev.lunaa.lunaversecore.test.EntityCommand;
import dev.lunaa.lunaversecore.test.ItemCommand;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;

@SuppressWarnings("UnstableApiUsage")
public class Bootstrap implements PluginBootstrap {
    @Override
    public void bootstrap(BootstrapContext context) {
        context.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register(ItemCommand.rootNode.build());
            commands.registrar().register(EntityCommand.rootNode.build());
        });
    }
}
