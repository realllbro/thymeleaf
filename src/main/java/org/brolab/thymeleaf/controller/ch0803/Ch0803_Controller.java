package org.brolab.thymeleaf.controller.ch0803;

import org.brolab.thymeleaf.domain.Address;
import org.brolab.thymeleaf.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
public class Ch0803_Controller {

    @GetMapping("ch0803/home0101")
    public String home0200(Model model){
        model.addAttribute("msg", "Hello world!");
        return "ch0803/home0101";
    }

    @GetMapping("ch0803/home0201")
    public String home0201(Model model){
        model.addAttribute("msg", "Hello world!");
        return "ch0803/home0201";
    }

    @GetMapping("ch0803/home0202")
    public String home0202(Model model){
        Member member = new Member();

        member.setUserId("hongkd");
        member.setPassword("1234");
        member.setEmail("bbb@ccc.com");
        member.setUserName("홍길동");

        LocalDate dateOfBirth = LocalDate.of(1988, 10, 7);
        member.setDateOfBirth(dateOfBirth);

        model.addAttribute(member);

        return "ch0803/home0202";
    }

    @GetMapping("ch0803/home0203")
    public String home0203(Model model){
        Member member = new Member();

        Address address = new Address();
        address.setPostCode("080908");
        address.setLocation("seoul");

        member.setAddress(address);
        model.addAttribute(member);

        return "ch0803/home0203";
    }

    @GetMapping("ch0803/home0301")
    public String home0301(Model model){
        Member member = new Member();

        member.setUserId("hongkd");
        member.setPassword("1234");
        member.setEmail("bbb@ccc.com");
        member.setUserName("홍길동");

        LocalDate dateOfBirth = LocalDate.of(1988, 10, 7);
        member.setDateOfBirth(dateOfBirth);

        model.addAttribute(member);

        return "ch0803/home0301";
    }

    @GetMapping("ch0803/home0302")
    public String home0302(Model model){
        Member member = new Member();

        member.setUserId("hongkd");
        member.setPassword("1234");
        member.setEmail("bbb@ccc.com");
        member.setUserName("홍길동");

        LocalDate dateOfBirth = LocalDate.of(1988, 10, 7);
        member.setDateOfBirth(dateOfBirth);

        model.addAttribute(member);

        return "ch0803/home0302";
    }

    @GetMapping("ch0803/home0401")
    public String home0401(Model model){
        return "ch0803/home0401";
    }

    @GetMapping("ch0803/sub/home0402")
    public String home0402(Model model){
        return "ch0803/sub/home0402";
    }

    @GetMapping("ch0803/home0403")
    public String home0403(Model model){
        return "ch0803/home0403";
    }

    @GetMapping("ch0803/home0501")
    public String home0501(Model model){
        return "ch0803/home0501";
    }

    @GetMapping("ch0803/home0601")
    public String home0601(Locale locale, Model model){
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 (E) a h시 m분 s초");
        String formattedNow = now.format(formatter);

        model.addAttribute("serverTime", formattedNow);
        return "ch0803/home0601";
    }

    @GetMapping("ch0803/home0701")
    public String home0701(Locale locale, Model model){
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 (E) a h시 m분 s초");
        String formattedNow = now.format(formatter);

        model.addAttribute("serverTime", formattedNow);
        return "ch0803/home0701";
    }
}



