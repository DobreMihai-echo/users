package com.myplanet.users.repository;

import com.myplanet.users.entity.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {

    List<UserChallenge> findAllByUsernameAndCompletedFalse(String username);

}
