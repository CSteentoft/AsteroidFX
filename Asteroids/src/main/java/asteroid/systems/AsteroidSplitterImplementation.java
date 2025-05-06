package asteroid.systems;

import common.asteroid.Asteroid;
import common.asteroid.IAsteroidSplitter;
import common.data.Entity;
import common.data.World;
import java.util.Random;

public class AsteroidSplitterImplementation implements IAsteroidSplitter {

    private static final float MIN_RADIUS = 10f;
    @Override
    public void createSplitAsteroid(Entity original, World world) {
        Double x = original.getX();
        Double y = original.getY();
        float radius = original.getRadius();
        Double originalRotation = original.getRotation();

        if (radius <= MIN_RADIUS) {
            return;
        }

        double childRadius = radius / 2.0;
        int edges = 5;
        Random random = new Random();

        for (int i = 0; i < 2; i++) {
            Asteroid splitAsteroid = new Asteroid();
            splitAsteroid.setType("Asteroid");
            splitAsteroid.setX(x + (i == 0 ? childRadius : -childRadius));
            splitAsteroid.setY(y);
            splitAsteroid.setRadius((float) childRadius);

            double[] coords = new double[edges * 2];
            for (int s = 0; s < edges; s++) {
                double angle = 2 * Math.PI * s / edges;
                coords[2*s]     = childRadius * Math.cos(angle);
                coords[2*s + 1] = childRadius * Math.sin(angle);
            }
            splitAsteroid.setPolygonCoordinates(coords);

            float offset   = (random.nextFloat() * 60f) - 30f;
            splitAsteroid.setRotation(originalRotation + offset);

            world.addEntity(splitAsteroid);
        }


}
}
