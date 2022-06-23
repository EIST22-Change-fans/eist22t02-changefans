package de.changefans.model;

import java.util.Random;

public class Feedback {
    private  int flightID;
    private int flightScore;
    private int cateringScore;
    private int entertainmentScore;
    private int serviceScore;
    private int comfortScore;
    private String comment;
    private String reward;

    /*

    private Passenger passenger;

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }


     */


    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public Feedback() {
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public int getFlightScore() {
        return flightScore;
    }

    public void setFlightScore(int flightScore) {
        this.flightScore = flightScore;
    }

    public int getCateringScore() {
        return cateringScore;
    }

    public void setCateringScore(int cateringScore) {
        this.cateringScore = cateringScore;
    }

    public int getEntertainmentScore() {
        return entertainmentScore;
    }

    public void setEntertainmentScore(int entertainmentScore) {
        this.entertainmentScore = entertainmentScore;
    }

    public int getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(int serviceScore) {
        this.serviceScore = serviceScore;
    }

    public int getComfortScore() {
        return comfortScore;
    }

    public void setComfortScore(int comfortScore) {
        this.comfortScore = comfortScore;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void reward(){
        Random random= new Random();

        String comment = this.getComment();
        if (!(comment == null || comment.equals(""))){
            int n = random.nextInt(10, 10 * comment.length());
            this.setReward("You have received " + n + " miles!");
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
            this.setReward("You have received a coupon for a free meal at the " + destination + "airport!\n You can find the code in your Coupons' List" );
        }
    }
}
