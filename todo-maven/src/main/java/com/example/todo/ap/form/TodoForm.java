package com.example.todo.ap.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TodoForm {

	private String todoId;

	@NotBlank(message = "内容が入力されていません.")
	private String todoTitle;

	private boolean finished;
}
