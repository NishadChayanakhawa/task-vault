package io.github.nishadchayanakhawa.taskvault.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.nishadchayanakhawa.taskvault.services.ResourceService;
import io.github.nishadchayanakhawa.taskvault.services.TaskGroupService;
import io.github.nishadchayanakhawa.taskvault.services.TaskTypeService;

@Controller
public class TaskVaultUIController {
	private TaskGroupService taskGroupService;
	private TaskTypeService taskTypeService;
	private ResourceService resourceService;

	@Autowired
	public TaskVaultUIController(TaskGroupService taskGroupService, TaskTypeService taskTypeService,ResourceService resourceService) {
		this.taskGroupService = taskGroupService;
		this.taskTypeService = taskTypeService;
		this.resourceService=resourceService;
	}

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

	@GetMapping("/task")
	public String getTaskPage(Model model) {
		model.addAttribute("taskGroups", this.taskGroupService.getAll());
		model.addAttribute("taskTypes", this.taskTypeService.getAll());
		model.addAttribute("resources", this.resourceService.getAll());
		return "task";
	}
}
