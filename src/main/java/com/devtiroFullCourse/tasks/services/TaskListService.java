package com.devtiroFullCourse.tasks.services;

import com.devtiroFullCourse.tasks.domain.entities.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    List<TaskList> listTaskList();
    TaskList createTaskList(TaskList list);

    Optional<TaskList> getTaskList(UUID id);
    TaskList updateTaskList(UUID id, TaskList updatedTaskList);

    void deleteTaskList(UUID id);
}
