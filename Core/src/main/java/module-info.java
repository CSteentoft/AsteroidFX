module Core {
    exports core;
    requires Common;
    requires javafx.graphics;
    requires spring.context;
    requires spring.core;
    requires spring.beans;
    opens core to spring.core, spring.beans, spring.context, javafx.graphics;
    uses common.services.IEntityProcessingService;
    uses common.services.IPostEntityProcessingService;
    uses common.services.IGamePluginService;
}