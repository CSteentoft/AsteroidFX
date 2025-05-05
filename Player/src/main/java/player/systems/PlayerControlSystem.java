package player.systems;

import common.bullet.BulletSPI;
import common.data.Entity;
import common.data.GameData;
import common.data.GameKeys;
import common.data.World;
import common.services.IEntityProcessingService;
import player.components.PlayerComponent;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(PlayerComponent.class)) {
            if (gameData.getGameKeys().isDown(GameKeys.LEFT)) {
                player.setRotation(player.getRotation() - 5);
            }
            if (gameData.getGameKeys().isDown(GameKeys.RIGHT)) {
                player.setRotation(player.getRotation() + 5);
            }
            if (gameData.getGameKeys().isDown(GameKeys.UP)) {
                double changeX = Math.cos(Math.toRadians(player.getRotation()));
                double changeY = Math.sin(Math.toRadians(player.getRotation()));
                player.setX(player.getX() + changeX);
                player.setY(player.getY() + changeY);
            }
            if (gameData.getGameKeys().isDown(GameKeys.SPACE)) {
                getBulletSPIs().stream().findFirst().ifPresent(spi -> {world.addEntity(spi.createBullet(player, gameData));});
            }
            if (player.getX() < 0) {
                player.setX(1);
            }
            if (player.getX() > gameData.getDisplayWidth()) {
                player.setX(gameData.getDisplayWidth() - 1);
            }
            if (player.getY() < 0) {
                player.setY(1);
            }
            if (player.getY() > gameData.getDisplayHeight()) {
                player.setY(gameData.getDisplayHeight() - 1);
            }
           
        }
    }


    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
