package com.management.fresher.controller;

import com.management.fresher.dto.CenterDto;
import com.management.fresher.response.ResultResponse;
import com.management.fresher.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/center")
public class CenterController {
    private final CenterService service;

    @Autowired
    public CenterController(CenterService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<ResultResponse> createCenter(@RequestBody @Valid CenterDto centerDto){
        service.create(centerDto);
        return new ResponseEntity<>(new ResultResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.toString()), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResultResponse> updateCenter(@RequestBody @Valid CenterDto centerDto){
        service.update(centerDto);
        return new ResponseEntity<>(new ResultResponse(HttpStatus.OK.value(), "Update success"), HttpStatus.OK);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<ResultResponse> deleteCenter(@RequestParam Long id){
        service.delete(id);
        return new ResponseEntity<>(new ResultResponse(HttpStatus.OK.value(), "Update success"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResultResponse> getAllCenter(){
        return new ResponseEntity<>(new ResultResponse(HttpStatus.OK.value(), HttpStatus.OK.toString(), service.getAllCenter()), HttpStatus.CREATED);
    }

    @PutMapping(path = "{center-id}/add-fresher", params = "fresherId")
    public ResponseEntity<ResultResponse> addFresherToCenter(@PathVariable("center-id") Long centerId, @RequestParam Long fresherId){
        service.addFresherToCenter(centerId, fresherId);
        return new ResponseEntity<>(new ResultResponse(HttpStatus.OK.value(), "Add success"), HttpStatus.OK);
    }
}
