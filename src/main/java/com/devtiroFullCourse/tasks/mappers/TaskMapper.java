package com.devtiroFullCourse.tasks.mappers;

import com.devtiroFullCourse.tasks.domain.dto.TaskDto;
import com.devtiroFullCourse.tasks.domain.entities.Task;

public interface TaskMapper {

    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);

}
