package player.plugins;

import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.services.IGamePluginService;
import player.components.PlayerComponent;

public class PlayerPlugin implements IGamePluginService {
    Entity player;

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        player = createPlayerShip(gameData);
        world.addEntity(player);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);

    }

    private Entity createPlayerShip(GameData gameData) {
        Entity playerShip = new PlayerComponent();
        playerShip.setPolygonCoordinates(12,0,4,4,-2,8,-12,6,-6,0,-12,-6,-2,-8, 4,-4);
        playerShip.setRadius(14);
        playerShip.setX(gameData.getDisplayWidth()/2);
        playerShip.setY(gameData.getDisplayHeight()/2);
        return playerShip;
    }

}
