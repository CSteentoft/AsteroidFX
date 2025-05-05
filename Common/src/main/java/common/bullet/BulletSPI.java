package common.bullet;

import common.data.Entity;
import common.data.GameData;

public interface BulletSPI {
    Entity createBullet(Entity entity, GameData gameData);
}
