package org.brolab.thymeleaf.controller.ch0806;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Ch0806_Controller {
	
	@GetMapping("ch0806/home0101")
	public String home0101(Model model) {
		model.addAttribute("username", "Sebastian");
		
		return "ch0806/home0101";
	}
	
	@GetMapping("ch0806/home0102")
	public String home0102(Model model) {
		model.addAttribute("username", "<b>Sebastian</b>");
		
		return "ch0806/home0102";
	}
	
	@GetMapping("ch0806/home0201")
	public String home0201(Model model) {
		model.addAttribute("username", "<b>Sebastian</b>");
		
		return "ch0806/home0201";
	}
	
	@GetMapping("ch0806/home0301")
	public String home0301(Model model) {
		model.addAttribute("username", "Sebastian");
		
		return "ch0806/home0301";
	}
	
	@GetMapping("ch0806/home0401")
	public String home0401(Model model) {
		model.addAttribute("username", "Sebastian");
		
		return "ch0806/home0401";
	}
	
}
