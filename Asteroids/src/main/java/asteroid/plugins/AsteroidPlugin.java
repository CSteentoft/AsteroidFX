package asteroid.plugins;

import common.asteroid.Asteroid;
import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.services.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        Entity asteroid = createAsteroid();
        world.addEntity(asteroid);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Asteroid.class)) {
            world.removeEntity(entity);
        }

    }
    private Entity createAsteroid() {
        Entity asteroid = new Asteroid();
        asteroid.setType("Asteroid");
        Random rnd = new Random();
        int size = rnd.nextInt(20) + 5;
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(0);
        asteroid.setY(0);
        asteroid.setRadius(size);
        asteroid.setRotation(rnd.nextInt(90));
        return asteroid;
    }
}
