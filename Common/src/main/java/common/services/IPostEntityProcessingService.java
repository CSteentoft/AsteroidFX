package common.services;

import common.data.GameData;
import common.data.World;

/**
 * This interface is used to process entities after they have been updated, as the name implies it's being used after IEntityProcessingService.
 * It is used to perform any additional processing that needs to be done on the entities after they have been updated.
 */
public interface IPostEntityProcessingService {

    /**
     * Perform any necessary actions after entities already have been processed by IEntityProcessingService.
     * A Great example of this is the collision detection, entities have to be updated first with their positions etc.
     * to be within range of each other to collide, and then the collision detection can be performed afterward using IPostEntityProcessingService.
     *
     * Preconditions:
     * @param gameData Game data contains information about the current input and display height and width.
     * @param world contains entities already processed by IEntityProcessingService.
     *
     * Post conditions:
     * @param world contains entities that have been modified by the post-processing logic.
     * @param gameData Game data contains information about the current input and display height and width.
     *
     */
    void process(GameData gameData, World world);
}
