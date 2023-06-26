package com.myplanet.users.model;

import com.myplanet.users.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private User user;
    private Boolean followedByAuthUser;
}
