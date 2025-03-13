package com.devtiroFullCourse.tasks.controller;

import com.devtiroFullCourse.tasks.domain.dto.TaskListDto;
import com.devtiroFullCourse.tasks.domain.entities.TaskList;
import com.devtiroFullCourse.tasks.mappers.TaskListMapper;
import com.devtiroFullCourse.tasks.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listTaskList(){
        return taskListService.listTaskList()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
    }

    @GetMapping(path = "/{id}")
    public Optional<TaskListDto> getTaskListByID(@PathVariable("id") UUID id){
        return taskListService.getTaskList(id).map(taskListMapper::toDto);
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto){
        TaskList createdTaskList =  taskListService.createTaskList(taskListMapper.fromDto(taskListDto));
        return taskListMapper.toDto(createdTaskList);
    }

    @PutMapping(path = "/{id}")
    public TaskListDto updateTaskList(@PathVariable("id") UUID id, @RequestBody TaskListDto taskListDto){
        TaskList taskListEntity = taskListService.updateTaskList(id, taskListMapper.fromDto(taskListDto));
        return taskListMapper.toDto(taskListEntity);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTaskList(@PathVariable("id") UUID id) {
        taskListService.deleteTaskList(id);
    }

}
