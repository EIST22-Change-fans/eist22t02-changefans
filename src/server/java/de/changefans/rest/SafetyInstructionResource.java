package de.changefans.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.IMAGE_JPEG_VALUE})

public class SafetyInstructionResource {
    @GetMapping("safetyInstruction")
    public ResponseEntity<byte[]> getSafetyInstruction(@RequestParam("isDetailed") boolean detailed) {
        String imagePath;
        if (detailed) {
            imagePath = "/resources/SafetyInstructions/detailedSafetyInstructions.jpg";
        } else {
            imagePath = "/resources/SafetyInstructions/simpleSafetyInstructions.jpg";
        }
        File imageFile = new File(imagePath);
        try {
            byte[] image = Files.readAllBytes(imageFile.toPath());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(image.length);
            return new ResponseEntity<>(image, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
