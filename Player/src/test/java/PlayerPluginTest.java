import common.data.Entity;
import common.data.GameData;
import common.data.World;
import common.data.Health;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import player.plugins.PlayerPlugin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerPluginTest {

    private PlayerPlugin plugin;
    private GameData gameData;
    private World world;

    @BeforeEach
    void setUp() {
        plugin = new PlayerPlugin();
        gameData = mock(GameData.class);
        world    = mock(World.class);

        when(gameData.getDisplayWidth()).thenReturn(800);
        when(gameData.getDisplayHeight()).thenReturn(600);
    }

    @Test
    void start_shouldCreateAndAddPlayerEntity() {
        ArgumentCaptor<Entity> captor = ArgumentCaptor.forClass(Entity.class);

        plugin.start(gameData, world);

        verify(world, times(1)).addEntity(captor.capture());
        Entity player = captor.getValue();
        assertNotNull(player, "Player entity should not be null");

        assertEquals("Player", player.getType(), "Entity type");

        assertEquals(14.0, player.getRadius(), 0.0001, "Radius");

        assertEquals(400.0, player.getX(),  0.0001, "X should be half of display width");
        assertEquals(300.0, player.getY(),  0.0001, "Y should be half of display height");

        Health health = player.getComponent(Health.class);
        assertNotNull(health, "Health component must be attached");
        assertEquals(3, health.getHealth(), "Health should start at 3");
    }

    @Test
    void stop_shouldRemoveAddedPlayerEntity() {
        plugin.start(gameData, world);

        ArgumentCaptor<Entity> captor = ArgumentCaptor.forClass(Entity.class);
        verify(world).addEntity(captor.capture());
        Entity player = captor.getValue();

        plugin.stop(gameData, world);
        verify(world, times(1)).removeEntity(player);
    }
}
