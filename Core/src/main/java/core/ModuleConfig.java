package core;

import common.scoringSystem.IScoringService;
import common.scoringSystem.ScoringLocator;
import common.services.IEntityProcessingService;
import common.services.IGamePluginService;
import common.services.IPostEntityProcessingService;
import java.util.List;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
class ModuleConfig {
    private static final String SCORE_URL = "http://localhost:8080/score?points={pts}";

    public ModuleConfig() {
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate rt = new RestTemplate();

        rt.getMessageConverters().add(
                new MappingJackson2HttpMessageConverter()
        );

        return rt;
    }

    @Bean
    public IScoringService httpScoringService(RestTemplate rt) {
        return new IScoringService() {
            @Override
            public void awardPoints(int pts) {
                try {
                    rt.postForObject(SCORE_URL, null, Long.class, pts);
                } catch (Exception e) {
                    System.err.println("Scoring failed: " + e.getMessage());
                }
            }
            @Override
            public long getTotalPoints() {
                return rt.getForObject("http://localhost:8080/score", Long.class);
            }
        };
    }

    @Bean
    public Game game(IScoringService scorer){
        ScoringLocator.setService(scorer);
        return new Game(
                gamePluginServices(),
                entityProcessingServiceList(),
                postEntityProcessingServices()
        );
    }

    @Bean
    public List<IEntityProcessingService> entityProcessingServiceList(){
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<IGamePluginService> gamePluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<IPostEntityProcessingService> postEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

}