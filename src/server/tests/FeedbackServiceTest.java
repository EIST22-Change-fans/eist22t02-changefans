import de.changefans.model.Feedback;
import de.changefans.service.FeedbackService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class FeedbackServiceTest {
    @Test
    public void testSaveFeedback() {
        FeedbackService feedbackService = new FeedbackService();
        Feedback feedback = new Feedback();
        assertEquals(feedback, feedbackService.saveFeedback(feedback));

    }

    @Test
    public void testcheckExistingFeedback() {
        FeedbackService feedbackService = new FeedbackService();
        Feedback feedback1 = new Feedback();
        feedback1.setFlightID("TUN2022");
        Feedback feedback2 = new Feedback();
        feedback2.setFlightID("TOK9944");
        Feedback feedback3 = new Feedback();
        feedback3.setFlightID("TUN2022");

        assertEquals(Optional.empty(), feedbackService.checkExistingFeedback(feedback1));
        feedbackService.saveFeedback(feedback1);
        assertEquals(Optional.empty(), feedbackService.checkExistingFeedback(feedback2));
        feedbackService.saveFeedback(feedback2);
        feedbackService.saveFeedback(feedback3);
        assertEquals(feedback1, feedbackService.checkExistingFeedback(feedback3).get());

    }
    @Test
    public void testGetAllFeedbacks() {
        FeedbackService feedbackService = new FeedbackService();
        Feedback fb1 = new Feedback();
        Feedback fb2 = new Feedback();
        Feedback fb3 = new Feedback();
        Feedback fb4 = new Feedback();

        List<Feedback> feedbackList = feedbackService.getAllFeedbacks();
        assertTrue(feedbackList.isEmpty());

        feedbackService.saveFeedback(fb1);
        feedbackService.saveFeedback(fb2);
        feedbackService.saveFeedback(fb3);
        feedbackService.saveFeedback(fb4);

        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        assertEquals(feedbacks.size(), 4);
        assertEquals(feedbacks.get(0), fb1);
        assertEquals(feedbacks.get(1), fb2);
        assertEquals(feedbacks.get(2), fb3);
        assertEquals(feedbacks.get(3), fb4);

    }

}
