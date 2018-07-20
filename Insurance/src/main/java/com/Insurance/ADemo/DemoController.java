package com.Insurance.ADemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping("demo")
    public String hello() {
        return "成功喽";
    }

}
