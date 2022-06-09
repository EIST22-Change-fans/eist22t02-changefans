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
        feedback.setReward(reward(feedback));
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

    private String reward(Feedback feedback){
        Random random= new Random();

        String comment = feedback.getComment();
        if (!(comment == null || comment.equals(""))){
            int n = random.nextInt(10, 10 * comment.length());
            feedback.setReward("You have received " + n + " miles!");
            //feedback.getPassenger.updateMiles(n);
        }
        else {
            StringBuilder code = new StringBuilder(10);
            for (int i = 0; i<10; i++) {
                code.append((char) random.nextInt(256)); //generate random code
            }
            //feedback.getPassenger().addCouponCode(code.toString()); //add coupon code to passenger's couponsList

            String destination = "";
            //destination = feedback.getPassenger().getFlightList().stream().filter(x -> x.getID() == feedback.getFlightID()).toList().get(0).getArrivalPlace().getName();
            feedback.setReward("You have received a coupon for a free meal at the " + destination + "airport!\n You can find the code in your Coupons' List" );
        }
        return "";
    }
}
