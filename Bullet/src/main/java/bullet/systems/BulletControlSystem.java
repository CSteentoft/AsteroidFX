package bullet.systems;

import common.bullet.Bullet;
import common.bullet.BulletSPI;
import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX * 3);
            bullet.setY(bullet.getY() + changeY * 3);
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        String shooterType = shooter.getType();
        bullet.setType(shooterType + "Bullet");
        bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
        double changeX = Math.cos(Math.toRadians(shooter.getRotation()));
        double changeY = Math.sin(Math.toRadians(shooter.getRotation()));
        bullet.setX(shooter.getX() + changeX * 14);
        bullet.setY(shooter.getY() + changeY * 14);
        bullet.setRotation(shooter.getRotation());
        bullet.setRadius(1);
        return bullet;
    }
}
