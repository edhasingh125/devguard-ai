package com.devguard.api.service;

import com.devguard.api.dto.request.RegisterRepositoryRequest;
import com.devguard.api.dto.response.RepositoryResponse;
import com.devguard.api.entity.RepositoryEntity;
import com.devguard.api.exception.DuplicateResourceException;
import com.devguard.api.exception.ResourceNotFoundException;
import com.devguard.api.repository.RepositoryRepository;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RepositoryService {
    // Accept only repository roots, never arbitrary hosts or nested GitHub pages.
    private static final Pattern GITHUB_URL = Pattern.compile(
            "^https://github\\.com/([a-z0-9](?:[a-z0-9-]{0,37}[a-z0-9])?)/([a-z0-9_.-]{1,100})/?$",
            Pattern.CASE_INSENSITIVE);

    private final RepositoryRepository repositories;

    public RepositoryService(RepositoryRepository repositories) {
        this.repositories = repositories;
    }

    @Transactional
    public RepositoryResponse registerRepository(RegisterRepositoryRequest request) {
        String input = request.url() == null ? "" : request.url().strip();
        var match = GITHUB_URL.matcher(input);
        if (!match.matches()) {
            throw new IllegalArgumentException("Use a GitHub repository URL: https://github.com/owner/repository");
        }
        String owner = match.group(1).toLowerCase(Locale.ROOT);
        String name = match.group(2).toLowerCase(Locale.ROOT);
        if (name.endsWith(".git")) {
            name = name.substring(0, name.length() - 4);
        }
        if (name.isBlank() || name.equals(".") || name.equals("..")) {
            throw new IllegalArgumentException("Repository name is invalid");
        }
        // GitHub names are case-insensitive; clone suffixes and trailing slashes
        // must not allow a second registration of the same repository.
        String url = "https://github.com/" + owner + "/" + name;
        if (repositories.existsByUrl(url)) {
            throw new DuplicateResourceException("Repository is already registered");
        }

        var repository = new RepositoryEntity();
        repository.setOwner(owner);
        repository.setName(name);
        repository.setUrl(url);
        try {
            return RepositoryResponse.from(repositories.saveAndFlush(repository));
        } catch (DataIntegrityViolationException ex) {
            // The unique constraint also protects against concurrent registrations.
            if (ex.getMostSpecificCause() instanceof java.sql.SQLException sql
                    && "23505".equals(sql.getSQLState())) {
                throw new DuplicateResourceException("Repository is already registered");
            }
            throw ex;
        }
    }

    public List<RepositoryResponse> getAllRepositories() {
        return repositories.findAll(Sort.by("id")).stream()
                .map(RepositoryResponse::from).toList();
    }

    public RepositoryResponse getRepositoryById(Long id) {
        return repositories.findById(id).map(RepositoryResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("Repository not found: " + id));
    }
}
