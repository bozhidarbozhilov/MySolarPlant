package com.bozhilov.mysolarplant.data.models.users;


import com.bozhilov.mysolarplant.data.models.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

import static com.bozhilov.mysolarplant.utils.Constants.*;


@Entity
@Table(name="users")
public class User extends BaseEntity {
    private String username;
    private String password;

//    private boolean isAccountNonExpired;
//    private boolean isAccountNonLocked;
//    private boolean isCredentialsNonExpired;
//    private boolean isEnabled;
//    private Set<Role> authorities;



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



//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    public void setAccountNonExpired(boolean accountNonExpired) {
//        isAccountNonExpired = accountNonExpired;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    public void setAccountNonLocked(boolean accountNonLocked) {
//        isAccountNonLocked = accountNonLocked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
//        isCredentialsNonExpired = credentialsNonExpired;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public void setEnabled(boolean enabled) {
//        isEnabled = enabled;
//    }
//
//    @Override
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name="users_roles",
//                joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
//                inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
//    public Set<Role> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(Set<Role> authorities) {
//        this.authorities = authorities;
//    }
}
