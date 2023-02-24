package org.brolab.thymeleaf.controller.ch0804;

import org.brolab.thymeleaf.domain.Address;
import org.brolab.thymeleaf.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
public class Ch0804_Controller {

    @GetMapping("ch0804/home0101")
    public String home0101(Model model){
        return "ch0804/home0101";
    }

    @GetMapping("ch0804/home0201")
    public String home0201(Model model){
        return "ch0804/home0201";
    }

    @GetMapping("ch0804/home0301")
    public String home0301(Model model){
        return "ch0804/home0301";
    }

    @PostMapping("ch0804/register")
    public String register(Model model) throws Exception {
        model.addAttribute("msg", "등록이 완료되었습니다.");

        return "ch0804/success";
    }
}



