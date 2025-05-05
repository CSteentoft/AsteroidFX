package core;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class Main extends Application {

    public static void main(String[] args) {
        launch(Main.class);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ModuleConfig.class);

        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }

        Game game = context.getBean(Game.class);
        game.start(primaryStage);
        game.render();

    }
}
