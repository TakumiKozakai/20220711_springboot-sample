package com.example.todo.domain.repository;

import java.util.Collection;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.todo.domain.model.Todo;

@Mapper
public interface TodoMapper {

	Optional<Todo> findBy(String todoId);

	Collection<Todo> findAll();

	void create(Todo todo);

	boolean update(Todo todo);

	void delete(Todo todo);

	long countByFinished(boolean finished);
}
