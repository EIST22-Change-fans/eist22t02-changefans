package de.changefans.model;

import javafx.scene.image.Image;

import java.awt.*;

public class SafetyInstruction {


    private boolean detailed;
    private Image image;

    public SafetyInstruction(String type, Image image) {
        this.image = image;
        detailed = type.equals("detailed");
    }

    public boolean isDetailed() {
        return detailed;
    }

    public Image getImage() {
        return image;
    }


}
