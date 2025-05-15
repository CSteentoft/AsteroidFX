import common.services.IEntityProcessingService;
import common.services.IGamePluginService;

module Enemy {
    requires Common;
    provides IGamePluginService with enemy.plugins.EnemyPlugin;
    provides IEntityProcessingService with enemy.systems.EnemySystem;
    uses common.bullet.BulletSPI;
}