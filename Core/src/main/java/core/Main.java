package core;

import common.services.IEntityProcessingService;
import common.services.IGamePluginService;
import common.services.IPostEntityProcessingService;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class Main extends Application {
    private Game game;
    @Override
    public void init(){
        List<IGamePluginService> gamePluginServices = loadAll(IGamePluginService.class);
        List<IEntityProcessingService> entityProcessingServices = loadAll(IEntityProcessingService.class);
        List<IPostEntityProcessingService> postEntityProcessingServices = loadAll(IPostEntityProcessingService.class);

        game = new Game(gamePluginServices, entityProcessingServices, postEntityProcessingServices);
    }

    public static void main(String[] args) {
        launch(Main.class);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        game.start(primaryStage);
        game.render();

    }

    private static <T> List<T> loadAll(Class<T> clazz) {
        return StreamSupport.stream(ServiceLoader.load(clazz).spliterator(),false).collect(Collectors.toList());
    }
}
