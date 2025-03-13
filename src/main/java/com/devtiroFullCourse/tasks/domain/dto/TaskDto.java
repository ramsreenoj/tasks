package com.devtiroFullCourse.tasks.domain.dto;

import com.devtiroFullCourse.tasks.domain.entities.TaskPriority;
import com.devtiroFullCourse.tasks.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status) {
}
