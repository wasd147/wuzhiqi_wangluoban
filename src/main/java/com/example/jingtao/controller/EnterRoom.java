package com.example.jingtao.controller;

import com.example.jingtao.entrity.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EnterRoom {
    @RequestMapping("/EnterRoom")
    public String enter(String enterid, Model model) {
        if (Room.roomMap.containsKey(enterid) && Room.roomMap.get(enterid) == 1) {
            model.addAttribute("roomid", enterid);
            model.addAttribute("color", "white");
            System.out.println("进入房间");
            Room.roomMap.put(enterid, 2);
            return "play";
        } else {
            return "error";
        }
    }
}
