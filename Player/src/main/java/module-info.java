import common.services.IGamePluginService;
import common.services.IEntityProcessingService;
import player.plugins.PlayerPlugin;
import player.systems.PlayerControlSystem;

module Player {
    requires Common;
    provides IGamePluginService with PlayerPlugin;
    provides IEntityProcessingService with PlayerControlSystem;
    uses common.bullet.BulletSPI;

    //Testing
    exports player.plugins;
    exports player.systems;
    exports player.components;
}