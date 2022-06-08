package de.changefans.model;

import javafx.scene.image.Image;

public class SafetyInstruction {

    private boolean isDetailed;
    private Image image;

    public SafetyInstruction(Boolean isDetailed, Image image) {
        this.isDetailed = isDetailed;
        this.image = image;
    }

    public boolean isDetailed() {
        return isDetailed;
    private Image image;

    public SafetyInstruction(String type, Image image) {
        this.image = image;
        detailed = type.equals("detailed");
    }

    public Image getImage() {
        return image;
    }
}
