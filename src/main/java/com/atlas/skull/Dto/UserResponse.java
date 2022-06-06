package com.atlas.skull.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserResponse {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private boolean active;
}
