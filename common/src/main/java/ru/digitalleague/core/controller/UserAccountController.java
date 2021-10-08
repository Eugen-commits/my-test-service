package ru.digitalleague.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.core.model.UserEntity;
import ru.digitalleague.core.service.UserServiceImpl;

@RestController
@RequiredArgsConstructor
public class UserAccountController {

    private final UserServiceImpl userService;

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody UserEntity userEntity){
        userService.registration(userEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
