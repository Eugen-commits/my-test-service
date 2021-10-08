package ru.digitalleague.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.digitalleague.core.model.AuthorityEntity;
import ru.digitalleague.core.model.UserEntity;
import ru.digitalleague.core.mapper.AuthorityEntityMapper;
import ru.digitalleague.core.mapper.UserEntityMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final AuthorityEntityMapper authorityEntityMapper;

    private final UserEntityMapper userEntityMapper;

    private final PasswordEncoder encoder;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityMapper.findByLogin(userName)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with %s name not found", userName)));
        return userEntity;
    }

    @Override
    public UserEntity registration(UserEntity userEntity) {
        String password = encoder.encode(userEntity.getPassword());
        userEntity.setPassword(password);
        userEntityMapper.insert(userEntity);
        List<AuthorityEntity> authority = userEntity.getAuthorities();
        authority.forEach(authorityEntity -> authorityEntity.setUserAccountId(userEntity.getId()));
        authorityEntityMapper.insertAll(authority);
        return userEntity;
    }

    public List<UserEntity> getAll(){
        List<UserEntity> allUsers = new ArrayList<>();
        userEntityMapper.getAllUsers().iterator().forEachRemaining(allUsers::add);
        return allUsers;
    }
}
