package com.devguard.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRepositoryRequest(
        @NotBlank(message = "Repository URL is required")
        @Size(max = 255, message = "Repository URL must not exceed 255 characters")
        String url) {
}
