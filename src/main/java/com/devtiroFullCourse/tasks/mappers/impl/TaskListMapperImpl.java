package com.devtiroFullCourse.tasks.mappers.impl;

import com.devtiroFullCourse.tasks.domain.dto.TaskListDto;
import com.devtiroFullCourse.tasks.domain.entities.Task;
import com.devtiroFullCourse.tasks.domain.entities.TaskList;
import com.devtiroFullCourse.tasks.domain.entities.TaskStatus;
import com.devtiroFullCourse.tasks.mappers.TaskListMapper;
import com.devtiroFullCourse.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks()).map(tasks -> tasks.stream().map(taskMapper::fromDto).toList()).orElse(null),
                null,
                null

        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTask())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(taskList.getTask()),
                Optional.ofNullable(taskList.getTask()).map(tasks -> tasks.stream().map(taskMapper::toDto).toList()).orElse(null));
    }

    private Double calculateTaskListProgress(List<Task> tasks) {
//        if (tasks == null || tasks.isEmpty()) {
//            return 0.0;
//        }
//        int completedTasks = (int) tasks.stream().filter(Task::isCompleted).count();
//        return (double) completedTasks / tasks.size() * 100.0;

        if (tasks == null) {
            return null;
        }

        var closedTasksCount = tasks.stream().filter(task -> task.getTaskStatus() == TaskStatus.CLOSED).count();

        return (double) closedTasksCount / tasks.size();
    }

}
