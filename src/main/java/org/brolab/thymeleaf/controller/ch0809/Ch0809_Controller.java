package org.brolab.thymeleaf.controller.ch0809;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Ch0809_Controller {
	
	@GetMapping("ch0809/home0101")
	public String home0101(Model model) {
		model.addAttribute("msg", "Hello world!");
		
		return "ch0809/home0101";
	}
	
	@GetMapping("ch0809/home0201")
	public String home0201(Model model) {
		model.addAttribute("msg", "Hello world!");
		
		return "ch0809/home0201";
	}
	
}
