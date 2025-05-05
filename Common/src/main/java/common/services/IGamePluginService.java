package common.services;

import common.data.GameData;
import common.data.World;

/**
 * The interface IGamePluginService is used for implementations that do not need to be processed each game loop.
 * So in a way it's for implementing stuff that is static, and does not need to be updated. For example, the PlayerPlugin,
 * which is used to create the player entity and playerShip, and add it to the world.
 */

public interface IGamePluginService {

    /**
     * The start method is called to initialize implementations of entities into the world.
     * Preconditions:
     * @param gameData Game data contains information about the current input and display height and width.
     * @param world    The world object, contains information about all current entities in the game, and allows for new entities to be added, removed or updated.
     *
     * Post conditions:
     * @param gameData Unchanged, game data still contains information about the current input and display height and width.
     * @param world    Post use, the world object can contain new entities that have been added in the start method.
     */
    void start(GameData gameData, World world);

    /**
     * The stop method is used to remove entities from the world.
     * Preconditions:
     * @param gameData Game data contains information about the current input and display height and width.
     * @param world  World contains implementations of entities that have been added in the start method.
     *
     * Post conditions:
     * @param gameData Unchanged, game data still contains information about the current input and display height and width.
     * @param world  Selected entities from world, have now been deleted.
     */
    void stop(GameData gameData, World world);
}
