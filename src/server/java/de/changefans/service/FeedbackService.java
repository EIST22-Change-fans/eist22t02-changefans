package de.changefans.service;
import de.changefans.model.Feedback;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class FeedbackService {
    private final List<Feedback> feedbacks;

    public FeedbackService() {
        this.feedbacks=new ArrayList<>();
    }

    public Feedback saveFeedback(Feedback feedback) {
        feedbacks.add(feedback);
        return feedback;
    }

    public List<Feedback> getAllFeedbacks() {
        return Collections.unmodifiableList(this.feedbacks);
    }
}
