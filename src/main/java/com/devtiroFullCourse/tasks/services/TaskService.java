package com.devtiroFullCourse.tasks.services;

import com.devtiroFullCourse.tasks.domain.entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID taskListId);


}
