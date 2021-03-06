package ru.digitalleague.core.model;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

@Data
public class UserEntity implements UserDetails {
    private Long id;

    private String login;

    private String password;

    private List<AuthorityEntity> authorities;


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
