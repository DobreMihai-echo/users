package com.myplanet.users.controller;

import com.myplanet.users.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/user")
    public ResponseEntity<User> getUsers();

}
