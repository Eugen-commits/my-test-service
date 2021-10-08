package ru.digitalleague.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.core.model.SearchDriverModel;
import ru.digitalleague.core.service.SearchDriverServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchDriverController {
    @Autowired
    private SearchDriverServiceImpl searchDriverService;

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createSearch(@RequestBody SearchDriverModel searchDriver) {
        searchDriverService.createSearch(searchDriver);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SearchDriverModel>> getAllSearch() {
        return new ResponseEntity<>(searchDriverService.getAllSearch(), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> updateOrder(@RequestBody SearchDriverModel searchDriver) {
        searchDriverService.updateSearch(searchDriver);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> delete(@RequestBody SearchDriverModel searchDriver) {
        searchDriverService.deleteSearch(searchDriver);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
