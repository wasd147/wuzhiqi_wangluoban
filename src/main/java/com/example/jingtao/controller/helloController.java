package com.example.jingtao.controller;

import com.example.jingtao.entrity.Room;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class helloController {
    @RequestMapping("/hello")
    public String fun(String s) {

        Room.roomMap.put(s, 1);
        System.out.println(Room.roomMap);
        return "ok";
    }
}
