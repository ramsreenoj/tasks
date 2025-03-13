package com.devtiroFullCourse.tasks.domain;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
