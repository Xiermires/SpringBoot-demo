package org.demo.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home
{
    @RequestMapping("/")
    public String root() {
        return "index.html";
    }
}
