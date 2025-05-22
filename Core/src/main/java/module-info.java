module Core {
    exports core;
    requires Common;
    requires javafx.graphics;
    requires spring.context;
    requires spring.core;
    requires spring.beans;
    requires spring.web;
    requires spring.aop;
    requires spring.expression;
    requires commons.logging;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires micrometer.commons;
    requires micrometer.core;
    requires micrometer.observation;

    opens core to spring.core, spring.beans, spring.context, spring.web, spring.aop, spring.expression;
    uses common.services.IEntityProcessingService;
    uses common.services.IPostEntityProcessingService;
    uses common.services.IGamePluginService;
}