package enemy.systems;

import common.bullet.BulletSPI;
import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.services.IEntityProcessingService;
import enemy.components.EnemyComponent;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemySystem implements IEntityProcessingService {
    private final Random random = new Random();

    private float directionCounter = 0;
    private float shootCounter = 0;

    private int framesPerDirectionShift = 30 + random.nextInt(60);
    private int framesPerShot = 90 + random.nextInt(20);

    private double speedPerFrame = 2.0;


    @Override
    public void process(GameData gameData, World world) {
        int screenWidth = gameData.getDisplayWidth();
        int screenHeight = gameData.getDisplayHeight();

        for (Entity enemy : world.getEntities(EnemyComponent.class) ) {
            directionCounter++;
            shootCounter++;

            if (directionCounter >= framesPerDirectionShift) {
                enemy.setRotation(random.nextFloat() * 360f);
                directionCounter = 0;
                framesPerDirectionShift = 30 + random.nextInt(60);
            }

            double dx = Math.cos(Math.toRadians(enemy.getRotation()) * speedPerFrame);
            double dy = Math.sin(Math.toRadians(enemy.getRotation()) * speedPerFrame);
            enemy.setX(enemy.getX() + dx);
            enemy.setY(enemy.getY() + dy);

            boolean hitScreenEdge = false;
            if (enemy.getX() < 0) {
                enemy.setX(1);
                hitScreenEdge = true;
            }
            if (enemy.getX() > screenWidth) {
                enemy.setX(screenWidth - 1);
                hitScreenEdge = true;
            }
            if (enemy.getY() < 0) {
                enemy.setY(1);
                hitScreenEdge = true;
            }
            if (enemy.getY() > screenHeight) {
                enemy.setY(screenHeight - 1);
                hitScreenEdge = true;
            }
            if (hitScreenEdge) {
                enemy.setRotation(random.nextFloat() * 360f);
            }

            if (shootCounter >= framesPerShot) {
                getBulletSPIs().stream().findFirst().ifPresent(spi -> {world.addEntity(spi.createBullet(enemy, gameData));});
                shootCounter = 0;
                framesPerShot = 90 + random.nextInt(20);
            }
        }


    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
