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
    }

    public Image getImage() {
        return image;
    }
}
