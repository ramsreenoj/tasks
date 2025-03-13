package com.devtiroFullCourse.tasks.services.impl;

import com.devtiroFullCourse.tasks.domain.entities.TaskList;
import com.devtiroFullCourse.tasks.repositories.TaskListRepository;
import com.devtiroFullCourse.tasks.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskList() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList list) {
        if (list.getId() != null) {
            throw new IllegalArgumentException("Task list already having an ID");
        }
        if (list.getTitle() == null || list.getTitle().isBlank()) {
            throw new IllegalArgumentException("Invalid title provided");
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(null, list.getTitle(), list.getDescription(), null, now, now));

    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("No UUID provided");
        }

        return taskListRepository.findById(id);
    }

    @Override
    public TaskList updateTaskList(UUID id, TaskList updatedTaskList) {
        if (updatedTaskList.getId() == null) {
            throw new IllegalArgumentException("Task list must have an ID");
        }

        if (!Objects.equals(updatedTaskList.getId(), id)){
            throw new IllegalArgumentException("Attempting to change task List ID. This is not permitted");
        }

        TaskList foundedTaskList = taskListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id not found"));

        foundedTaskList.setTitle(updatedTaskList.getTitle());
        foundedTaskList.setDescription(updatedTaskList.getDescription());
        foundedTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(foundedTaskList);
    }

    @Override
    public void deleteTaskList(UUID id) {
        taskListRepository.deleteById(id);
    }
}
