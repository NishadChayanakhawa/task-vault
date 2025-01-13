package io.github.nishadchayanakhawa.taskvault.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskVaultUIController {
	@GetMapping("/home")
	public String getHomePage() {
		return "home";
	}

	@GetMapping("/configuration/taskType")
	public String getTaskTypeConfigurationPage() {
		return "configuration/taskType";
	}

	@GetMapping("/configuration/taskGroup")
	public String getTaskGroupConfigurationPage() {
		return "configuration/taskGroup";
	}

	@GetMapping("/configuration/resource")
	public String getResourceConfigurationPage() {
		return "configuration/resource";
	}
}
