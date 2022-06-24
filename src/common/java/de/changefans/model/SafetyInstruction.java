package de.changefans.model;

import javafx.scene.image.Image;

<<<<<<< HEAD
public class SafetyInstruction {

    private boolean isDetailed;
=======
import java.awt.*;

public class SafetyInstruction {

    private boolean isDetailed;

>>>>>>> 9bf3ff82936b251352490a5d2e9c2d7d1ea5410a
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
<<<<<<< HEAD
=======


>>>>>>> 9bf3ff82936b251352490a5d2e9c2d7d1ea5410a
}
