package com.myplanet.users.authentication;

import com.myplanet.users.entity.UserChallenge;
import com.myplanet.users.service.UserChallengeService;
import org.bouncycastle.asn1.cmp.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-chalenge")
@CrossOrigin(origins = "http://localhost:8085")
public class UserChallengeController {

    @Autowired
    UserChallengeService service;

    @GetMapping()
    public ResponseEntity<List<UserChallenge>> getAllChallengesForUser(@RequestParam(name = "username") String username) {
        return ResponseEntity.ok(service.findChallengesForUser(username));
    }

    @PostMapping()
    public ResponseEntity<String> save(@RequestBody List<UserChallenge> challenges, @RequestParam(name = "username") String username) {
        return ResponseEntity.ok(service.saveChallenges(challenges,username));
    }

    @PutMapping("/complete")
    public ResponseEntity<String> completeChallenge(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(service.completeChallenge(id));
    }
}
