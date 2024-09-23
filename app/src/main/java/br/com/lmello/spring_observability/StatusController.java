package br.com.lmello.spring_observability;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class StatusController {

    private final Random random = new Random();

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
}
