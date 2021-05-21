package com.example.jingtao.controller;

import com.example.jingtao.entrity.Room;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class RoomListController {
    @RequestMapping("/getRoomList")
    public Set<String> getRoomList() {
        return Room.roomMap.keySet();
    }
}
