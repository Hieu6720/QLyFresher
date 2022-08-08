package com.management.fresher.controller;

import com.management.fresher.dto.FresherDto;
import com.management.fresher.response.ResultResponse;
import com.management.fresher.service.FresherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/dashboard")
@Validated
public class DashboardController {
    private final FresherService fresherService;

    @Autowired
    public DashboardController(FresherService fresherService) {
        this.fresherService = fresherService;
    }

    @GetMapping(path = "quantity", params = "centerId")
    public ResponseEntity<ResultResponse> quantityStatistics(@RequestParam Long centerId){
        List<FresherDto> fresherList = fresherService.getFresherByCenterId(centerId);
        Map<String, Object> result = new HashMap<>();
        result.put("centerId", centerId);
        result.put("quantityFresher",fresherList.size());
        result.put("fresherList",fresherList);
        ResultResponse response = new ResultResponse(HttpStatus.OK.value(), HttpStatus.OK.toString(), result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping(path = "score/{type}", params = "score")
    public ResponseEntity<ResultResponse> testScore1Statistics(@RequestParam @Min(0) @Max(10) Float score, @PathVariable String type) {
        List<FresherDto> fresherList = fresherService.getFresherByTestScore(score, type);
        Map<String, Object> result = new HashMap<>();
        result.put(type+" score",score);
        result.put("totalFresher", fresherList.size());
        result.put("fresherList", fresherList);
        ResultResponse response = new ResultResponse(HttpStatus.OK.value(), HttpStatus.OK.toString(), result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
