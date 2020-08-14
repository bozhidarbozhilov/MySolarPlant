package com.bozhilov.mysolarplant.data.models.users;


import com.bozhilov.mysolarplant.data.models.BaseEntity;
import com.bozhilov.mysolarplant.data.models.plant.SolarPlant;
import com.bozhilov.mysolarplant.data.models.plant.SolarUnit;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;
import java.util.Set;

import static com.bozhilov.mysolarplant.utils.Constants.*;


@Entity
@Table(name="users")
public class User extends BaseEntity implements UserDetails {
    private String username;
    private String password;
    private UserProfile profile;
    private List<SolarUnit> solarUnits;
    private List<SolarPlant> solarPlants;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private Set<Role> authorities;

    @NotNull
    @Size(min = USERNAME_MIN_LENGTH,
            max = USERNAME_MAX_LENGTH)
    @Column(name="username", nullable=false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name="password", nullable=false)
    @Size(min=PASSWORD_MIN_LENGTH)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "user", targetEntity = SolarUnit.class)
    public List<SolarUnit> getSolarUnits() {
        return solarUnits;
    }

    public void setSolarUnits(List<SolarUnit> solarUnits) {
        this.solarUnits = solarUnits;
    }


    @OneToMany(mappedBy = "user", targetEntity = SolarPlant.class)
    public List<SolarPlant> getSolarPlants() {
        return solarPlants;
    }

    public void setSolarPlants(List<SolarPlant> solarPlants) {
        this.solarPlants = solarPlants;
    }



    @OneToOne(mappedBy = "user", targetEntity = UserProfile.class)
    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="users_roles",
                joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
}
