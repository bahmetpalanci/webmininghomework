package com.web.mining.cli.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ApiController {

    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public String index() {

        return "This is Home page";
    }

    @PostMapping(value = "/getResult")
    public String getResult(@RequestParam("message") String message, @RequestParam(value = "dataset", required = false) MultipartFile dataset) {
        return "";
    }
}
