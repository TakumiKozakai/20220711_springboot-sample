package com.example.todo.ap.controller;

import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.todo.ap.form.TodoForm;
import com.example.todo.domain.model.Todo;
import com.example.todo.domain.service.TodoService;

@Controller
@RequestMapping("/todo")
public class TodoController {

	@Autowired
	private TodoService todoService;

	@Autowired
	private ModelMapper modelMapper;

	@ModelAttribute
	public TodoForm setUpForm() {
		return new TodoForm();
	}

	@GetMapping("/list")
	public String list(TodoForm todoForm, Model model) {

		Collection<Todo> todos = todoService.findAll();
		model.addAttribute("todos", todos);
		return "todo/list";
	}

	@PostMapping("/create")
	public String create(@Validated TodoForm todoForm, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return list(todoForm, model);
		}

		Todo todo = modelMapper.map(todoForm, Todo.class);
		todoService.create(todo);
		return "redirect:/todo/list";
	}

	@PostMapping("/finish")
	public String finish(@Validated TodoForm todoForm, BindingResult bindingResult, Model model,
			RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return list(todoForm, model);
		}

		todoService.finish(todoForm.getTodoId());
		return "redirect:/todo/list";
	}

	@PostMapping("/delete")
	public String delete(@Validated TodoForm todoForm, BindingResult bindingResult, Model model,
			RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return list(todoForm, model);
		}

		todoService.delete(todoForm.getTodoId());
		return "redirect:/todo/list";
	}

}
