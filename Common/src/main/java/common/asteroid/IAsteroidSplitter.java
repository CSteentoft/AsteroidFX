package common.asteroid;

import common.data.Entity;
import common.data.World;

public interface IAsteroidSplitter {
    void createSplitAsteroid(Entity entity, World world);
}
