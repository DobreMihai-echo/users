package com.myplanet.users.repository;

import com.myplanet.users.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    List<User> findUsersByFollowingUsers(User user, Pageable pageable);
    List<User> findUsersByFollowerUsers(User user, Pageable pageable);

    List<User> findUsersByUsername(String name, Pageable pageable);

    List<User> findByUsernameIn(List<String> username);
}
