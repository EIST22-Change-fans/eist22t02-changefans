package de.changefans.rest;
import de.changefans.model.Feedback;
import de.changefans.service.FeedbackService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class FeedbackResource {
    private final FeedbackService feedbackService;


    public FeedbackResource(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("feedbacks")
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }

    @PostMapping("feedbacks")
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
        Optional<Feedback> feedback1 = feedbackService.checkExistingFeedback(feedback);
        if (feedback1.isEmpty()) {
            return ResponseEntity.ok(feedbackService.saveFeedback(feedback));
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
}
