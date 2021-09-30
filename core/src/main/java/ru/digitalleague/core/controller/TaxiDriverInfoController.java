package ru.digitalleague.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.core.model.TaxiDriverInfoModel;
import ru.digitalleague.core.service.TaxiDriverInfoService;

import java.util.List;

@RestController
@RequestMapping ("/driver")
public class TaxiDriverInfoController {
    @Autowired
    private TaxiDriverInfoService taxiDriverInfoService;

    @GetMapping("/all")
    public ResponseEntity<List<TaxiDriverInfoModel>> getAllDrivers(){
        return new ResponseEntity<>(taxiDriverInfoService.getAllDrivers(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> createDriver(@RequestBody TaxiDriverInfoModel taxiDriverInfoModel){
        taxiDriverInfoService.createDriverInfo(taxiDriverInfoModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteDriver(@RequestBody TaxiDriverInfoModel taxiDriverInfoModel){
        taxiDriverInfoService.deleteDriver(taxiDriverInfoModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
