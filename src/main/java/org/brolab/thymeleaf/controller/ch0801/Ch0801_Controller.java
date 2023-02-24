package org.brolab.thymeleaf.controller.ch0801;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Ch0801_Controller {

    @GetMapping("ch0801/home")
    public String home0101(Model model){
        model.addAttribute("msg","<b>Hello world!</b>");
        return "ch0801/home";
    }
}



