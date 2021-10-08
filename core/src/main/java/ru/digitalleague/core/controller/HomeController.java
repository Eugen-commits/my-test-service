package ru.digitalleague.core.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.core.model.UserEntity;
import ru.digitalleague.core.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final UserServiceImpl userService;


    @GetMapping("home")
    public String homePage(Principal principal){
        return "Welcome home dude " + principal.getName();
    }

    @GetMapping("users/all")
    public ResponseEntity<List<UserEntity>> allUsers(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

}
