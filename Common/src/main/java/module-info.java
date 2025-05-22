module Common {
    exports common.data;
    exports common.services;
    exports common.bullet;
    exports common.asteroid;
    exports common.scoringSystem;
    uses common.services.IPostEntityProcessingService;
    uses common.services.IEntityProcessingService;
    uses common.services.IGamePluginService;
    uses common.asteroid.IAsteroidSplitter;
}