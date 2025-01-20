package io.github.nishadchayanakhawa.taskvault.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TaskDTO {
	private Long id;
	private Long taskTypeId;
	private String taskTypeName;
	private Long taskGroupId;
	private String taskGroupName;
	private String name;
	private String priorityCode;
	private String priorityDisplayValue;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDate;
}
