package asteroid.systems;

import common.asteroid.Asteroid;
import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.services.IEntityProcessingService;


public class AsteroidProcessor implements IEntityProcessingService {

    private static final double SPEED = 0.5;

    @Override
    public void process(GameData gameData, World world) {
        double w = gameData.getDisplayWidth();
        double h = gameData.getDisplayHeight();

        for (Entity ent : world.getEntities(Asteroid.class)) {
            double rot = ent.getRotation();
            double rad = Math.toRadians(rot);

            double dx = Math.cos(rad) * SPEED;
            double dy = Math.sin(rad) * SPEED;

            double x = ent.getX() + dx;
            double y = ent.getY() + dy;

            if (x < 0) {
                x = 0;
                rot = 180 - rot;
            } else if (x > w) {
                x = w;
                rot = 180 - rot;
            }

            if (y < 0) {
                y = 0;
                rot = -rot;
            } else if (y > h) {
                y = h;
                rot = -rot;
            }

            rot = rot % 360;
            if (rot < 0) rot += 360;

            ent.setX(x);
            ent.setY(y);
            ent.setRotation(rot);
        }
    }

}
