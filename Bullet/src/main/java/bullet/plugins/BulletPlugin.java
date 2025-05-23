package bullet.plugins;

import common.bullet.Bullet;
import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {
    private Entity bullet;
    @Override
    public void start(GameData gameData, World world) {
    }

    @Override
    public void stop(GameData gameData, World world) {
        for(Entity entity : world.getEntities()) {
            if (entity.getClass() == Bullet.class) {
                world.removeEntity(entity);
            }
        }

    }
}
