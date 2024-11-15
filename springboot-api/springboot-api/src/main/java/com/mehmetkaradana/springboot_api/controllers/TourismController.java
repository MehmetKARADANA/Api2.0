
package com.mehmetkaradana.springboot_api.controllers;

import com.mehmetkaradana.springboot_api.services.AuthService;
import com.mehmetkaradana.springboot_api.services.TourismAgencyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TourismController {

    private final TourismAgencyService tourismAgencyService;

    @Autowired
    AuthService authService;

    public TourismController(TourismAgencyService tourismAgencyService) {
        this.tourismAgencyService = tourismAgencyService;
    }

    @GetMapping("/getRooms")
    public ResponseEntity<?> getRooms(HttpServletRequest request) {
        // Validate the token
        ResponseEntity<String> tokenValidationResponse = authService.validateToken(request);

        // If the token is invalid, return the response from the auth service
        if (!tokenValidationResponse.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(tokenValidationResponse.getStatusCode()).body(tokenValidationResponse.getBody());
        }

        List<String> rooms = tourismAgencyService.getRoomsFromAgencies();
        return ResponseEntity.ok(rooms);
    }
}
