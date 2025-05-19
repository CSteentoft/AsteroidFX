// src/main/java/asteroid/systems/AsteroidSplitterImplementation.java
package asteroid.components;

import common.asteroid.Asteroid;
import common.asteroid.IAsteroidSplitter;
import common.data.Entity;
import common.data.World;

import java.util.Random;

public class AsteroidSplitterImplementation implements IAsteroidSplitter {

    private float minRadius = 15f;
    private int edges = 5;
    private final Random random = new Random();

    @Override
    public void SplitAsteroid(Entity original, World world) {

        double x = original.getX();
        double y = original.getY();
        float  r = original.getRadius();
        double originalRotation = original.getRotation();

        if (r <= minRadius) {
            return;
        }

        float childRadius = r / 2f;

        for (int i = 0; i < 2; i++) {
            Asteroid splitAsteroid = new Asteroid();
            splitAsteroid.setType("Asteroid");

            double offsetX = (i == 0 ? +childRadius : -childRadius);
            splitAsteroid.setX(x + offsetX);
            splitAsteroid.setY(y);
            splitAsteroid.setRadius(childRadius);

            double[] coords = new double[edges * 2];
            for (int v = 0; v < edges; v++) {
                double angle = 2 * Math.PI * v / edges;
                double vr = childRadius * (0.5 + random.nextDouble() * 0.5);
                coords[2 * v]     = vr * Math.cos(angle);
                coords[2 * v + 1] = vr * Math.sin(angle);
            }
            splitAsteroid.setPolygonCoordinates(coords);

            float deltaRot = (random.nextFloat() * 60f) - 30f;
            splitAsteroid.setRotation(originalRotation + deltaRot);

            world.addEntity(splitAsteroid);
        }
    }
}

