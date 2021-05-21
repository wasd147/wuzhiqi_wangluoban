package com.example.jingtao.controller;

import com.example.jingtao.entrity.Room;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class CreateRoomController {
    @RequestMapping("/CreateRoomController")
    String CreateRoomController(String roomid, Model model) {
        if (Room.roomMap.containsKey(roomid)) {
            return "error";
        } else {
            model.addAttribute("roomid", roomid);
            model.addAttribute("color", "black");
            System.out.println(roomid);
            Room.roomMap.put(roomid, 1);
            return "play";
        }
    }
}
