package com.fofancy.auth.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDto implements Serializable{
    private String username;
    private String password;
}
