package de.changefans.controller;

import de.changefans.model.RequestService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.function.Consumer;

public class RequestServiceController {

    private final WebClient webClient;


    public RequestServiceController() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public void addService(Consumer<RequestService> serviceConsumer) {
        webClient.post()
                .uri("Services")
                .retrieve()
                .bodyToMono(RequestService.class)
                .onErrorStop()
                .subscribe(serviceConsumer::accept);
    }
}
