package org.brolab.thymeleaf.controller.ch0810;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Ch0810_Controller {
	
	@GetMapping("ch0810/home0101")
	public String home0101(Model model) {
		int coin = 1000;
		
		model.addAttribute("coin", coin);
		
		return "ch0810/home0101";
	}

	@GetMapping("ch0810/home0201")
	public String home0201(Model model) {
		Date date = new Date();
		
		model.addAttribute("now", date);
		
		return "ch0810/home0201";
	}
	
	@GetMapping("ch0810/home0301")
	public String home0301(Model model) {
		String str = "Hello, World!";
		
		model.addAttribute("str", str);
		
		return "ch0810/home0301";
	}

	@GetMapping("ch0810/home0401")
	public String home0401(Model model) {
		LocalDateTime now = LocalDateTime.now();
		
		model.addAttribute("now", now);
		
		return "ch0810/home0401";
	}
	
}
