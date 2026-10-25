package com.devguard.api.dto.response;

import com.devguard.api.entity.RepositoryEntity;
import com.devguard.api.enums.RepositoryStatus;
import java.time.LocalDateTime;

public record RepositoryResponse(
        Long id, String name, String owner, String url,
        String defaultBranch, String language,
        RepositoryStatus status, LocalDateTime createdAt) {

    public static RepositoryResponse from(RepositoryEntity repository) {
        return new RepositoryResponse(
                repository.getId(), repository.getName(), repository.getOwner(),
                repository.getUrl(), repository.getDefaultBranch(), repository.getLanguage(),
                repository.getStatus(), repository.getCreatedAt());
    }
}
