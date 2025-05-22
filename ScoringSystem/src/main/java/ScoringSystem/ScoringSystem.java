package ScoringSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
@RestController
public class ScoringSystem {

    private final AtomicLong totalScore = new AtomicLong();
    public static void main(String[] args) {
        SpringApplication.run(ScoringSystem.class, args);
    }

    @PostMapping("/score")
    public long addScore(@RequestParam("points") long points) {
        return totalScore.addAndGet(points);
    }

    @GetMapping("/score")
    public long getScore() {
        return totalScore.get();
    }
}