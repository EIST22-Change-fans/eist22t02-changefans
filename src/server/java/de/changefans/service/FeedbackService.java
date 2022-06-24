package de.changefans.service;
import de.changefans.model.Feedback;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

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

    public Optional<Feedback> checkExistingFeedback (Feedback feedback) {
        for (Feedback feedback1 : feedbacks){
            if (feedback1.getFlightID() == feedback.getFlightID() /*&& feedback1.getPassenger().equals(feedback.getPassenger())*/){
                return Optional.of(feedback1);
            }
        }
        return Optional.empty();
    }

    public List<Feedback> getAllFeedbacks() {
        return Collections.unmodifiableList(this.feedbacks);
    }
}
