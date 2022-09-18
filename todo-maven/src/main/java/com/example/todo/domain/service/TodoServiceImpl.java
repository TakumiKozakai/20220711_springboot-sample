package com.example.todo.domain.service;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.domain.exception.ResourceNotFoundException;
import com.example.todo.domain.model.Todo;
import com.example.todo.domain.repository.TodoMapper;;;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	TodoMapper todoMapper;

	@Override
	@Transactional(readOnly = true)
	public Collection<Todo> findAll() {
		return todoMapper.findAll();
	}

	@Override
	public Todo create(Todo todo) {

		String todoId = UUID.randomUUID().toString();
		Date createdAt = new Date();
		todo.setTodoId(todoId);
		todo.setCreatedAt(createdAt);
		todo.setFinished(false);

		todoMapper.create(todo);
		return todo;
	}

	@Override
	public Todo finish(String todoId) {
		Todo todo = findOne(todoId);
		todo.setFinished(true);
		todoMapper.update(todo);

		return todo;
	}

	@Override
	public void delete(String todoId) {
		Todo todo = findOne(todoId);
		todoMapper.delete(todo);
	}

	private Todo findOne(String todoId) {
		return todoMapper.findBy(todoId).orElseThrow(() -> {
			return new ResourceNotFoundException("[ERROR]指定されたTodoが見つかりません.");
		});
	}

}
