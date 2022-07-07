package de.changefans.controller;
import de.changefans.model.Feedback;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class FeedbackController {
    private final WebClient webClient;
    private final List<Feedback> feedbacks;
//
    public FeedbackController() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        this.feedbacks = new ArrayList<>();
    }

    public void addFeedback(Feedback feedback, Consumer<List<Feedback>> feedbacksConsumer) {
        webClient.post()
                .uri("feedbacks")
                .bodyValue(feedback)
                .retrieve()
                .bodyToMono(Feedback.class)
                .onErrorStop()
                .subscribe(newFeedback -> {
                    feedbacks.add(newFeedback);
                    feedbacksConsumer.accept(feedbacks);
                });
    }


}
