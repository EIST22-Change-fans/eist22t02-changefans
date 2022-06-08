package de.changefans.model;

public class Feedback {
    private  int flightID;
    private int flightScore;
    private int cateringScore;
    private int entertainmentScore;
    private int serviceScore;
    private int comfortScore;
    private String comment;

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
}
