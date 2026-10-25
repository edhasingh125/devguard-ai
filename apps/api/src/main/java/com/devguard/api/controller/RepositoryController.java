package com.devguard.api.controller;

import com.devguard.api.dto.request.RegisterRepositoryRequest;
import com.devguard.api.dto.response.RepositoryResponse;
import com.devguard.api.service.RepositoryService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/repositories")
public class RepositoryController {
    private final RepositoryService service;

    public RepositoryController(RepositoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RepositoryResponse> register(
            @Valid @RequestBody RegisterRepositoryRequest request) {
        var repository = service.registerRepository(request);
        return ResponseEntity.created(URI.create("/api/repositories/" + repository.id()))
                .body(repository);
    }

    @GetMapping
    public List<RepositoryResponse> getAll() {
        return service.getAllRepositories();
    }

    @GetMapping("/{id}")
    public RepositoryResponse getById(@PathVariable Long id) {
        return service.getRepositoryById(id);
    }
}
