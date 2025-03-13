package com.devtiroFullCourse.tasks.controller;

import com.devtiroFullCourse.tasks.domain.dto.TaskDto;
import com.devtiroFullCourse.tasks.domain.entities.Task;
import com.devtiroFullCourse.tasks.mappers.TaskMapper;
import com.devtiroFullCourse.tasks.services.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists/{task_list_id}/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    private List<TaskDto> getTask(@PathVariable("task_list_id") UUID id) {
        List<Task> tasks = taskService.listTasks(id);
        return tasks.stream().map(taskMapper::toDto).toList();
    }


}
