package asteroid.systems;

import common.asteroid.Asteroid;
import common.data.GameData;
import common.data.World;
import common.data.Entity;
import common.services.IEntityProcessingService;

import java.util.Random;

public class AsteroidSpawnSystem implements IEntityProcessingService {

    private int framesPerSpawm = 60; // 1 new asteroid per second
    private int edgeSpawnZone = 30;
    private int frameCounter = 0;
    private final Random rnd = new Random();

    @Override
    public void process(GameData gameData, World world) {
        if (++frameCounter < framesPerSpawm) {
            return;
        }
        frameCounter = 0;
        spawnAsteroid(gameData, world);
    }

    private void spawnAsteroid(GameData gameData, World world) {
        Entity a = new Asteroid();
        a.setType("Asteroid");

        int baseSize = rnd.nextInt(40) + 20;
        int edges    = 5;
        double[] coords = new double[edges * 2];
        for (int i = 0; i < edges; i++) {
            double angle = 2 * Math.PI * i / edges;
            double radius = baseSize * (0.5 + rnd.nextDouble() * 0.5);
            coords[2*i]     = radius * Math.cos(angle);
            coords[2*i + 1] = radius * Math.sin(angle);
        }
        a.setPolygonCoordinates(coords);
        a.setRadius(baseSize);

        double w = gameData.getDisplayWidth();
        double h = gameData.getDisplayHeight();

        int edge = rnd.nextInt(4);
        double x, y;
        switch (edge) {
            case 0:
                x = rnd.nextDouble() * w;
                y = rnd.nextDouble() * edgeSpawnZone;
                break;
            case 1:
                x = rnd.nextDouble() * w;
                y = h - rnd.nextDouble() * edgeSpawnZone;
                break;
            case 2:
                x = rnd.nextDouble() * edgeSpawnZone;
                y = rnd.nextDouble() * h;
                break;
            default:
                x = w - rnd.nextDouble() * edgeSpawnZone;
                y = rnd.nextDouble() * h;
        }

        a.setX(x);
        a.setY(y);
        a.setRotation(rnd.nextDouble() * 360.0);

        world.addEntity(a);
    }
}
