package com.bozhilov.mysolarplant.data.models.users;


import com.bozhilov.mysolarplant.data.models.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users_profiles")
@Getter
@Setter
public class UserProfile extends BaseEntity {
    @OneToOne(optional = false)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
    @Column(name="first_name")
    private String firstName;
    @NotNull
    @Column(name="last_name",nullable = false)
    private String lastName;
    @NotNull
    @Email
    @Column(name="email", nullable=false, unique=true)
    private String email;
    @Column(name="address")
    private String address;
    @Column(name="information")
    private String information;

}
