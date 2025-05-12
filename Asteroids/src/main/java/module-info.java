import common.services.IEntityProcessingService;
import common.services.IGamePluginService;
import common.asteroid.IAsteroidSplitter;


module Asteroids {
    requires Common;
    provides IEntityProcessingService with asteroid.systems.AsteroidProcessor, asteroid.systems.AsteroidSpawnSystem;
    provides IAsteroidSplitter with asteroid.systems.AsteroidSplitterImplementation;
}