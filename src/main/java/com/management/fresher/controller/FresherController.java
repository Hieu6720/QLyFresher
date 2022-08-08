package com.management.fresher.controller;

import com.management.fresher.dto.FresherDto;
import com.management.fresher.entity.Fresher;
import com.management.fresher.exception.CustomException;
import com.management.fresher.response.ResultResponse;
import com.management.fresher.service.FresherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "api/fresher")
@Validated
public class FresherController {
    private final FresherService fresherService;

    @Autowired
    public FresherController(FresherService fresherService) {
        this.fresherService = fresherService;
    }

    @GetMapping(path = "query", params = "name")
    public ResponseEntity<ResultResponse> getFresherByName(@RequestParam String name){
        ResultResponse result = new ResultResponse(HttpStatus.OK.value(), HttpStatus.OK.toString(),fresherService.getFresherByName(name));
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping(path = "query", params = "programLanguage")
    public ResponseEntity<ResultResponse> getFresherByProgramLanguage(@RequestParam String programLanguage){
        ResultResponse result = new ResultResponse(HttpStatus.OK.value(), HttpStatus.OK.toString(),fresherService.getFresherByProgramLanguage(programLanguage));
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping(path = "query", params = "email")
    public ResponseEntity<ResultResponse> getFresherByEmail(@Email @RequestParam String email){
        ResultResponse result = new ResultResponse(HttpStatus.OK.value(), HttpStatus.OK.toString(),fresherService.getFresherByEmail(email));
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResultResponse> getAllFresher(){
        ResultResponse result = new ResultResponse(HttpStatus.OK.value(), HttpStatus.OK.toString(),fresherService.getAllFresher());
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResultResponse> createFresher(@RequestBody @Valid FresherDto fresherDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Bad request");
        }
        fresherService.create(fresherDto);
        return new ResponseEntity<>(new ResultResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.toString()),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResultResponse> updateFresher(@RequestBody @Valid FresherDto fresherDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Bad request");
        }
        fresherService.update(fresherDto);

        return new ResponseEntity<>(new ResultResponse(HttpStatus.OK.value(), "Update success"),HttpStatus.OK);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<ResultResponse> deleteFresher(@RequestParam Long id){
        fresherService.delete(id);
        return new ResponseEntity<>(new ResultResponse(HttpStatus.OK.value(), "Delete success"),HttpStatus.OK);
    }
}
