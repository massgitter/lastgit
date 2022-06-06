package com.atlas.skull.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String username;
    private Long authority;
    private String password;
}
