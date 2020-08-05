package com.bozhilov.mysolarplant.web.models;


import com.bozhilov.mysolarplant.data.annotations.FieldMatch;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@FieldMatch(firstField = "password", secondField = "confirmPassword")
public class RegisterUserModel {
    private String username;
    private String password;
    private String confirmPassword;

}
