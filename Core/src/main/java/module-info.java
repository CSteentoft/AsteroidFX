module Core {
    exports core;
    requires Common;
    requires javafx.graphics;
    requires spring.context;
    requires spring.core;
    requires spring.beans;
    opens core to javafx.graphics, spring.core;
    uses common.services.IPostEntityProcessingService;
    uses common.services.IEntityProcessingService;
    uses common.services.IGamePluginService;
}