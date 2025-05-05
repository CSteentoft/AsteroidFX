package common.services;

import common.data.GameData;
import common.data.World;

/**
 * The interface IEntityProcessingService defines a contract for processing world entities each game loop,
 * meaning that the logic in the implementation of the process method will be rerun over and over till the game is shutdown.
 */
public interface IEntityProcessingService {

    /**
     * This method is responsible for processing the game entities, and game data each game loop.
     ** Preconditions:
     * @param gameData Game data contains information about the current input and display height and width.
     * @param world    World contains information about all current entities in the game, and allows for new entities to be added, removed or updated.
     * Preconditions:
     * @param gameData Constantly updated based on implemented logic, will be updated till game is shutdown.
     * @param world    Entities constantly updated based on implemented logic, will be updated till game is shutdown.
     */
    void process(GameData gameData, World world);
}
