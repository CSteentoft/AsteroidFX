module Common {
    exports common.util;
    exports common.data;
    exports common.services;
    exports common.bullet;
    uses common.services.IPostEntityProcessingService;
    uses common.services.IEntityProcessingService;
    uses common.services.IGamePluginService;
}