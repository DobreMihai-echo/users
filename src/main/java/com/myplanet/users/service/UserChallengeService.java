package com.myplanet.users.service;

import com.myplanet.users.entity.UserChallenge;
import com.myplanet.users.repository.UserChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserChallengeService {

    @Autowired
    private UserChallengeRepository repository;

    public List<UserChallenge> findChallengesForUser(String username) {
        return repository.findAllByUsernameAndCompletedFalse(username);
    }

    public String saveChallenges(List<UserChallenge> challenges, String username) {
        List<UserChallenge> challengesToUpdate = new ArrayList<>();
        for (UserChallenge challenge:challenges) {
            UserChallenge myChallenge = new UserChallenge();
            myChallenge.setColor(challenge.getColor());
            myChallenge.setCompleted(false);
            myChallenge.setDescription(challenge.getDescription());
            myChallenge.setLevel(challenge.getLevel());
            myChallenge.setPoints(challenge.getPoints());
            myChallenge.setCreator(challenge.getCreator());
            myChallenge.setTile(challenge.getTile());
            myChallenge.setUsername(username);
        }
        repository.saveAll(challengesToUpdate);
        return "Success";
    }

    public String completeChallenge(Long id) {
        UserChallenge userChallengeToUpdate = repository.findById(id).get();
        userChallengeToUpdate.setCompleted(true);
        repository.save(userChallengeToUpdate);
        return "Success";
    }
}
