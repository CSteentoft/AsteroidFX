module Common {
    exports common.util;
    exports common.data;
    exports common.services;
    exports common.bullet;
    exports common.asteroid;
    uses common.services.IPostEntityProcessingService;
    uses common.services.IEntityProcessingService;
    uses common.services.IGamePluginService;
    uses common.asteroid.IAsteroidSplitter;
}