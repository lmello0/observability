package br.com.lmello.spring_observability;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@RestController
public class StatusController {

    private final Random random = new Random();
    private List<byte[]> memoryHog = new ArrayList<>();

    Logger logger = LoggerFactory.getLogger(StatusController.class);

    @GetMapping("/status")
    public ResponseEntity<?> randomStatus() {
        List<HttpStatus> availableStatus = List.of(HttpStatus.OK, HttpStatus.BAD_REQUEST, HttpStatus.INTERNAL_SERVER_ERROR);

        HttpStatus returnStatus = availableStatus.get(random.nextInt(availableStatus.size()));

        logger.info("Returning [{}] status", returnStatus);

        return ResponseEntity.status(returnStatus.value()).build();
    }

    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok().body("Pong");
    }

    @GetMapping("/cpu_task/{qtd}")
    public ResponseEntity<?> cpuTask(@PathVariable int qtd) {
        int result = 0;
        for (int i = 0; i <= qtd; i++) {
            result += i * i * i;
        }

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/memory_task")
    public ResponseEntity<?> memoryTask() {
        byte[] data = new byte[1024 * 1024];

        memoryHog.add(data);

        return ResponseEntity.ok().body("Consumed 1MB. Total consumed: " + (memoryHog.size()) + "MB");
    }
    
    @GetMapping("/clear_memory")
    public ResponseEntity<?> clearMemory() {
        memoryHog.clear();

        return ResponseEntity.ok().build();
    }
    
    
}
