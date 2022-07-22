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

    /**
     * saves feedback to the feeback list
     * returns feedback
     */
    public Feedback saveFeedback(Feedback feedback) {
        feedbacks.add(feedback);
        return feedback;
    }

    /**
     * checks if the feedback parameter exists already in the feedback list by comparing its
     * flight id to the flight id of all feedbacks in the list. if it exists an Optional.of(feedback)
     * will be returned otherwise Optional.empty()
     */
    public Optional<Feedback> checkExistingFeedback (Feedback feedback) {
        for (Feedback feedback1 : feedbacks){
            if (feedback1.getFlightID().equals(feedback.getFlightID()) /*&& feedback1.getPassenger().equals(feedback.getPassenger())*/){
                return Optional.of(feedback1);
            }
        }
        return Optional.empty();
    }

    /**
     returns the feedback list containing all saved feedbacks
     */
    public List<Feedback> getAllFeedbacks() {
        return Collections.unmodifiableList(this.feedbacks);
    }


}
