import common.data.GameData;
import common.data.GameKeys;
import common.data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.components.PlayerComponent;
import player.systems.PlayerControlSystem;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerControlSystemTest {

    private PlayerControlSystem system;
    private GameData gameData;
    private GameKeys gameKeys;
    private World world;
    private PlayerComponent player;

    @BeforeEach
    void setUp() {
        system   = new PlayerControlSystem();
        gameData = mock(GameData.class);
        gameKeys = mock(GameKeys.class);
        world    = mock(World.class);

        when(gameData.getGameKeys()).thenReturn(gameKeys);
        when(gameData.getDisplayWidth()).thenReturn(800);
        when(gameData.getDisplayHeight()).thenReturn(600);

        player = new PlayerComponent();
        player.setX(100);
        player.setY(150);
        player.setRotation(0);

        when(world.getEntities(PlayerComponent.class)).thenReturn(Collections.singletonList(player));
    }

    @Test
    void leftKey_shouldRotateByNegative5() {
        when(gameKeys.isDown(GameKeys.LEFT)).thenReturn(true);

        system.process(gameData, world);

        assertEquals(-5.0, player.getRotation(), 0.0001);
    }

    @Test
    void rightKey_shouldRotateBy5() {
        when(gameKeys.isDown(GameKeys.RIGHT)).thenReturn(true);

        system.process(gameData, world);

        assertEquals(5.0, player.getRotation(), 0.0001);
    }

    @Test
    void upKey_shouldMoveInFacingDirection() {
        player.setRotation(0);
        when(gameKeys.isDown(GameKeys.UP)).thenReturn(true);

        system.process(gameData, world);

        assertEquals(101.0, player.getX(), 0.0001);
        assertEquals(150.0, player.getY(), 0.0001);
    }

    @Test
    void boundaryConditions_shouldGetMovedWithinBounds() {
        player.setX(-10);
        player.setY(610);

        system.process(gameData, world);

        assertEquals(1.0,   player.getX(), 0.0001);
        assertEquals(599.0, player.getY(), 0.0001);
    }
}
