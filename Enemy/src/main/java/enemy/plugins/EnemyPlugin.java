package enemy.plugins;

import common.data.Entity;
import common.data.GameData;
import common.data.Health;
import common.data.World;
import common.services.IGamePluginService;
import enemy.components.EnemyComponent;

public class EnemyPlugin implements IGamePluginService {
    Entity enemy;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemyShip(gameData);
        enemy.setType("Enemy");
        enemy.addComponent(new Health(3));
        world.addEntity(enemy);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);

    }

    private Entity createEnemyShip(GameData gameData) {
        Entity enemyShip = new EnemyComponent();
        enemyShip.setPolygonCoordinates(12,0,4,4,-2,8,-12,6,-6,0,-12,-6,-2,-8, 4,-4);
        enemyShip.setRadius(14);
        enemyShip.setX(gameData.getDisplayWidth()/3);
        enemyShip.setY(gameData.getDisplayHeight()/2);
        return enemyShip;
    }

}