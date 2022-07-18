package de.changefans.model;

public class POI {
    private String name;
    private String type;
    private String location;
    private String opening_hours;

    public POI(String name, String type, String location, String opening_hours) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.opening_hours = opening_hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(String opening_hours) {
        this.opening_hours = opening_hours;
    }
}
