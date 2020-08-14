package com.bozhilov.mysolarplant.web.models;


import com.bozhilov.mysolarplant.data.annotations.FieldMatch;
import com.bozhilov.mysolarplant.data.annotations.UniqueUsername;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

import static com.bozhilov.mysolarplant.utils.Constants.*;

@Getter
@Setter
@NoArgsConstructor
@FieldMatch(firstField = "password", secondField = "confirmPassword")
public class RegisterUserModel {
    @Size(min=USERNAME_MIN_LENGTH,
            max = USERNAME_MAX_LENGTH)
    @UniqueUsername
    private String username;
    @Size(min=PASSWORD_MIN_LENGTH)
    private String password;
    private String confirmPassword;

}
