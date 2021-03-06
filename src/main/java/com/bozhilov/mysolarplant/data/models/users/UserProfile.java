package com.bozhilov.mysolarplant.data.models.users;


import com.bozhilov.mysolarplant.data.models.BaseEntity;
import com.bozhilov.mysolarplant.data.models.plant.SolarPlant;
import com.bozhilov.mysolarplant.data.models.plant.SolarUnit;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users_profiles")
public class UserProfile extends BaseEntity {
    private User user;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String information;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name="first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Column(name="last_name",nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Column(name="email", nullable=false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="information")
    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }


}
