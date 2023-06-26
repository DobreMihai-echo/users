package com.myplanet.users.controller;

import com.myplanet.users.entity.CarbonFootprint;
import com.myplanet.users.service.CarbonFootprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carbon-footprint")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://34.88.179.57", maxAge = 3600, allowCredentials="true")
public class CarbonFootprintController {

    private final CarbonFootprintService service;

    @GetMapping()
    public ResponseEntity<?> getCarbonFootprintForUser(@RequestParam(name = "username") String username) {
        return ResponseEntity.ok(service.getCarbonFootprintsOfUser(username));
    }

    @PostMapping()
    public ResponseEntity<?> saveCarbonFootprint(@RequestBody CarbonFootprint footprint, @RequestParam(name = "username") String username) {
        System.out.println("CARBON:" + footprint);
        try {
            service.addCarbonFootprintForUser(footprint,username);
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Success\"}");
        } catch (Exception exception) {
            return ResponseEntity.ok(exception.getMessage());
        }
    }
}
