package com.todo.list.test.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test/requestAnnotation")
public class RequestMappingAnnotationTest {

    @GetMapping(value = "/headers",headers = HttpHeaders.AUTHORIZATION)
    public String doTestRequestMappingArgument_Header(@RequestHeader String authorization) {

        System.out.println(authorization);

        return "SUCCESS";
    }

    @GetMapping(value = "/consumes", consumes = HttpHeaders.AUTHORIZATION)
    public ResponseEntity<?> doTestRequestMappingArgument_consumes() {

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setBearerAuth("/Authorization token~~");

        return new ResponseEntity<>("SUCCESS", httpHeaders, HttpStatus.OK);
    }


    @RequestMapping(value = "/method", method = {RequestMethod.GET , RequestMethod.POST})
    public String doTestRequestMappingArgument_methods() {

        return "SUCCESS";
    }

    @RequestMapping(value = "/produces", produces = "text/plain",method = RequestMethod.GET)
    public String doTestRequestMappingArgument_produces() {

        return "SUCCESS";
    }
}
