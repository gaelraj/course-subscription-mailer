package com.school.mailer.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public record CreateCourseRequest(
    @NotBlank(message = "The title is mandatory") String title,
    @NotNull(message = "The start is mandatory") Instant start,
    @NotNull(message = "The end is mandatory") Instant end) {}
