package org.brolab.thymeleaf.controller.ch0802;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Ch0802_Controller {

    @GetMapping("ch0802/home0101")
    public String home0101(Model model){
        model.addAttribute("msg","<b>Hello world!</b>");
        return "ch0802/home0101";
    }

    @GetMapping("ch0802/home0102")
    public String home0102(Model model){
        model.addAttribute("msg","<b>Hello world!</b>");
        return "ch0802/home0102";
    }
}



