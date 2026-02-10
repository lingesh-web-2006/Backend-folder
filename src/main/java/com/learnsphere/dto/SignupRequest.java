package com.learnsphere.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupRequest {
    private String name;
    private String email;
    private String password;
}
