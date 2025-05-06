import common.services.IEntityProcessingService;
import common.services.IGamePluginService;
import common.asteroid.IAsteroidSplitter;


module Asteroids {
    requires Common;
    provides IGamePluginService with asteroid.plugins.AsteroidPlugin;
    provides IEntityProcessingService with asteroid.systems.AsteroidProcessor;
    provides IAsteroidSplitter with asteroid.systems.AsteroidSplitterImplementation;
}