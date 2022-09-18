package com.example.todo.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class Todo {

    private String todoId;

    private String todoTitle;

    private boolean finished;

    private Date createdAt;
}
