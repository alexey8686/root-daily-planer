package com.bae.spb.daily.planner.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDto implements Serializable {
    public static final long serialVersionUID = 1l;
    private String id;

    @NotBlank(message = "User name must be not empty")
    private String userName;

    @Email (message = "It is not valid email address")
    @NotBlank (message = "Email must be not empty")
    private String email;

    @NotBlank (message = "Password must be not empty")
    private String password;
}
