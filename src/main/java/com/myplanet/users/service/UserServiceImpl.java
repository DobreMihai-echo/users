package com.myplanet.users.service;

import com.myplanet.users.entity.User;
import com.myplanet.users.model.UserResponse;
import com.myplanet.users.repository.UserRepository;
import com.myplanet.users.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl{

    private final UserRepository userRepository;

    public Boolean save(User user) {

        try {
            userRepository.save(user);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return true;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    public List<User> getFollowerUsersPaginate(Long userId, Integer page, Integer size) {
        User targetUser = getUserById(userId);
        return new ArrayList<>(userRepository.findUsersByFollowingUsers(targetUser,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstName", "lastName"))));
    }

    public List<User> getFollowingUsersPaginate(Long userId, Integer page, Integer size) {
        User targetUser = getUserById(userId);
        return new ArrayList<>(userRepository.findUsersByFollowerUsers(targetUser,
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "firstName", "lastName"))));
    }

    public List<User> getAllUsersByUsername(List<String> usernames) {
        return userRepository.findByUsernameIn(usernames);
    }

    public void followUser(Long userId) {
        User authUser = getAuthenticatedUser();
        if (!authUser.getId().equals(userId)) {
            User userToFollow = getUserById(userId);
            authUser.getFollowingUsers().add(userToFollow);
            authUser.setFollowingCount(authUser.getFollowingCount() + 1);
            userToFollow.getFollowerUsers().add(authUser);
            userToFollow.setFollowerCount(userToFollow.getFollowerCount() + 1);
            userRepository.save(userToFollow);
            userRepository.save(authUser);
        } else {
            throw new RuntimeException();
        }
    }

    public void unfollowUser(Long userId) {
        User authUser = getAuthenticatedUser();
        if (!authUser.getId().equals(userId)) {
            User userToUnfollow = getUserById(userId);
            authUser.getFollowingUsers().remove(userToUnfollow);
            authUser.setFollowingCount(authUser.getFollowingCount() - 1);
            userToUnfollow.getFollowerUsers().remove(authUser);
            userToUnfollow.setFollowerCount(userToUnfollow.getFollowerCount() - 1);
            userRepository.save(userToUnfollow);
            userRepository.save(authUser);
        } else {
            throw new RuntimeException();
        }
    }

    public List<UserResponse> getUserSearchResult(String key, Integer page, Integer size) {

        return userRepository.findUsersByUsername(
                key,
                PageRequest.of(page, size)
        ).stream().map(this::userToUserResponse).collect(Collectors.toList());
    }

    private UserResponse userToUserResponse(User user) {
        User authUser = userRepository.findByUsername("test").get();
        return UserResponse.builder()
                .user(user)
                .followedByAuthUser(user.getFollowerUsers().contains(authUser))
                .build();
    }

//    public User updateProfilePhoto(MultipartFile profilePhoto) {
//        User targetUser = getAuthenticatedUser();
//        if (!profilePhoto.isEmpty() && profilePhoto.getSize() > 0) {
//            String uploadDir = "uploads/user";
//            String oldPhotoName = targetUser.getProfilePhoto();
//            String newPhotoName = fileNamingUtil.nameFile(profilePhoto);
//            String newPhotoUrl = environment.getProperty("app.root.backend") + File.separator
//                    + environment.getProperty("upload.user.images") + File.separator + newPhotoName;
//            targetUser.setProfilePhoto(newPhotoUrl);
//            try {
//                if (oldPhotoName == null) {
//                    fileUploadUtil.saveNewFile(uploadDir, newPhotoName, profilePhoto);
//                } else {
//                    fileUploadUtil.updateFile(uploadDir, oldPhotoName, newPhotoName, profilePhoto);
//                }
//            } catch (IOException e) {
//                throw new RuntimeException();
//            }
//        }
//        return userRepository.save(targetUser);
//    }

    public final User getAuthenticatedUser() {
//        String authUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        System.out.println("AUTH:" + userDetails);
        return getUserByUsername("test");
    }
}
