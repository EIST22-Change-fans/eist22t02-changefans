package de.changefans.rest;
import de.changefans.model.Feedback;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.IMAGE_JPEG_VALUE})
public class RequestServiceResource {

    @PostMapping("RequestService")
    public ResponseEntity<String> createRequest(@RequestParam("type") String wanted ) {
        return new ResponseEntity<>(wanted, HttpStatus.OK);
    }

    @GetMapping("RequestService")
    public ResponseEntity<String> getMessage() {
        String message="Your request has been sent. A Staff member will come to you soon..";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
