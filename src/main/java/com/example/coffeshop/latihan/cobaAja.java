package com.example.coffeshop.latihan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class cobaAja {

    @GetMapping("/hai")
    public String hello(){
        return "hellohello";
    }
}
