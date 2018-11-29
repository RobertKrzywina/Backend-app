package pl.robert.restapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestHomeController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }
}
