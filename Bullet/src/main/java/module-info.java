import common.services.IGamePluginService;
import common.services.IEntityProcessingService;
import common.bullet.BulletSPI;

module Bullet {
    requires Common;
    exports bullet.plugins;
    exports bullet.systems;
    provides IGamePluginService with bullet.plugins.BulletPlugin;
    provides IEntityProcessingService with bullet.systems.BulletControlSystem;
    provides BulletSPI with bullet.systems.BulletControlSystem;
}