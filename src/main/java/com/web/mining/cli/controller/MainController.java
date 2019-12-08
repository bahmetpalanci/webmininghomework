package com.web.mining.cli.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * User: bap
 * Date: 12/8/2019
 * Time: 22:25
 */
@Controller
public class MainController {

    @GetMapping(value = "/")
    public String index(Model model) {
        return "index";
    }
}
