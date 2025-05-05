import common.services.IGamePluginService;
import common.services.IEntityProcessingService;

module Player {
    requires Common;
    provides IGamePluginService with player.plugins.PlayerPlugin;
    provides IEntityProcessingService with player.systems.PlayerControlSystem;
    uses common.bullet.BulletSPI;
}