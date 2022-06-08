package de.changefans.controller;

import de.changefans.model.SafetyInstruction;
import javafx.scene.image.Image;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


import java.io.ByteArrayInputStream;
import java.util.function.Consumer;

public class SafetyInstructionController {

    private final WebClient webClient;

    public SafetyInstructionController() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.IMAGE_JPEG_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                .build();
    }

    public void getSafetyInstruction(Boolean isDetailed, Consumer<SafetyInstruction> imageConsumer) {
        byte[] imageBytes = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("safetyInstruction")
                        .queryParam("isDetailed", isDetailed).build())
                .retrieve()
                .bodyToMono(byte[].class)
                .onErrorStop()
                .block();
        assert imageBytes != null;
        imageConsumer.accept(new SafetyInstruction(isDetailed, new Image(new ByteArrayInputStream(imageBytes))));

    }
}
