package com.devtiroFullCourse.tasks.mappers;

import com.devtiroFullCourse.tasks.domain.dto.TaskListDto;
import com.devtiroFullCourse.tasks.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto (TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);
}
