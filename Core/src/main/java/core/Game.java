package core;

import common.data.Entity;
import common.data.GameData;
import common.data.GameKeys;
import common.data.World;
import common.scoringSystem.ScoringLocator;
import common.services.IEntityProcessingService;
import common.services.IGamePluginService;
import common.services.IPostEntityProcessingService;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Game {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();
    private final List<IGamePluginService> gamePluginServices;
    private final List<IEntityProcessingService> entityProcessingServiceList;
    private final List<IPostEntityProcessingService> postEntityProcessingServices;
    private Text scoreText;

    Game(List<IGamePluginService> gamePluginServices, List<IEntityProcessingService> entityProcessingServiceList, List<IPostEntityProcessingService> postEntityProcessingServices) {
        this.gamePluginServices = gamePluginServices;
        this.entityProcessingServiceList = entityProcessingServiceList;
        this.postEntityProcessingServices = postEntityProcessingServices;
    }

    public void start(Stage window) throws Exception {
        scoreText = new Text(10, 40, "Score: 0");
        scoreText.setFill(Color.WHITE);
        scoreText.setFont(Font.font("Monospace", FontWeight.BOLD, 18));
        gameWindow.getChildren().add(scoreText);
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());


        Image bgGif = new Image(getClass().getResource("/assets/images/space.gif").toExternalForm());

        ImageView bgView = new ImageView(bgGif);

        bgView.setFitWidth(gameData.getDisplayWidth());
        bgView.setFitHeight(gameData.getDisplayHeight());
        bgView.setPreserveRatio(false);


        gameWindow.getChildren().add(0, bgView);

        Scene scene = new Scene(gameWindow);
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.A)) {
                gameData.getGameKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.D)) {
                gameData.getGameKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.W)) {
                gameData.getGameKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getGameKeys().setKey(GameKeys.SPACE, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.A)) {
                gameData.getGameKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.D)) {
                gameData.getGameKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.W)) {
                gameData.getGameKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getGameKeys().setKey(GameKeys.SPACE, false);
            }

        });

        for (IGamePluginService iGamePlugin : getGamePluginServices()) {
            iGamePlugin.start(gameData, world);
        }
        for (Entity entity : world.getEntities()) {
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }
        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();
    }

    public void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                draw();
                gameData.getGameKeys().update();
            }

        }.start();
    }

    private void update() {
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    private void draw() {
        long pts = ScoringLocator.getService().getTotalPoints();
        scoreText.setText("Score: " + pts);

        for (Entity polygonEntity : new ArrayList<>(polygons.keySet())) {
            if (!world.getEntities().contains(polygonEntity)) {
                Polygon removed = polygons.remove(polygonEntity);
                gameWindow.getChildren().remove(removed);
            }
        }

        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }

            switch (entity.getType()) {
                case "Enemy":
                    polygon.setFill(Color.RED);
                    polygon.setStroke(Color.DARKRED);
                    break;
                case "Player":
                    polygon.setFill(Color.GREEN);
                    polygon.setStroke(Color.DARKGREEN);
                    break;
                case "Asteroid":
                    polygon.setFill(Color.DARKGRAY);
                    polygon.setStroke(Color.WHITE);
                    break;
                case "Bullet":
                    polygon.setFill(Color.RED);
                    polygon.setStroke(Color.RED);
                    break;
                default:
                    polygon.setFill(Color.BLACK);
                    polygon.setStroke(Color.WHITE);
                    break;
            }

            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());

        }
    }


    public List<IGamePluginService> getGamePluginServices() {
        return gamePluginServices;
    }

    public List<IEntityProcessingService> getEntityProcessingServices() {
        return entityProcessingServiceList;
    }

    public List<IPostEntityProcessingService> getPostEntityProcessingServices() {
        return postEntityProcessingServices;
    }

}